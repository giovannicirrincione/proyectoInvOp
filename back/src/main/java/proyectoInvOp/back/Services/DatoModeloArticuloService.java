package proyectoInvOp.back.Services;

import proyectoInvOp.back.Entity.Articulo;
import proyectoInvOp.back.Entity.DatoModeloArticulo;

import java.util.List;

public interface DatoModeloArticuloService extends BaseService<DatoModeloArticulo,Long> {

    List<DatoModeloArticulo> datosModeloByArticulo(Long id) throws Exception;
}
