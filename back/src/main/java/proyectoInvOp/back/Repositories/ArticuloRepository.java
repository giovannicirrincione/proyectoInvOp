package proyectoInvOp.back.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyectoInvOp.back.DTOArticulo;
import proyectoInvOp.back.Entity.Articulo;

import java.util.List;

@Repository
public interface ArticuloRepository extends BaseRepository<Articulo,Long>{

    @Query("SELECT a.id as id, a.nombre as nombre, a.stockActual as stockActual, " +
            "COALESCE(MAX(CASE WHEN dma.nombreDato = 'Lote Optimo' THEN adma.valorDato ELSE 0 END), 0) as valorLoteOptimo, " +
            "COALESCE(MAX(CASE WHEN dma.nombreDato = 'Punto Pedido' THEN adma.valorDato ELSE 0 END), 0) as valorPuntoPedido, " +
            "COALESCE(MAX(CASE WHEN dma.nombreDato = 'Stock Seguridad' THEN adma.valorDato ELSE 0 END), 0) as stockSeguridad " +
            "FROM Articulo a " +
            "LEFT JOIN ArticuloDatoModeloArticulo adma ON a.id = adma.articulo.id " +
            "LEFT JOIN DatoModeloArticulo dma ON adma.datoModeloArticulo.id = dma.id " +
            "WHERE a.fechaBaja IS NULL " +
            "GROUP BY a.id, a.nombre, a.stockActual")
    List<DTOArticulo> findArticulosConValores();

    @Query("SELECT a.id as id, a.nombre as nombre, a.stockActual as stockActual, " +
            "COALESCE(MAX(CASE WHEN dma.nombreDato = 'Lote Optimo' THEN adma.valorDato ELSE 0 END), 0) as valorLoteOptimo, " +
            "COALESCE(MAX(CASE WHEN dma.nombreDato = 'Punto Pedido' THEN adma.valorDato ELSE 0 END), 0) as valorPuntoPedido, " +
            "COALESCE(MAX(CASE WHEN dma.nombreDato = 'Stock Seguridad' THEN adma.valorDato ELSE 0 END), 0) as stockSeguridad " +
            "FROM Articulo a " +
            "LEFT JOIN ArticuloDatoModeloArticulo adma ON a.id = adma.articulo.id " +
            "LEFT JOIN DatoModeloArticulo dma ON adma.datoModeloArticulo.id = dma.id " +
            "WHERE a.fechaBaja IS NULL AND a.id = :articuloId " +
            "GROUP BY a.id, a.nombre, a.stockActual")
    DTOArticulo findArticuloConValoresById(@Param("articuloId") Long articuloId);
}
