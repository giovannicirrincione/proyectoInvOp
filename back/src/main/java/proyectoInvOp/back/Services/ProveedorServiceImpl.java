package proyectoInvOp.back.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoInvOp.back.Entity.Articulo;
import proyectoInvOp.back.Entity.DemoraProveedorArticulo;
import proyectoInvOp.back.Entity.Proveedor;
import proyectoInvOp.back.Repositories.ArticuloRepository;
import proyectoInvOp.back.Repositories.BaseRepository;
import proyectoInvOp.back.Repositories.ProveedorRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProveedorServiceImpl extends BaseServiceImpl<Proveedor,Long> implements ProveedorService{

    @Autowired
    ProveedorRepository proveedorRepository;

    public ProveedorServiceImpl(BaseRepository<Proveedor, Long> baseRepository, ProveedorRepository proveedorRepository) {
        super(baseRepository);
    }

    @Override
    public List<Proveedor> getProveedoresArticulo(Long id) throws Exception {

        try {

            List<Proveedor> proveedores = proveedorRepository.findAllActive();

            List<Proveedor> provedoresArticulo = new ArrayList<Proveedor>();

            for(Proveedor proveedor : proveedores){
                boolean bandera = false;
                List<DemoraProveedorArticulo> demoraProveedorArticulos = proveedor.getDemoraProveedorArticulos();

                for (DemoraProveedorArticulo demoraProveedorArticulo : demoraProveedorArticulos){
                    Articulo articulo = demoraProveedorArticulo.getArticulo();
                    if(articulo.getId().equals(id)){
                        bandera = true;
                        break;
                    }
                }
                if(bandera){
                    provedoresArticulo.add(proveedor);

                }
            }
            return provedoresArticulo;

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
