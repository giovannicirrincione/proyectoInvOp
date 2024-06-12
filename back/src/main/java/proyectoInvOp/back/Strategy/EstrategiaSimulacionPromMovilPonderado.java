package proyectoInvOp.back.Strategy;

import proyectoInvOp.back.DTOS.DTOValores;
import proyectoInvOp.back.DTOS.DTOValoresOptimos;

import java.util.List;

public class EstrategiaSimulacionPromMovilPonderado implements EstrategiaSimulacion{
    @Override
    public DTOValoresOptimos simular(List<DTOValores> dtoValores) {
        System.out.println("esotoy simulando con MOVIL PONDERADO");
        return null;
    }
}
