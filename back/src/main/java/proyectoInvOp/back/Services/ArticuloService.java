package proyectoInvOp.back.Services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import proyectoInvOp.back.DTOS.DTOArticulo;
import proyectoInvOp.back.Entity.Articulo;

import java.net.http.HttpResponse;
import java.util.List;

public interface ArticuloService extends BaseService<Articulo,Long> {
    Articulo saveArticulo(Articulo articulo) throws Exception;

    boolean bajaArticulo(Long id) throws Exception;

    List<DTOArticulo> listarArticulos() throws Exception;

    DTOArticulo listarArticuloById(Long id) throws Exception;
}
