package proyectoInvOp.back.Services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoInvOp.back.Entity.Articulo;
import proyectoInvOp.back.Entity.DatoModeloArticulo;
import proyectoInvOp.back.Entity.ModeloInventario;
import proyectoInvOp.back.Repositories.ArticuloRepository;
import proyectoInvOp.back.Repositories.BaseRepository;
import proyectoInvOp.back.Repositories.DatoModeloArticuloRepository;
import proyectoInvOp.back.Repositories.FamiliaArticuloRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DatoModeloArticuloServiceImpl  extends BaseServiceImpl<DatoModeloArticulo,Long> implements DatoModeloArticuloService{
    @Autowired
    DatoModeloArticuloRepository datoModeloArticuloRepository;
    @Autowired
    ArticuloRepository articuloRepository;
    @PersistenceContext
    private EntityManager entityManager;
    public DatoModeloArticuloServiceImpl(BaseRepository<DatoModeloArticulo, Long> baseRepository) {
        super(baseRepository);
    }


    @Override
    public List<DatoModeloArticulo> datosModeloByArticulo(Long id) throws Exception {


        Optional<Articulo> articuloOptional = articuloRepository.findActiveById(id);

        Articulo articuloBD = articuloOptional.get();

        ModeloInventario modeloInventario = articuloBD.getModeloInventario();

        System.out.println(modeloInventario.getNombre());

        List<DatoModeloArticulo> datoModeloArticulos = modeloInventario.getDatosModeloArticulos();

        return datoModeloArticulos;


    }
}
