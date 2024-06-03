package proyectoInvOp.back.Services;

import proyectoInvOp.back.Entity.Proveedor;

import java.util.List;

public interface ProveedorService extends BaseService<Proveedor,Long> {

    List<Proveedor> getProveedoresArticulo(Long id) throws Exception;
}
