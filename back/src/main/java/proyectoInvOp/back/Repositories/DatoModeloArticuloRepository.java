package proyectoInvOp.back.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyectoInvOp.back.Entity.DatoModeloArticulo;

import java.util.List;

@Repository
public interface DatoModeloArticuloRepository extends BaseRepository<DatoModeloArticulo,Long>{

    @Query(
            value = "SELECT * FROM datosModeloArticulo WHERE datosModeloArticulo.nombreDato LIKE %nombreDato%",
            nativeQuery = true
    )
    DatoModeloArticulo findDatoModeloArticuloConNombre(@Param("nombreDato")String nombreDato);
}
