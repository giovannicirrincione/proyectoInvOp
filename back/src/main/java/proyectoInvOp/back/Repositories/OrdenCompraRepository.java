package proyectoInvOp.back.Repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyectoInvOp.back.Entity.Articulo;
import proyectoInvOp.back.Entity.OrdenCompra;

import java.util.List;

@Repository
public interface OrdenCompraRepository extends BaseRepository<OrdenCompra,Long>{
    //Me trae todas la ordenes de compra de un articulo

    @Query("SELECT oc FROM OrdenCompra oc WHERE oc.articulo.id = :articuloId")
    List<OrdenCompra> findAllByArticuloId(@Param("articuloId") Long articuloId);

    @Modifying
    @Transactional
    @Query("UPDATE OrdenCompra oc SET oc.fechaCreacion = :#{#newOrdenCompra.fechaCreacion}, " +
            "oc.fechaLlegada = :#{#newOrdenCompra.fechaLlegada}, " +
            "oc.montoTotal = :#{#newOrdenCompra.montoTotal}, " +
            "oc.cantidad = :#{#newOrdenCompra.cantidad}, " +
            "oc.estadoOrdenCompra = :#{#newOrdenCompra.estadoOrdenCompra}, " +
            "oc.proveedor = :#{#newOrdenCompra.proveedor}, " +
            "oc.articulo = :#{#newOrdenCompra.articulo} " +
            "WHERE oc.id = :ordenCompraId")
    void updateOrdenCompra(@Param("ordenCompraId") Long ordenCompraId, @Param("newOrdenCompra") OrdenCompra newOrdenCompra);


}
