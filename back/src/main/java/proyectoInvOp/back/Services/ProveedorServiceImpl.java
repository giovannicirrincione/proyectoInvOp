package proyectoInvOp.back.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoInvOp.back.Entity.Articulo;
import proyectoInvOp.back.Entity.Proveedor;
import proyectoInvOp.back.Repositories.ArticuloRepository;
import proyectoInvOp.back.Repositories.BaseRepository;
import proyectoInvOp.back.Repositories.ProveedorRepository;

@Service
public class ProveedorServiceImpl extends BaseServiceImpl<Proveedor,Long> implements ProveedorService{

    @Autowired
    ProveedorRepository proveedorRepository;

    public ProveedorServiceImpl(BaseRepository<Proveedor, Long> baseRepository, ProveedorRepository proveedorRepository) {
        super(baseRepository);
    }
}
