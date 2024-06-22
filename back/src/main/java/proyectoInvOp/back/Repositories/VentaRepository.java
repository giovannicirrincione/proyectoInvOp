package proyectoInvOp.back.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyectoInvOp.back.DTOS.DTOVentas;
import proyectoInvOp.back.Entity.Venta;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VentaRepository extends BaseRepository<Venta,Long>{

    @Query("SELECT DISTINCT v FROM Venta v " +
            "JOIN v.detalleVentas dv " +
            "WHERE dv.articulo.id = :articuloId " +
            "AND v.fechaVenta BETWEEN :fechaDesde AND :fechaHasta")
    List<Venta> ventasDeUnProducto(@Param("articuloId") Long articuloId,
                                   @Param("fechaDesde") LocalDate fechaDesde,
                                   @Param("fechaHasta") LocalDate fechaHasta);

    @Query("SELECT new proyectoInvOp.back.DTOS.DTOVentas(MONTH(v.fechaVenta), SUM(dv.cantidad)) " +
            "FROM Venta v " +
            "JOIN v.detalleVentas dv " +
            "WHERE dv.articulo.id = :idArticulo " +
            "AND v.fechaVenta BETWEEN :fechaInicio AND :fechaFin " +
            "GROUP BY MONTH(v.fechaVenta) " +
            "ORDER BY MONTH(v.fechaVenta)")
    List<DTOVentas> findVentasMensualesByArticulo(@Param("idArticulo") Long idArticulo,
                                                  @Param("fechaInicio") LocalDate fechaInicio,
                                                  @Param("fechaFin") LocalDate fechaFin);

}

