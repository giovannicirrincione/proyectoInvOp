package proyectoInvOp.back.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoInvOp.back.Entity.ParametroEspecifico;
import proyectoInvOp.back.Entity.ParametroGeneral;
import proyectoInvOp.back.Repositories.BaseRepository;
import proyectoInvOp.back.Repositories.ParametroEspecificoRepository;
import proyectoInvOp.back.Repositories.ParametroGeneralRepository;

@Service
public class ParametroEspecificoServiceImpl extends BaseServiceImpl<ParametroEspecifico,Long> implements ParametroEspecificoService {

    @Autowired
    ParametroEspecificoRepository parametroEspecificoRepository;


    public ParametroEspecificoServiceImpl(BaseRepository<ParametroEspecifico,Long> baseRepository,  ParametroEspecificoRepository parametroEspecificoRepository) {
        super(baseRepository);
    }
}
