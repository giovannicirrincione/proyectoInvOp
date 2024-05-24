package proyectoInvOp.back.Repositories;

import org.springframework.stereotype.Repository;
import proyectoInvOp.back.Controller.BaseControllerImpl;
import proyectoInvOp.back.Entity.Proveedor;

@Repository
public interface ProveedorRepository extends BaseRepository<Proveedor, Long> {
}
