package proyectoInvOp.back.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import proyectoInvOp.back.Entity.DatoModeloArticulo;

@Repository
public interface DatoModeloArticuloRepository extends BaseRepository<DatoModeloArticulo,Long>{

    @Query(
            value = "",
            nativeQuery = true
    )
    List<DatoModeloArticulo> findDatoConNombre(String nombreDato);
}
