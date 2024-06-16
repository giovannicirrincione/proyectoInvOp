package proyectoInvOp.back.Strategy;

import proyectoInvOp.back.DTOS.DTOResultadoSimu;
import proyectoInvOp.back.DTOS.DTOVentas;
import proyectoInvOp.back.Entity.PrediccionDemanda;

import java.util.List;

public class EstrategiaPrediccionDemandaPMSuavizadoExpImpl implements   EstrategiaPrediccionDemanda{

    @Override
    public PrediccionDemanda predecirDemanda(List<DTOVentas> ventas, int cantPeriodos, DTOResultadoSimu resultadoSimu) {
        //codigo de la prediccion
        return null;
    }
}
