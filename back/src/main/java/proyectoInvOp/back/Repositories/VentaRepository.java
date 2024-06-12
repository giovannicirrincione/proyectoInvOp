package proyectoInvOp.back.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyectoInvOp.back.DTOS.DTOValores;
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

    @Query("SELECT new com.example.DTOValores(MONTH(v.fechaVenta), YEAR(v.fechaVenta), SUM(dv.cantidad)) " +
            "FROM Venta v JOIN v.detalleVentas dv " +
            "WHERE dv.articulo.id = :articuloId AND v.fechaVenta >= :fechaInicio " +
            "GROUP BY YEAR(v.fechaVenta), MONTH(v.fechaVenta) " +
            "ORDER BY YEAR(v.fechaVenta) DESC, MONTH(v.fechaVenta) DESC")
    List<DTOValores> findCantidadVendidaPorMes(@Param("articuloId") Long articuloId,
                                               @Param("fechaInicio") LocalDate fechaInicio);
}

