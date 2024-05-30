package proyectoInvOp.back.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyectoInvOp.back.Entity.EstadoOrdenCompra;
@Repository
public interface EstadoOrdenCompraRepository extends BaseRepository<EstadoOrdenCompra,Long>{

    @Query("SELECT e FROM EstadoOrdenCompra e WHERE e.nombre = :nombre")
    EstadoOrdenCompra findByNombre(@Param("nombre") String nombre);

}
