package proyectoInvOp.back.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyectoInvOp.back.Entity.OrdenCompra;

import java.util.List;

@Repository
public interface OrdenCompraRepository extends BaseRepository<OrdenCompra,Long>{
    //Me trae todas la ordenes de compra de un articulo

    @Query("SELECT oc FROM OrdenCompra oc WHERE oc.articulo.id = :articuloId")
    List<OrdenCompra> findAllByArticuloId(@Param("articuloId") Long articuloId);


}
