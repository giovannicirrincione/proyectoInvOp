package proyectoInvOp.back.Services;

import proyectoInvOp.back.Entity.PrediccionDemanda;

import java.time.YearMonth;
import java.util.List;

public interface PrediccionDemandaService extends BaseService<PrediccionDemanda, Long> {

    List<Double> searchPorFecha(YearMonth desde, YearMonth hasta) throws Exception;
}
