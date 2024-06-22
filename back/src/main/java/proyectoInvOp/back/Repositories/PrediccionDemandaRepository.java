package proyectoInvOp.back.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyectoInvOp.back.Entity.PrediccionDemanda;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PrediccionDemandaRepository extends BaseRepository<PrediccionDemanda,Long>{

    @Query("SELECT pd FROM PrediccionDemanda pd WHERE pd.articulo.id = :idArticulo AND pd.fechaDesde >= :fechaActual AND pd.fechaHasta <= :fechaFinal")
    List<PrediccionDemanda> findPrediccionesByArticuloAndPeriodo(@Param("idArticulo") Long idArticulo, @Param("fechaActual") LocalDate fechaActual, @Param("fechaFinal") LocalDate fechaFinal);

}
