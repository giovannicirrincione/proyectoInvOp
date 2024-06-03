package proyectoInvOp.back.Services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoInvOp.back.Entity.Articulo;
import proyectoInvOp.back.Entity.EstadoOrdenCompra;
import proyectoInvOp.back.Repositories.ArticuloRepository;
import proyectoInvOp.back.Repositories.BaseRepository;
import proyectoInvOp.back.Repositories.EstadoOrdenCompraRepository;

@Service
public class EstadoOrdenCompraServiceImpl  extends BaseServiceImpl<EstadoOrdenCompra,Long> implements EstadoOrdenCompraService{
    @Autowired
    EstadoOrdenCompraRepository estadoOrdenCompraRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public EstadoOrdenCompraServiceImpl(BaseRepository<EstadoOrdenCompra, Long> baseRepository) {
        super(baseRepository);
    }
}
