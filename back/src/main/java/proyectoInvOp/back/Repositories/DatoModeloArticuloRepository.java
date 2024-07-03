package proyectoInvOp.back.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyectoInvOp.back.Entity.DatoModeloArticulo;

@Repository
public interface DatoModeloArticuloRepository extends BaseRepository<DatoModeloArticulo,Long>{
    @Query(
            value = "SELECT * FROM datos_modelo_articulo WHERE datos_modelo_articulo.nombre =:nombre",
            nativeQuery = true
    )
    DatoModeloArticulo findDatoModeloArticuloConNombre(@Param("nombre")String nombreDato);
}
