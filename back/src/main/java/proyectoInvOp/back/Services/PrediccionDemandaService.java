package proyectoInvOp.back.Services;

import proyectoInvOp.back.Entity.PrediccionDemanda;

public interface PrediccionDemandaService extends BaseService<PrediccionDemanda,Long>{

    public PrediccionDemanda predecirDemanda(Long id, int cantPeriodos) throws Exception;
}
