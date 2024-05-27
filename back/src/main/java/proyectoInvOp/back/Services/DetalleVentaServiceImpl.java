package proyectoInvOp.back.Services;



import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import proyectoInvOp.back.Entity.DetalleVenta;
import proyectoInvOp.back.Entity.FamiliaArticulo;
import proyectoInvOp.back.Repositories.BaseRepository;
import proyectoInvOp.back.Repositories.DetalleVentaRepository;

public class DetalleVentaServiceImpl extends BaseServiceImpl<DetalleVenta,Long> {

    @Autowired
    DetalleVentaRepository detalleVentaRepository;

    @PersistenceContext
    private EntityManager entityManager;


    public DetalleVentaServiceImpl(BaseRepository<DetalleVenta, Long> baseRepository) {
        super(baseRepository);
    }

}
