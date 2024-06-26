package proyectoInvOp.back.Strategy;

import proyectoInvOp.back.DTOS.*;

import java.util.ArrayList;
import java.util.List;

public class EstrategiaSimulacionEstacionalidad implements EstrategiaSimulacion{
    @Override
    public DTOResultadoSimu simular(List<DTOVentas> ventas, List<DTOParametroValor> parametros) {

        DTOResultadoSimu resultadoSimu = new DTOResultadoSimu();

        // Paso 1: Calcular el promedio general
        double promGeneral = 0;
        double totalVentas = 0;

        for (DTOVentas venta : ventas) {
            totalVentas += venta.getCantidadVentas();
        }

        promGeneral = totalVentas / ventas.size();

        // Paso 2: Calcular las ventas mensuales para los primeros 24 meses
        int[] ventaMensual = new int[12]; // Cambiado a 12 meses

        for (int i = 0; i < 24; i++) {
            int mesVenta = ventas.get(i).getFecha().getMonthValue() - 1;
            int cantVendida = ventas.get(i).getCantidadVentas();
            ventaMensual[mesVenta] += cantVendida;
        }

        // Paso 3: Calcular el índice mensual para los primeros 24 meses
        double[] indiceMensual = new double[12]; // Cambiado a 12 meses

        for (int i = 0; i < indiceMensual.length; i++) {
            indiceMensual[i] = (ventaMensual[i] / 2.0) / promGeneral; // Cambiado a 2.0 para dividir correctamente
        }

        // Paso 4: Predecir las ventas para los meses 25 a 36
        List<Double> predicciones = new ArrayList<>();
        for (int i = 24; i < 36; i++) {
            int mesIndice = (i % 12); // Use 12 months period
            double prediccion = promGeneral * indiceMensual[mesIndice];
            predicciones.add(prediccion);
        }


        // Paso 5: Comparar las predicciones con los valores reales y calcular el error absoluto medio
        double error = 0;
        for (int i = 24; i < 35; i++) {
            int valorReal = ventas.get(i).getCantidadVentas();
            double valorPredicho = predicciones.get(i - 24);
            error += Math.abs(valorPredicho - valorReal);
        }


        resultadoSimu.setErrorObtenido(error);

        resultadoSimu.setNombreMetodo("Estacionalidad");
        System.out.println("******************");
        System.out.println("******************");
        System.out.println("simule con estacionalidad");
        System.out.println("obtuve" + resultadoSimu.getErrorObtenido());
        System.out.println("******************");
        return resultadoSimu;
    }
}
