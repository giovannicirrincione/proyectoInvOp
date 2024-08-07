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
        //Mes actual acomodado a la lista
        int mes = LocalDate.now().getMonthValue() - 1;

        double demanda = 0;
        int mesFuturo = 0;


        for (int i = 0; i < cantPeriodos; i++) {
            DetallePrediccion detallePrediccion = new DetallePrediccion();

            // Calcula el mes futuro con manejo de desbordamiento usando el operador módulo
            mesFuturo = (mes + i) % 12;

            // Calcula la demanda para el mes futuro
            demanda = indiceMensual[mesFuturo] * promGeneral;

            // Aquí puedes agregar la lógica para usar 'detallePrediccion'
            detallePrediccion.setValorPredecido(demanda);
            detallePrediccion.setMes(mesFuturo + 1);
            detallePrediccionList.add(detallePrediccion);
        }


        prediccionDemanda.setDetallePrediccions(detallePrediccionList);

        return prediccionDemanda;

    }
}
