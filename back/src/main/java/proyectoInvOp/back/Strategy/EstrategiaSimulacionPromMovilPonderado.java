package proyectoInvOp.back.Strategy;

import proyectoInvOp.back.DTOS.*;

import java.nio.channels.FileLock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EstrategiaSimulacionPromMovilPonderado implements EstrategiaSimulacion{
    @Override
    public DTOResultadoSimu simular(List<DTOVentas> ventas, List<DTOParametroValor> parametros) {


        float mesesAtras = 0;
        List<Float> pesos = new ArrayList<>();

        for (DTOParametroValor param : parametros) {
            String nombre = param.getNombreParametro();
            if ("Periodos historicos".equals(nombre)) {
                mesesAtras = param.getValorParametro();
            } else if (nombre.startsWith("pesoMensual")) {
                pesos.add(param.getValorParametro());
            }
        }

        // Asegurarse de que solo se consideren los últimos 12 meses
        int maxMesesConsiderados = 12;
        if (pesos.size() > maxMesesConsiderados) {
            pesos = pesos.subList(0, maxMesesConsiderados);
        }

        // Agregar ceros para completar los 12 meses si hay menos pesos
        while (pesos.size() < maxMesesConsiderados) {
            pesos.add(0.0f);
        }

        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaDesde = fechaActual.minusMonths((long) mesesAtras);

        float sumaErrores = 0;
        List<Float> demandasReales = new ArrayList<>();
        while (!fechaDesde.isAfter(fechaActual)) {


            float demandaReal = 0;
            boolean tieneVentas = false;

            for (DTOVentas venta : ventas) {
                if (venta.getFecha().getMonthValue() == fechaDesde.getMonthValue()) {
                    demandaReal = venta.getCantidadVentas();
                    demandasReales.add(demandaReal);
                    tieneVentas = true;

                    break;
                }
            }

            if (tieneVentas) {
                if (demandasReales.size() > mesesAtras) {
                    demandasReales.remove(0);  // Eliminar el valor más antiguo
                }

                if (demandasReales.size() == mesesAtras) {
                    float pronostico = 0;
                    for (int i = 0; i < pesos.size(); i++) {
                        pronostico += demandasReales.get(i) * pesos.get(i);
                    }

                    float err = Math.abs(demandaReal - pronostico);
                    sumaErrores += err;

                }
            }

            fechaDesde = fechaDesde.plusMonths(1);
        }



        DTOResultadoSimu resultadoSimu = new DTOResultadoSimu();
        resultadoSimu.setErrorObtenido((double)sumaErrores);
        resultadoSimu.setNombreMetodo("PromMovilPonderado");
        //agregamos esto pq despues lo necesitamos en la prediccion
        resultadoSimu.setValoresParametros(pesos);

        System.out.println("******************");
        System.out.println("simule con PROMMOVILPONDERADO");
        System.out.println("obtuve  " + resultadoSimu.getErrorObtenido());
        System.out.println("******************");

        return resultadoSimu;
    }
}
