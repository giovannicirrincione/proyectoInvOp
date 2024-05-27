package proyectoInvOp.back.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoInvOp.back.Entity.ParametroGeneral;
import proyectoInvOp.back.Entity.Proveedor;
import proyectoInvOp.back.Repositories.BaseRepository;
import proyectoInvOp.back.Repositories.ParametroGeneralRepository;
import proyectoInvOp.back.Repositories.ProveedorRepository;

@Service
public class ParametroGeneralServiceImpl extends BaseServiceImpl<ParametroGeneral,Long> implements ParametroGeneralService{

    @Autowired
    ParametroGeneralRepository parametroGeneralRepository;

    public ParametroGeneralServiceImpl(BaseRepository<ParametroGeneral,Long> baseRepository, ParametroGeneralRepository parametroGeneralRepository) {
        super(baseRepository);
    }
}
