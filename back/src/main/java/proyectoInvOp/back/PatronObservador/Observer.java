package proyectoInvOp.back.PatronObservador;

import proyectoInvOp.back.Entity.Articulo;

public interface Observer {
    void update(Articulo articulo, int cantidadVendida);
}
