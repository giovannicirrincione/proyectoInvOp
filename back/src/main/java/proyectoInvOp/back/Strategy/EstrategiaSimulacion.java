package proyectoInvOp.back.Strategy;

import proyectoInvOp.back.DTOS.*;

import java.util.List;

public interface EstrategiaSimulacion {
    public DTOResultadoSimu simular(List<DTOVentas> ventas, List<DTOParametroValor> parametros);

}
