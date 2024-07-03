package proyectoInvOp.back.Strategy;

import proyectoInvOp.back.DTOS.DTOResultadoSimu;
import proyectoInvOp.back.DTOS.DTOVentas;
import proyectoInvOp.back.Entity.DetallePrediccion;
import proyectoInvOp.back.Entity.PrediccionDemanda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EstrategiaPrediccionDemandaRegresionLinealImpl implements EstrategiaPrediccionDemanda{

    @Override
    public PrediccionDemanda predecirDemanda(List<DTOVentas> ventas, int cantPeriodos, DTOResultadoSimu resultadoSimu) {


        int n = ventas.size();

        double sumX = 0, sumY = 0, sumXY = 0, sumX2 = 0;

        for (DTOVentas venta : ventas) {
            int x = venta.getFecha().getMonthValue();
            double y = venta.getCantidadVentas();
            //suma de los meses.
            sumX += x;
            //suma de las ventas.
            sumY += y;
            //suma del producto de meses y ventas.
            sumXY += x * y;
            //suma del cuadrado de los meses.
            sumX2 += x * x;
        }
        //la pendiente de la línea de regresión.
        double slope = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);
        //la intersección de la línea de regresión con el eje y.
        double intercept = (sumY - slope * sumX) / n;

        //Seteamos la prediccion futura

        List<DetallePrediccion> detallePrediccionList = new ArrayList<>();

        PrediccionDemanda prediccionDemanda = new PrediccionDemanda();

        int mes = LocalDate.now().getMonthValue();

        for (int i = 0; i < cantPeriodos; i++) {
            DetallePrediccion detallePrediccion = new DetallePrediccion();
            int mesFuturo = mes + i;
            double ventasPredecidas = slope * (mesFuturo+i) + intercept;

            detallePrediccion.setValorPredecido(ventasPredecidas);
            detallePrediccion.setMes(mesFuturo);
            detallePrediccionList.add(detallePrediccion);
        }

        prediccionDemanda.setDetallePrediccions(detallePrediccionList);

        return prediccionDemanda;
    }
}
