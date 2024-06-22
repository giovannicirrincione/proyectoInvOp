package proyectoInvOp.back.Factory;

import proyectoInvOp.back.Entity.MetodoPrediccion;
import proyectoInvOp.back.Strategy.*;

public class FactorySimulacionSeleccionParametros {

    private static FactorySimulacionSeleccionParametros instancia;


    public static FactorySimulacionSeleccionParametros getInstancia(){
        // Si la instancia aún no ha sido creada, crearla
        if (instancia == null) {
            instancia = new FactorySimulacionSeleccionParametros();
        }
        // Devolver la instancia existente o recién creada
        return instancia;
    }


    public EstrategiaSimulacion obtenerEstrategiaSimulacion(MetodoPrediccion metodoPrediccion){

        String nombrePredicion = metodoPrediccion.getNombre();

        switch (nombrePredicion){

            case "PMSuavizadoExp":
                EstrategiaSimulacion estrategiaSimulacion = new EstrategiaSimulacionPMSuavizadoExp();
                return estrategiaSimulacion;
            case "PromMovilPonderado":
                EstrategiaSimulacion estrategiaSimulacion1 = new EstrategiaSimulacionPromMovilPonderado();
                return estrategiaSimulacion1;
            case "RegresionLineal":
                EstrategiaSimulacion estrategiaSimulacion2 = new EstrategiaSimulacionRegresionLineal();
                return estrategiaSimulacion2;
            case "Estacionalidad":
                EstrategiaSimulacion estrategiaSimulacion3 = new EstrategiaSimulacionEstacionalidad();
                return estrategiaSimulacion3;
            default:
                return null;
        }
    }
}
