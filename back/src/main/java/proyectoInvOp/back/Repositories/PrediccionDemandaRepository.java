package proyectoInvOp.back.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import proyectoInvOp.back.Entity.PrediccionDemanda;

import java.time.YearMonth;
import java.util.List;
import java.util.Date;

public interface PrediccionDemandaRepository extends BaseRepository<PrediccionDemanda, Long>{

    @Query(
            value = "SELECT valorPredecido FROM prediccionDemanda WHERE prediccionDemanda.mesAnio BETWEEN :desde AND :hasta",
            nativeQuery = true
    )
    List<Double> searchPorFecha(@Param("desde") YearMonth desde, @Param("hasta") YearMonth hasta);
}
