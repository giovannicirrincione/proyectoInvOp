package proyectoInvOp.back.Services;

import org.springframework.stereotype.Service;
import proyectoInvOp.back.Entity.PrediccionDemanda;
import proyectoInvOp.back.Repositories.BaseRepository;

@Service
public class PrediccionDemandaServiceImpl extends BaseServiceImpl<PrediccionDemanda,Long>{

    public PrediccionDemandaServiceImpl(BaseRepository<PrediccionDemanda, Long> baseRepository) {
        super(baseRepository);
    }


}
