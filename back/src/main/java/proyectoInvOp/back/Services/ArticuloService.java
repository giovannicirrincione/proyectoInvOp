package proyectoInvOp.back.Services;

import jakarta.persistence.Id;
import proyectoInvOp.back.DTOArticulo;
import proyectoInvOp.back.Entity.Articulo;

import java.time.LocalDate;
import java.util.List;

public interface ArticuloService extends BaseService<Articulo,Long> {
    Articulo saveArticulo(Articulo articulo) throws Exception;

    String bajaArticulo(Long id) throws Exception;

    List<DTOArticulo> listarArticulos() throws Exception;

    DTOArticulo listarArticuloById(Long id) throws Exception;
}
