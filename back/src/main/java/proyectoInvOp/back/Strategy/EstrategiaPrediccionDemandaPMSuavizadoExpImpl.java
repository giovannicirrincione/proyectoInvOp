package proyectoInvOp.back.Strategy;

import proyectoInvOp.back.DTOS.DTOResultadoSimu;
import proyectoInvOp.back.DTOS.DTOVentas;
import proyectoInvOp.back.Entity.PrediccionDemanda;

import java.util.ArrayList;
import java.util.List;

public class EstrategiaPrediccionDemandaPMSuavizadoExpImpl implements   EstrategiaPrediccionDemanda{
    private double alfa; // Coeficiente de suavización
    private double prediccionRaiz; // Valor de la predicción raíz

    public EstrategiaPrediccionDemandaPMSuavizadoExpImpl() {
        // Valores predeterminados
        this.alfa = 0.5;
        this.prediccionRaiz = 0.0; // Podría ser un valor de inicio basado en la primera observación de ventas
    }

    public void setAlfa(double alfa) {
        if (alfa < 0 || alfa > 1) {
            throw new IllegalArgumentException("El valor de alfa debe estar entre 0 y 1.");
        }
        this.alfa = alfa;
    }

    public void setPrediccionRaiz(double prediccionRaiz) {
        this.prediccionRaiz = prediccionRaiz;
    }
    @Override
    public PrediccionDemanda predecirDemanda(List<DTOVentas> ventas, int cantPeriodos, DTOResultadoSimu resultadoSimu) {
        List<Double> predicciones = new ArrayList<>();

        if (ventas.isEmpty()) {
            return new PrediccionDemanda(predicciones);
        }

        // Inicializar el valor suavizado con la primera observación
        double valorSuavizado = ventas.get(0).getCantidadVentas();

        // Calcular el valor suavizado para cada punto de datos
        for (DTOVentas venta : ventas) {
            valorSuavizado = alfa * venta.getCantidadVentas() + (1 - alfa) * valorSuavizado;
        }

        // Generar predicciones utilizando el último valor suavizado
        for (int i = 0; i < cantPeriodos; i++) {
            predicciones.add(valorSuavizado);
        }

        return new PrediccionDemanda(predicciones);
    }
}
