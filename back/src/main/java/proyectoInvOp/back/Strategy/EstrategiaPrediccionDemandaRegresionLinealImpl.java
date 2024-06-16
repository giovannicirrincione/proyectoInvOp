package proyectoInvOp.back.Strategy;

import proyectoInvOp.back.DTOS.DTOResultadoSimu;
import proyectoInvOp.back.DTOS.DTOVentas;
import proyectoInvOp.back.Entity.PrediccionDemanda;

import java.util.ArrayList;
import java.util.List;

public class EstrategiaPrediccionDemandaRegresionLinealImpl implements EstrategiaPrediccionDemanda{

    @Override
    public PrediccionDemanda predecirDemanda(List<DTOVentas> ventas, int cantPeriodos, DTOResultadoSimu resultadoSimu) {
        List<Double> predicciones = new ArrayList<>();

        if (ventas.isEmpty()) {
            throw new IllegalArgumentException("La lista de ventas no puede estar vacía.");
        }

        int n = ventas.size();
        double sumX = 0, sumY = 0, sumXY = 0, sumX2 = 0;

        for (int i = 0; i < n; i++) {
            double x = i + 1; // El tiempo se considera como 1, 2, 3, ..., n
            double y = ventas.get(i).getCantidadVentas();
            sumX += x;
            sumY += y;
            sumXY += x * y;
            sumX2 += x * x;
        }

        // Cálculo de los coeficientes de la regresión lineal
        double b = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);
        double a = (sumY - b * sumX) / n;

        // Generar predicciones para los periodos solicitados
        for (int i = 0; i < cantPeriodos; i++) {
            double x = n + i + 1; // Continuar con el tiempo
            double y = a + b * x;
            predicciones.add(y);
        }

        return new PrediccionDemanda(predicciones);
    }
}
