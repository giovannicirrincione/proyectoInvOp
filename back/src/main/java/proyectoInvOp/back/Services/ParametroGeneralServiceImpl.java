package proyectoInvOp.back.Services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoInvOp.back.Entity.Articulo;
import proyectoInvOp.back.Entity.ParametroGeneral;
import proyectoInvOp.back.Repositories.ArticuloRepository;
import proyectoInvOp.back.Repositories.BaseRepository;
import proyectoInvOp.back.Repositories.ParametroGeneralRepository;

@Service
public class ParametroGeneralServiceImpl  extends BaseServiceImpl<ParametroGeneral,Long> implements ParametroGeneralService {
    @Autowired
    ParametroGeneralRepository parametroGeneralRepository;
    public ParametroGeneralServiceImpl(BaseRepository<ParametroGeneral, Long> baseRepository) {
        super(baseRepository);
    }
}
