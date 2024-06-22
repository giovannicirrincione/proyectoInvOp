package proyectoInvOp.back.Strategy;

import proyectoInvOp.back.DTOS.DTOResultadoSimu;
import proyectoInvOp.back.DTOS.DTOVentas;
import proyectoInvOp.back.Entity.PrediccionDemanda;

import java.util.ArrayList;
import java.util.List;

public class EstrategiaPrediccionDemandaEstacionalidad implements EstrategiaPrediccionDemanda{
    @Override
    public PrediccionDemanda predecirDemanda(List<DTOVentas> ventas, int cantPeriodos, DTOResultadoSimu resultadoSimu) {
        List<Double> predicciones = new ArrayList<>();
        int estacionalidad = 12; // Por ejemplo, estacionalidad anual (12 meses)

        // Calcular promedios estacionales
        double[] promediosEstacionales = new double[estacionalidad];
        int[] conteosEstacionales = new int[estacionalidad];

        for (int i = 0; i < ventas.size(); i++) {
            int indiceEstacional = i % estacionalidad;
            promediosEstacionales[indiceEstacional] += ventas.get(i).getCantidadVentas();
            conteosEstacionales[indiceEstacional]++;
        }

        for (int i = 0; i < estacionalidad; i++) {
            if (conteosEstacionales[i] > 0) {
                promediosEstacionales[i] /= conteosEstacionales[i];
            }
        }

        // Generar predicciones
        for (int i = 0; i < cantPeriodos; i++) {
            int indiceEstacional = (ventas.size() + i) % estacionalidad;
            predicciones.add(promediosEstacionales[indiceEstacional]);
        }

        return new PrediccionDemanda(predicciones);
    }
}
