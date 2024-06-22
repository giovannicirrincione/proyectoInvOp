package proyectoInvOp.back.Services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoInvOp.back.Entity.ArticuloDatoModeloArticulo;
import proyectoInvOp.back.Entity.EstadoOrdenCompra;
import proyectoInvOp.back.Repositories.ArticuloDatoModeloArticuloRepository;
import proyectoInvOp.back.Repositories.BaseRepository;
import proyectoInvOp.back.Repositories.EstadoOrdenCompraRepository;

@Service
public class ArticuloDatoModeloArticuloServiceImpl extends BaseServiceImpl<ArticuloDatoModeloArticulo,Long> implements ArticuloDatoModeloArticuloService{
    @Autowired
    ArticuloDatoModeloArticuloRepository articuloDatoModeloArticuloRepository;
    @PersistenceContext
    private EntityManager entityManager;
    public ArticuloDatoModeloArticuloServiceImpl(BaseRepository<ArticuloDatoModeloArticulo, Long> baseRepository) {
        super(baseRepository);
    }
}
