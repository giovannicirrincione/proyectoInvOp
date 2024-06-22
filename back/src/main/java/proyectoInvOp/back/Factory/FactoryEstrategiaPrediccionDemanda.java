package proyectoInvOp.back.Factory;

import proyectoInvOp.back.Entity.MetodoPrediccion;
import proyectoInvOp.back.Strategy.*;

public class FactoryEstrategiaPrediccionDemanda {

    private static FactoryEstrategiaPrediccionDemanda instancia;


    public static FactoryEstrategiaPrediccionDemanda getInstancia(){
        // Si la instancia aún no ha sido creada, crearla
        if (instancia == null) {
            instancia = new FactoryEstrategiaPrediccionDemanda();
        }
        // Devolver la instancia existente o recién creada
        return instancia;
    }

    public EstrategiaPrediccionDemanda obtenerEstrategiaPrediccionDemanda(String nombrePrediccion){



        switch (nombrePrediccion){

            case "PMSuavizadoExp":
                EstrategiaPrediccionDemanda estrategiaPrediccionDemanda = new EstrategiaPrediccionDemandaPMSuavizadoExpImpl();
                return estrategiaPrediccionDemanda;
            case "PromMovilPonderado":
                EstrategiaPrediccionDemanda estrategiaPrediccionDemanda1 = new EstrategiaPrediccionDemandaPromMovilPonderadoImpl();
                return estrategiaPrediccionDemanda1;
            case "RegresionLineal":
                EstrategiaPrediccionDemanda estrategiaPrediccionDemanda2 = new EstrategiaPrediccionDemandaRegresionLinealImpl();
                return estrategiaPrediccionDemanda2;
            case "Estacionalidad":
                EstrategiaPrediccionDemanda estrategiaPrediccionDemanda3 = new EstrategiaPrediccionDemandaEstacionalidad();
                return estrategiaPrediccionDemanda3;
            default:
                return null;
        }
    }
}

