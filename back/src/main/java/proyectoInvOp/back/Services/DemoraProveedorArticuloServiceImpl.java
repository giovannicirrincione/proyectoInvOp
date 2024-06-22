package proyectoInvOp.back.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoInvOp.back.Entity.DemoraProveedorArticulo;
import proyectoInvOp.back.Repositories.BaseRepository;
import proyectoInvOp.back.Repositories.DemoraProveedorArticuloRepository;

@Service
public class DemoraProveedorArticuloServiceImpl extends BaseServiceImpl<DemoraProveedorArticulo,Long> implements DemoraProveedorArticuloService{
    @Autowired
    DemoraProveedorArticuloRepository demoraProveedorArticuloRepository;
    public DemoraProveedorArticuloServiceImpl(BaseRepository<DemoraProveedorArticulo, Long> baseRepository) {
        super(baseRepository);
    }
}
