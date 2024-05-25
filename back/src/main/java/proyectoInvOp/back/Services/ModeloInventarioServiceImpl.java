package proyectoInvOp.back.Services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoInvOp.back.Entity.FamiliaArticulo;
import proyectoInvOp.back.Entity.ModeloInventario;
import proyectoInvOp.back.Repositories.BaseRepository;
import proyectoInvOp.back.Repositories.FamiliaArticuloRepository;
import proyectoInvOp.back.Repositories.ModeloInventarioRepository;

@Service
public class ModeloInventarioServiceImpl extends BaseServiceImpl<ModeloInventario,Long> implements ModeloInventarioService{
    @Autowired
    ModeloInventarioRepository modeloInventarioRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public ModeloInventarioServiceImpl(BaseRepository<ModeloInventario, Long> baseRepository) {
        super(baseRepository);
    }
}
