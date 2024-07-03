package proyectoInvOp.back.Strategy;

import proyectoInvOp.back.DTOS.DTOResultadoSimu;
import proyectoInvOp.back.DTOS.DTOVentas;
import proyectoInvOp.back.Entity.DetallePrediccion;
import proyectoInvOp.back.Entity.PrediccionDemanda;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EstrategiaPrediccionDemandaPMSuavizadoExpImpl implements   EstrategiaPrediccionDemanda {
    @Override
    public PrediccionDemanda predecirDemanda(List<DTOVentas> ventas, int cantPeriodos, DTOResultadoSimu resultadoSimu) {
        System.out.println("Toy haciendo la prediccion de suavizado EXP");

        //creamos el objeto prediccion
        PrediccionDemanda prediccionDemanda = new PrediccionDemanda();
        //Obtenemos el alpha a utilizar
        float alfa = resultadoSimu.getValorParametro();

        //obtenemos la venta del ultimos mes
        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();

        // Obtener el mes anterior
        LocalDate fechaMesAnterior = fechaActual.minusMonths(1);

        // Obtener el número del mes anterior
        int mesAnterior = fechaMesAnterior.getMonthValue();

        //Obtenemos la cantidad vendida el ultimo mes
        int cant = 0;
        //Asigno el valor del ultimo mes
        for (DTOVentas Cadaventa : ventas) {
            if (Cadaventa.getFecha().getMonthValue() == mesAnterior) {
                cant = Cadaventa.getCantidadVentas();
                break;
            }
        }

        List<Double> predicciones = new ArrayList<>();

        // Si hay ventas históricas, aplicar el suavizado exponencial simple
        if (!ventas.isEmpty()) {
            // Inicializar la predicción actual con la primera venta histórica
            double prediccionActual = ventas.get(0).getCantidadVentas();

            // Aplicar suavizado exponencial a los datos históricos
            for (DTOVentas venta : ventas) {
                prediccionActual = alfa * venta.getCantidadVentas() + (1 - alfa) * prediccionActual;
            }

            List<DetallePrediccion> detallePrediccionList = new ArrayList<>();
            // Realizar predicciones para los periodos futuros
            for (int i = 0; i < cantPeriodos; i++) {
                predicciones.add(prediccionActual);
                DetallePrediccion detallePrediccion = new DetallePrediccion();

                LocalDate añoActual = LocalDate.now();
                int mes = añoActual.getMonthValue();

                detallePrediccion.setMes(mes + i );
                detallePrediccion.setValorPredecido(prediccionActual);
                detallePrediccionList.add(detallePrediccion);

            }

            prediccionDemanda.setDetallePrediccions(detallePrediccionList);

            return prediccionDemanda;

        }else {
            return prediccionDemanda;
        }
    }
}
