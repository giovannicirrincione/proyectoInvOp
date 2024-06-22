package proyectoInvOp.back.Strategy;

import proyectoInvOp.back.DTOS.*;

import java.util.List;

public class EstrategiaSimulacionPromMovilPonderado implements EstrategiaSimulacion{
    @Override
    public DTOResultadoSimu simular(List<DTOVentas> ventas, List<DTOParametroValor> parametros) {
        System.out.println("esotoy simulando con MOVIL PONDERADO");
        return null;
    }
}
