package proyectoInvOp.back.Services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoInvOp.back.Entity.Articulo;
import proyectoInvOp.back.Entity.DetalleOrden;
import proyectoInvOp.back.Entity.OrdenCompra;
import proyectoInvOp.back.Repositories.ArticuloRepository;
import proyectoInvOp.back.Repositories.BaseRepository;
import proyectoInvOp.back.Repositories.OrdenCompraRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdenCompraServiceImpl extends BaseServiceImpl<OrdenCompra,Long> implements OrdenCompraService{
    @Autowired
    OrdenCompraRepository ordenCompraRepository;
    @PersistenceContext
    private EntityManager entityManager;


    public OrdenCompraServiceImpl(BaseRepository<OrdenCompra, Long> baseRepository, OrdenCompraRepository ordenCompraRepository) {
        super(baseRepository);
    }
    @Override
    @Transactional
    public OrdenCompra save(OrdenCompra ordenCompra) throws Exception {
        try {
            // Asegúrate de que cada `DetalleOrden` esté gestionado por el `EntityManager`
            List<DetalleOrden> managedDetalles = new ArrayList<>();
            for (DetalleOrden detalle : ordenCompra.getDetallesOrden()) {
                if (detalle.getId() != null) {
                    // Si `detalle` ya tiene un ID, está desconectado, así que usa `merge`
                    detalle = entityManager.merge(detalle);
                } else {
                    // Si es un nuevo `detalle`, simplemente persístelo
                    entityManager.persist(detalle);
                }
                managedDetalles.add(detalle);
            }
            // Reemplaza la lista de detalles en `ordenCompra` con la lista gestionada
            ordenCompra.setDetallesOrden(managedDetalles);

            return super.save(ordenCompra);
        } catch (Exception e) {
            throw new Exception("Error saving OrdenCompra", e);
        }
    }


}
