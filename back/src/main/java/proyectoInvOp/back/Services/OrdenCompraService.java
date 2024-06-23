package proyectoInvOp.back.Services;

import proyectoInvOp.back.Entity.EstadoOrdenCompra;
import proyectoInvOp.back.Entity.OrdenCompra;

public interface OrdenCompraService extends BaseService<OrdenCompra,Long>{

    boolean cambioEstado(OrdenCompra ordenCompra, EstadoOrdenCompra estadoOrdenCompra) throws Exception;
}
