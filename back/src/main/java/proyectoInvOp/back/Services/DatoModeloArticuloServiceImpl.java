package proyectoInvOp.back.Services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoInvOp.back.Entity.Articulo;
import proyectoInvOp.back.Entity.DatoModeloArticulo;
import proyectoInvOp.back.Repositories.BaseRepository;
import proyectoInvOp.back.Repositories.DatoModeloArticuloRepository;
import proyectoInvOp.back.Repositories.FamiliaArticuloRepository;

@Service
public class DatoModeloArticuloServiceImpl  extends BaseServiceImpl<DatoModeloArticulo,Long> implements DatoModeloArticuloService{
    @Autowired
    DatoModeloArticuloRepository datoModeloArticuloRepository;
    @PersistenceContext
    private EntityManager entityManager;
    public DatoModeloArticuloServiceImpl(BaseRepository<DatoModeloArticulo, Long> baseRepository) {
        super(baseRepository);
    }
}
