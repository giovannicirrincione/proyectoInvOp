package proyectoInvOp.back.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyectoInvOp.back.Entity.Articulo;
import proyectoInvOp.back.Entity.ArticuloDatoModeloArticulo;

import java.util.List;

@Repository
public interface ArticuloDatoModeloArticuloRepository extends BaseRepository<ArticuloDatoModeloArticulo,Long>{

    @Query(
            value = "SELECT * FROM articulo_dato_modelo_articulo WHERE articulo_dato_modelo_articulo.dato_modelo_articulo_id = :idDato",
            nativeQuery = true
    )
    List<ArticuloDatoModeloArticulo> findArticulosDatoModeloArticulo(@Param("idDato")Long idDato);

    @Query(
            value = "SELECT * FROM articulo_dato_modelo_articulo WHERE articulo_dato_modelo_articulo.articulo_id = :idDato",
            nativeQuery = true
    )
    List<ArticuloDatoModeloArticulo> findArticulosDatoModeloArticuloPorArticulo(@Param("idDato")Long idDato);
}
