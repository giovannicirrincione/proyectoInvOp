package proyectoInvOp.back.Services;

import jakarta.persistence.Id;
import proyectoInvOp.back.Entity.Articulo;

import java.time.LocalDate;

public interface ArticuloService extends BaseService<Articulo,Long> {
    Articulo saveArticulo(Articulo articulo) throws Exception;

    String bajaArticulo(Long id) throws Exception;
}
