package proyectoInvOp.back.PatronObservador;

import proyectoInvOp.back.Entity.Articulo;

public interface Observer {
    void updateVenta(Articulo articulo, int cantidadVendida);

    void updateCompra(Articulo articulo, int cantidadAdquirida);


}
