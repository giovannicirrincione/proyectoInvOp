package proyectoInvOp.back.Services;

import proyectoInvOp.back.DTOS.DTOArticulo;
import proyectoInvOp.back.Entity.Articulo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ArticuloService extends BaseService<Articulo,Long> {
    Articulo saveArticulo(Articulo articulo) throws Exception;

    boolean bajaArticulo(Long id) throws Exception;

    List<DTOArticulo> listarArticulos() throws Exception;

    DTOArticulo listarArticuloById(Long id) throws Exception;

    int demandaHistorica(Long id, LocalDate fechaDesde, LocalDate fechaHasta) throws Exception;
}
