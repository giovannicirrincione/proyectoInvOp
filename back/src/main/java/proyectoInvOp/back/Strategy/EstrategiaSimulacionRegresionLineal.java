package proyectoInvOp.back.Strategy;

import proyectoInvOp.back.DTOS.*;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

public class EstrategiaSimulacionRegresionLineal implements EstrategiaSimulacion{
    @Override
    public DTOResultadoSimu simular(List<DTOVentas> ventas, List<DTOParametroValor> parametros) {


        float mesesAtras = 0;

        for (DTOParametroValor param : parametros) {
            String nombre = param.getNombreParametro();
            if ("Periodos historicos".equals(nombre)) {
                mesesAtras = param.getValorParametro();
            }
        }

        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaDesde = fechaActual.minusMonths((long) mesesAtras);

        List<Integer> meses = new ArrayList<>();
        List<Float> demandas = new ArrayList<>();

        while (!fechaDesde.isAfter(fechaActual)) {

            for (DTOVentas venta : ventas) {
                if (venta.getFecha().getMonthValue() == fechaDesde.getMonthValue()) {
                    int mes = ventas.indexOf(venta);
                    float demanda = venta.getCantidadVentas();
                    meses.add(mes);
                    demandas.add(demanda);
                    break;
                }
            }

            fechaDesde = fechaDesde.plusMonths(1);
        }

        // Calcular los parámetros de la regresión lineal
        int n = meses.size();
        float sumaX = 0, sumaY = 0, sumaXY = 0, sumaX2 = 0;
        for (int i = 0; i < n; i++) {
            sumaX += meses.get(i);
            sumaY += demandas.get(i);
            sumaXY += meses.get(i) * demandas.get(i);
            sumaX2 += meses.get(i) * meses.get(i);
        }

        float pendiente = (n * sumaXY - sumaX * sumaY) / (n * sumaX2 - sumaX * sumaX);
        float interseccion = (sumaY - pendiente * sumaX) / n;


        float sumaErrores = 0;
        fechaDesde = LocalDate.now().minusMonths((long) mesesAtras);
        while (!fechaDesde.isAfter(fechaActual)) {
            int mesActual = ventas.indexOf(new DTOVentas(fechaDesde, 0));
            float pronostico = pendiente * mesActual + interseccion;
            for (DTOVentas venta : ventas) {
                if (venta.getFecha().getMonthValue() == fechaDesde.getMonthValue()) {
                    float demandaReal = venta.getCantidadVentas();
                    float error = Math.abs(demandaReal - pronostico);
                    sumaErrores += error;
                    break;
                }
            }
            fechaDesde = fechaDesde.plusMonths(1);
        }


        DTOResultadoSimu resultadoSimu = new DTOResultadoSimu();
        resultadoSimu.setErrorObtenido((double)sumaErrores);
        resultadoSimu.setNombreMetodo("RegresionLineal");

        System.out.println("******************");
        System.out.println("simule con REG LINEAL");
        System.out.println("obtuve" + resultadoSimu.getErrorObtenido());
        System.out.println("******************");

        return resultadoSimu;
    }
}
