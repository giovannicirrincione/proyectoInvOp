package proyectoInvOp.back.Strategy;

import proyectoInvOp.back.DTOS.*;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

public class EstrategiaSimulacionRegresionLineal implements EstrategiaSimulacion{
    @Override
    public DTOResultadoSimu simular(List<DTOVentas> ventas, List<DTOParametroValor> parametros) {

        System.out.println("               ");
        System.out.println("******SIMULACION REGRESION LINEAL*********");
        for (DTOParametroValor par : parametros) {
            System.out.println("nombre parametro: " + par.getNombreParametro() + ", valor parametro: " + par.getValorParametro());
        }

        float mesesAtras = 0;

        for (DTOParametroValor param : parametros) {
            String nombre = param.getNombreParametro();
            if ("Periodos historicos".equals(nombre)) {
                mesesAtras = param.getValorParametro();
            }
        }

        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaDesde = fechaActual.minusMonths((long) mesesAtras);
        System.out.println(fechaActual);
        System.out.println(fechaDesde);

        List<Integer> meses = new ArrayList<>();
        List<Float> demandas = new ArrayList<>();

        while (!fechaDesde.isAfter(fechaActual)) {
            System.out.println("Procesando mes: " + fechaDesde.getMonth());

            for (DTOVentas venta : ventas) {
                if (venta.getMes() == fechaDesde.getMonthValue()) {
                    int mes = ventas.indexOf(venta);
                    float demanda = venta.getCantidadVentas();
                    meses.add(mes);
                    demandas.add(demanda);
                    System.out.println("Demanda real en mes " + fechaDesde.getMonth() + ": " + demanda);
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

        System.out.println("Pendiente (m): " + pendiente);
        System.out.println("Intersección (b): " + interseccion);

        float sumaErrores = 0;
        fechaDesde = LocalDate.now().minusMonths((long) mesesAtras);
        while (!fechaDesde.isAfter(fechaActual)) {
            int mesActual = ventas.indexOf(new DTOVentas(fechaDesde.getMonthValue(), 0));
            float pronostico = pendiente * mesActual + interseccion;
            for (DTOVentas venta : ventas) {
                if (venta.getMes() == fechaDesde.getMonthValue()) {
                    float demandaReal = venta.getCantidadVentas();
                    float error = Math.abs(demandaReal - pronostico);
                    sumaErrores += error;
                    System.out.println("Pronóstico: " + pronostico);
                    System.out.println("Error: " + error);
                    break;
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
