package proyectoInvOp.back.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoInvOp.back.Entity.ParametroEspecifico;
import proyectoInvOp.back.Repositories.BaseRepository;
import proyectoInvOp.back.Repositories.ParametrosEspecificosRepository;

@Service
public class ParametroEspecificoServiceImpl  extends BaseServiceImpl<ParametroEspecifico,Long> implements ParametroEspecificoService{
    @Autowired
    ParametrosEspecificosRepository parametrosEspecificosRepository;
    public ParametroEspecificoServiceImpl(BaseRepository<ParametroEspecifico, Long> baseRepository) {
        super(baseRepository);
    }
}
