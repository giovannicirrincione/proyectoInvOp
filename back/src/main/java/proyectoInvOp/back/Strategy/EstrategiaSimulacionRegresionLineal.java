package proyectoInvOp.back.Strategy;

import proyectoInvOp.back.DTOS.*;

import java.util.Comparator;
import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

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
        LocalDate fechaDesde = fechaActual.minusMonths(24);
        LocalDate fechaHasta = fechaActual.minusMonths(12);

        // Filtrar y ordenar las ventas de los meses 12 al 24
        LocalDate finalFechaDesde = fechaDesde;
        LocalDate finalFechaHasta = fechaHasta;
        List<DTOVentas> ventasMeses12a24 = ventas.stream()
                .filter(venta -> !venta.getFecha().isBefore(finalFechaDesde) && venta.getFecha().isBefore(finalFechaHasta))
                .sorted(Comparator.comparing(DTOVentas::getFecha))
                .collect(Collectors.toList());

        List<Integer> meses = new ArrayList<>();
        List<Float> demandas = new ArrayList<>();

        while (!fechaDesde.isAfter(fechaHasta)) {
            for (DTOVentas venta : ventasMeses12a24) {
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

        // Ahora simulamos y comparamos los meses 24 al 36
        float sumaErrores = 0;
        fechaDesde = fechaHasta;
        fechaHasta = fechaActual;

        while (!fechaDesde.isAfter(fechaHasta)) {
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
