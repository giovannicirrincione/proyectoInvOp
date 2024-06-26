package proyectoInvOp.back.Strategy;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import proyectoInvOp.back.DTOS.DTOResultadoSimu;
import proyectoInvOp.back.DTOS.DTOVentas;
import proyectoInvOp.back.Entity.DetallePrediccion;
import proyectoInvOp.back.Entity.ParametroEspecifico;
import proyectoInvOp.back.Entity.PrediccionDemanda;
import proyectoInvOp.back.Repositories.ParametrosEspecificosRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class EstrategiaPrediccionDemandaPromMovilPonderadoImpl implements EstrategiaPrediccionDemanda{
    @Autowired
    ParametrosEspecificosRepository parametrosEspecificosRepository;

    @Override
    public PrediccionDemanda predecirDemanda(List<DTOVentas> ventas, int cantPeriodos, DTOResultadoSimu resultadoSimu) {


        //ordeno la lista de mas viejo a mas nuevos
        ventas.sort(Comparator.comparing(DTOVentas::getFecha));

        //Dejamos solo las ventas de los ultimos 12 meses
        List<DTOVentas> ventasUltimos12Meses = ventas.stream()
                .filter(venta -> venta.getFecha().isAfter(LocalDate.now().minusMonths(12)))
                .sorted(Comparator.comparing(DTOVentas::getFecha))
                .collect(Collectors.toList());

        //almacenamos la predicciones
        List<Integer> predicciones = new ArrayList<>();

        //obtenemos los parametros usados en la simulacion
        List<Float> valoresPesoMensual = resultadoSimu.getValoresParametros();

        //oredenamos los pesos
        Collections.sort(valoresPesoMensual);

        //guardamos las predicciones
        List<Integer> valoresAuxiliares = new ArrayList<>();

        //Llenamos el valoresAuxiliales con las catidad vendidas
        for (DTOVentas ventas1 : ventasUltimos12Meses){
            Integer valor = ventas1.getCantidadVentas();
            valoresAuxiliares.add(valor);
        }

        //Calculo las predicciones
        for (int i = 0; i < cantPeriodos; i++){
            Float valorPredecido = 0.0f;

            for (int j = 0; j<valoresAuxiliares.size();j++){


                valorPredecido = valorPredecido + valoresAuxiliares.get(j) * valoresPesoMensual.get(j);

            }
            valoresAuxiliares.remove(0);
            valoresAuxiliares.add(valorPredecido.intValue());
            predicciones.add(valorPredecido.intValue());

        }


        List<DetallePrediccion> detallePrediccionList = new ArrayList<>();

        PrediccionDemanda prediccionDemanda = new PrediccionDemanda();

        int mes = LocalDate.now().getMonthValue();


        for (int i=0; i<predicciones.size(); i++){
            DetallePrediccion detallePrediccion = new DetallePrediccion();
            Integer valorPred = predicciones.get(i);
            detallePrediccion.setValorPredecido(Double.valueOf(valorPred));
            detallePrediccion.setMes(mes + i);
            detallePrediccionList.add(detallePrediccion);
        }
        prediccionDemanda.setDetallePrediccions(detallePrediccionList);

        return prediccionDemanda;
    }
}
