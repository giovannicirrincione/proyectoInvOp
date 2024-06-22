package proyectoInvOp.back.Strategy;

import proyectoInvOp.back.DTOS.*;

import java.util.ArrayList;
import java.util.List;

public class EstrategiaSimulacionPMSuavizadoExp implements EstrategiaSimulacion {
    @Override
    public DTOResultadoSimu simular(List<DTOVentas> ventas, List<DTOParametroValor> parametros) {

        for ( DTOParametroValor param : parametros) {
            System.out.println(param.getNombreParametro());
        }

        return null;
    }

}
