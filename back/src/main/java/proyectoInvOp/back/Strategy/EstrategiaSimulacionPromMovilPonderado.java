package proyectoInvOp.back.Strategy;

import proyectoInvOp.back.DTOS.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EstrategiaSimulacionPromMovilPonderado implements EstrategiaSimulacion{
    @Override
    public DTOResultadoSimu simular(List<DTOVentas> ventas, List<DTOParametroValor> parametros) {

        System.out.println("               ");
        System.out.println("******SIMULACION PM PONDERADO*********");
        for (DTOParametroValor par:parametros){
            System.out.println("nombre parametro: "+par.getNombreParametro()+", valor parametro: "+par.getValorParametro());
        }

        float mesesAtras = 0;
        List<Float> pesos = new ArrayList<>();

        for (DTOParametroValor param : parametros) {
            String nombre = param.getNombreParametro();
            if ("Periodos historicos".equals(nombre)) {
                mesesAtras = param.getValorParametro();
            } else if (nombre.startsWith("peso")) {
                pesos.add(param.getValorParametro());
            }
        }

        if (pesos.size() != mesesAtras) {
            throw new IllegalArgumentException("La cantidad de pesos no coincide con la cantidad de periodos históricos");
        }

        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaDesde = fechaActual.minusMonths((long) mesesAtras);
        System.out.println(fechaActual);
        System.out.println(fechaDesde);

        float sumaErrores = 0;
        List<Float> demandasReales = new ArrayList<>();
        while (!fechaDesde.isAfter(fechaActual)) {
            System.out.println("Procesando mes: " + fechaDesde.getMonth());

            float demandaReal = 0;
            boolean tieneVentas = false;

            for (DTOVentas venta : ventas) {
                if (venta.getMes() == fechaDesde.getMonthValue()) {
                    demandaReal = venta.getCantidadVentas();
                    demandasReales.add(demandaReal);
                    tieneVentas = true;
                    System.out.println("Demanda real en mes " + fechaDesde.getMonth() + ": " + demandaReal);
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
                    System.out.println("Pronóstico: " + pronostico);
                    System.out.println("Error: " + err);
                }
            }

            fechaDesde = fechaDesde.plusMonths(1);
        }

        System.out.println("Error total: " + sumaErrores);

        DTOResultadoSimu resultadoSimu = new DTOResultadoSimu();
        resultadoSimu.setErrorObtenido(sumaErrores);

        System.out.println("**************************************");
        System.out.println("               ");
        return resultadoSimu;
    }
}
