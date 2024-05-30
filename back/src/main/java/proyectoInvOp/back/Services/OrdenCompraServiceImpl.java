package proyectoInvOp.back.Services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoInvOp.back.Entity.OrdenCompra;
import proyectoInvOp.back.Repositories.BaseRepository;
import proyectoInvOp.back.Repositories.OrdenCompraRepository;

@Service
public class OrdenCompraServiceImpl extends BaseServiceImpl<OrdenCompra,Long> implements OrdenCompraService {
    @Autowired
    OrdenCompraRepository ordenCompraRepository;

    public OrdenCompraServiceImpl(BaseRepository<OrdenCompra, Long> baseRepository, OrdenCompraRepository ordenCompraRepository) {
        super(baseRepository);
    }



}




