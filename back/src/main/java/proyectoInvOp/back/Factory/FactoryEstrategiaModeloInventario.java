package proyectoInvOp.back.Factory;

import proyectoInvOp.back.Entity.ModeloInventario;
import proyectoInvOp.back.Strategy.EstrategiaModeloInventario;
import proyectoInvOp.back.Strategy.EstrategiaModeloInventarioIntervaloFijo;
import proyectoInvOp.back.Strategy.EstrategiaModeloInventarioLoteFijo;

public class FactoryEstrategiaModeloInventario {

    private static FactoryEstrategiaModeloInventario instancia;

    public static FactoryEstrategiaModeloInventario getInstance() {
        if (instancia == null) {
            instancia = new FactoryEstrategiaModeloInventario();
        }
        return instancia;
    }

    public EstrategiaModeloInventario obtenerEstrategiaModeloInventario(ModeloInventario modeloInventario) {
        String nombreModelo = modeloInventario.getNombre();

        switch (nombreModelo) {
            case "ModeloLoteFijo":
                EstrategiaModeloInventario estrategiaModeloInventario = new EstrategiaModeloInventarioLoteFijo();
                return estrategiaModeloInventario;
            case "ModeloIntervaloFijo":
                EstrategiaModeloInventario estrategiaModeloInventario2 = new EstrategiaModeloInventarioIntervaloFijo();
                return estrategiaModeloInventario2;
            default:
                return null;
        }
    }
}