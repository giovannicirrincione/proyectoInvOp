package proyectoInvOp.back.Strategy;

import proyectoInvOp.back.DTOS.DTOValores;
import proyectoInvOp.back.DTOS.DTOValoresOptimos;

import java.util.List;

public interface EstrategiaSimulacion {
    public DTOValoresOptimos simular(List<DTOValores> dtoValores);

}
