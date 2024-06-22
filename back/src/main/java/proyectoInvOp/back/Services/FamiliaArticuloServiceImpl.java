package proyectoInvOp.back.Services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoInvOp.back.Entity.FamiliaArticulo;
import proyectoInvOp.back.Repositories.BaseRepository;
import proyectoInvOp.back.Repositories.FamiliaArticuloRepository;

@Service
public class FamiliaArticuloServiceImpl extends BaseServiceImpl<FamiliaArticulo,Long> implements FamiliaArticuloService{
    @Autowired
    FamiliaArticuloRepository familiaArticuloRepository;
    @PersistenceContext
    private EntityManager entityManager;


    public FamiliaArticuloServiceImpl(BaseRepository<FamiliaArticulo, Long> baseRepository) {
        super(baseRepository);
    }
}
