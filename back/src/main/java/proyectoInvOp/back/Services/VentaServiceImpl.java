package proyectoInvOp.back.Services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoInvOp.back.Entity.Articulo;
import proyectoInvOp.back.Entity.DetalleVenta;
import proyectoInvOp.back.Entity.Venta;
import proyectoInvOp.back.PatronObservador.ArticuloObserver;
import proyectoInvOp.back.PatronObservador.VentaObservable;
import proyectoInvOp.back.Repositories.ArticuloRepository;
import proyectoInvOp.back.Repositories.BaseRepository;
import proyectoInvOp.back.Repositories.VentaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
    ArticuloRepository articuloRepository;
    @Autowired
    VentaRepository ventaRepository;
    @Override
    @Transactional
    public Venta save(Venta venta) throws Exception {
        try {
            //Chequear stock de los articulos
            boolean bandera = true;

            List<DetalleVenta> detalleVentas = venta.getDetalleVentas();

            for (DetalleVenta detalleVenta : detalleVentas){

                Long idArticulo = detalleVenta.getArticulo().getId();
                Optional<Articulo> articulo = articuloRepository.findActiveById(idArticulo);

                if(articulo.isPresent()){

                    Articulo articuloBD = articulo.get();
                    int stockArt = articuloBD.getStockActual();

                    if(stockArt < detalleVenta.getCantidad()){

                        bandera = false;
                        break;
                    }
                }
            }

            if(bandera) {
                Venta savedVenta = super.save(venta);

                // Creo el observable y mando como parametro la Venta

                VentaObservable ventaObservable = new VentaObservable(savedVenta);

                ventaObservable.addObserver(articuloObserver);

                // Notificar a los observadores (actualizar stock)
                ventaObservable.notifyObservers();


                return savedVenta;
            }

        } catch (Exception e) {
            throw new Exception("Error saving Venta", e);
        }
            throw new Exception("No stock");


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
