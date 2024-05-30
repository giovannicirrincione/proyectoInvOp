package proyectoInvOp.back.Services;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoInvOp.back.Entity.DetalleVenta;
import proyectoInvOp.back.Entity.Venta;
import proyectoInvOp.back.PatronObservador.ArticuloObserver;
import proyectoInvOp.back.PatronObservador.VentaObservable;
import proyectoInvOp.back.Repositories.BaseRepository;
import proyectoInvOp.back.Repositories.VentaRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class VentaServiceImpl extends BaseServiceImpl<Venta, Long> implements VentaService {
    @Autowired
    private final ArticuloObserver articuloObserver;

    @Autowired
    public VentaServiceImpl(BaseRepository<Venta, Long> baseRepository, ArticuloObserver articuloObserver) {
        super(baseRepository);
        this.articuloObserver = articuloObserver;
    }
    @Autowired
    EntityManager entityManager;
    @Autowired
    VentaRepository ventaRepository;
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
            //Guardamos la venta en la BD

            Venta savedVenta = super.save(venta);


            // Creo el observable y mando como parametro la Venta

            VentaObservable ventaObservable = new VentaObservable(savedVenta);

            ventaObservable.addObserver(articuloObserver);


            // Notificar a los observadores (actualizar stock)
            ventaObservable.notifyObservers();


            return savedVenta;


        } catch (Exception e) {
            throw new Exception("Error saving Venta", e);
        }



    }
    @Override
    public int demandaHistorica(Long id, LocalDate fechaDesde, LocalDate fechaHasta) throws Exception {
        try {
            List<Venta> ventasDelArticulo = ventaRepository.ventasDeUnProducto(id, fechaDesde, fechaHasta);

            int cantidadVendidaTotal = 0;
            for (Venta venta : ventasDelArticulo) {
                List<DetalleVenta> detalleVentas = venta.getDetalleVentas();
                for (DetalleVenta detalle : detalleVentas) {
                    if (id.equals(detalle.getArticulo().getId())) {
                        cantidadVendidaTotal += detalle.getCantidad();
                    }
                }
            }
            return cantidadVendidaTotal;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
