package proyectoInvOp.back.Strategy;

import proyectoInvOp.back.DTOS.*;

import java.util.List;

public class EstrategiaSimulacionRegresionLineal implements EstrategiaSimulacion{

    @Override
    public DTOResultadoSimu simular(List<DTOVentas> ventas, List<DTOParametroValor> parametros) {
        System.out.println("esotoy simulando con REGRECION LINEAL");
        return null;
    }
}
