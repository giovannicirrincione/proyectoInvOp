package proyectoInvOp.back.Services;

import proyectoInvOp.back.Entity.Venta;

public interface VentaService extends BaseService<Venta, Long> {
    Venta saveVenta(Venta venta) throws Exception;

    String bajaVenta(Long id) throws Exception;
}
