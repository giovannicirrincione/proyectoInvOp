package proyectoInvOp.back.Services;

import org.springframework.stereotype.Service;
import proyectoInvOp.back.Entity.MetodoPrediccion;
import proyectoInvOp.back.Repositories.BaseRepository;

@Service
public class MetodoPrediccionServiceImpl extends BaseServiceImpl<MetodoPrediccion,Long> implements MetodoPrediccionService{
    public MetodoPrediccionServiceImpl(BaseRepository<MetodoPrediccion, Long> baseRepository) {
        super(baseRepository);
    }
}
