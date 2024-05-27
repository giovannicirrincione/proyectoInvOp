package proyectoInvOp.back.Services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoInvOp.back.Entity.DetalleOrden;
import proyectoInvOp.back.Entity.DetalleVenta;
import proyectoInvOp.back.Entity.Venta;
import proyectoInvOp.back.Repositories.BaseRepository;
import proyectoInvOp.back.Repositories.VentaRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class VentaServiceImpl extends BaseServiceImpl<Venta, Long> implements VentaService {
    public VentaServiceImpl(BaseRepository<Venta, Long> baseRepository, VentaRepository ventaRepository) {
        super(baseRepository);
    }

    @Autowired
    VentaRepository ventaRepository;
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
    public Venta save(Venta venta) throws Exception {
        try {
            // Asegúrate de que cada `DetalleVenta` esté gestionado por el `EntityManager`
            List<DetalleVenta> managedDetalles = new ArrayList<>();
            for (DetalleVenta detalle : venta.getDetalleVentas()) {
                if (detalle.getId() != null) {
                    // Si `detalle` ya tiene un ID, está desconectado, así que usa `merge`
                    detalle = entityManager.merge(detalle);
                } else {
                    // Si es un nuevo `detalle`, simplemente persístelo
                    entityManager.persist(detalle);
                }
                managedDetalles.add(detalle);
            }
            // Reemplaza la lista de detalles en `venta` con la lista gestionada
            venta.setDetalleVentas(managedDetalles);

            return super.save(venta);
        } catch (Exception e) {
            throw new Exception("Error saving Venta", e);
        }



    }

}
