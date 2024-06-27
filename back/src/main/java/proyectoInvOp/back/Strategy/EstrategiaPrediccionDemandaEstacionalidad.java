package proyectoInvOp.back.Strategy;

import proyectoInvOp.back.DTOS.DTOResultadoSimu;
import proyectoInvOp.back.DTOS.DTOVentas;
import proyectoInvOp.back.Entity.DetallePrediccion;
import proyectoInvOp.back.Entity.PrediccionDemanda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EstrategiaPrediccionDemandaEstacionalidad implements EstrategiaPrediccionDemanda{
    @Override
    public PrediccionDemanda predecirDemanda(List<DTOVentas> ventas, int cantPeriodos, DTOResultadoSimu resultadoSimu) {
        List<Double> predicciones = new ArrayList<>();

        double promGeneral = 0;

        double totalVentas = 0;

        for (DTOVentas venta : ventas){

            totalVentas = totalVentas + venta.getCantidadVentas();

        }


        promGeneral = totalVentas/ventas.size();


        int[] ventaMensual = new int[12];

        for (DTOVentas ventas1 : ventas){

            int mesVenta = ventas1.getFecha().getMonthValue()-1;

            int cantVendida = ventas1.getCantidadVentas();

            //agregamos en el indice del mes la cantidad vendida
            ventaMensual[mesVenta] = ventaMensual[mesVenta] + cantVendida;
        }

        double[] indiceMensual = new double[12];

        for (int i = 0; i < indiceMensual.length; i++){

            indiceMensual[i] = (ventaMensual[i]/3.0)/ promGeneral;

        }


        List<DetallePrediccion> detallePrediccionList = new ArrayList<>();

        PrediccionDemanda prediccionDemanda = new PrediccionDemanda();

        int mes = LocalDate.now().getMonthValue();

        // Generar predicciones
        for (int i = 0; i < cantPeriodos; i++) {
            DetallePrediccion detallePrediccion = new DetallePrediccion();
            int mesFuturo = mes + i;
            double demanda = (indiceMensual[mesFuturo]*promGeneral);
            detallePrediccion.setValorPredecido(demanda);
            detallePrediccion.setMes(mesFuturo);
            detallePrediccionList.add(detallePrediccion);
        }
        prediccionDemanda.setDetallePrediccions(detallePrediccionList);

        return prediccionDemanda;

    }
}
