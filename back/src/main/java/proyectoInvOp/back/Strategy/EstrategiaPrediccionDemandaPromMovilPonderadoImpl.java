package proyectoInvOp.back.Strategy;

import proyectoInvOp.back.DTOS.DTOResultadoSimu;
import proyectoInvOp.back.DTOS.DTOVentas;
import proyectoInvOp.back.Entity.PrediccionDemanda;

import java.util.ArrayList;
import java.util.List;

public class EstrategiaPrediccionDemandaPromMovilPonderadoImpl implements EstrategiaPrediccionDemanda{
    private int periodosMoviles;
    private double[] pesos;

    public EstrategiaPrediccionDemandaPromMovilPonderadoImpl() {
        // Valores predeterminados
        this.periodosMoviles = 3;
        this.pesos = new double[]{0.6, 0.3, 0.1};
    }

    public void setPeriodosMoviles(int periodosMoviles) {
        this.periodosMoviles = periodosMoviles;
        this.pesos = new double[periodosMoviles];
        double pesoTotal = 0;
        for (int i = 0; i < periodosMoviles; i++) {
            this.pesos[i] = 1.0 / (i + 1);
            pesoTotal += this.pesos[i];
        }
        for (int i = 0; i < periodosMoviles; i++) {
            this.pesos[i] /= pesoTotal;
        }
    }

    public void setPesos(double[] pesos) {
        if (pesos.length != this.periodosMoviles) {
            throw new IllegalArgumentException("La cantidad de pesos debe coincidir con la cantidad de periodos móviles.");
        }
        this.pesos = pesos;
    }

    @Override
    public PrediccionDemanda predecirDemanda(List<DTOVentas> ventas, int cantPeriodos, DTOResultadoSimu resultadoSimu) {
        List<Double> predicciones = new ArrayList<>();

        if (ventas.size() < periodosMoviles) {
            throw new IllegalArgumentException("No hay suficientes datos para calcular el promedio móvil ponderado.");
        }

        // Calcular el promedio móvil ponderado para los datos históricos
        for (int i = periodosMoviles - 1; i < ventas.size(); i++) {
            double sumaPonderada = 0.0;
            for (int j = 0; j < periodosMoviles; j++) {
                sumaPonderada += ventas.get(i - j).getCantidadVentas() * pesos[j];
            }
            predicciones.add(sumaPonderada);
        }

        // Generar predicciones para los periodos futuros utilizando el último valor calculado
        double ultimaPrediccion = predicciones.get(predicciones.size() - 1);
        for (int i = 0; i < cantPeriodos; i++) {
            predicciones.add(ultimaPrediccion);
        }

        return new PrediccionDemanda(predicciones);
    }
}
