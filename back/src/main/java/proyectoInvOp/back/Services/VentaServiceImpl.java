package proyectoInvOp.back.Services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoInvOp.back.Entity.*;
import proyectoInvOp.back.PatronObservador.ArticuloObserver;
import proyectoInvOp.back.PatronObservador.VentaObservable;
import proyectoInvOp.back.Repositories.*;
import proyectoInvOp.back.ServiceEmail.GmailService;

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
    @Autowired
    ArticuloDatoModeloArticuloRepository articuloDatoModeloArticuloRepository;
    @Autowired
    EstadoOrdenCompraRepository estadoOrdenCompraRepository;
    @Autowired
    OrdenCompraRepository ordenCompraRepository;
    @Autowired
    GmailService gmailService;
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

                float montoTotal = calcularMontoTotal(venta);

                venta.setMontoTotal(montoTotal);

                Venta savedVenta = super.save(venta);

                // Creo el observable y mando como parametro la Venta

                VentaObservable ventaObservable = new VentaObservable(savedVenta);

                ventaObservable.addObserver(articuloObserver);

                // Notificar a los observadores (actualizar stock)
                ventaObservable.notifyObservers();



                //chequear si el articulo bajo de su PUNTO PEDIDO
                List<DetalleVenta> detalleVenta = venta.getDetalleVentas();
                for (DetalleVenta detallesVenta : detalleVenta) {
                    Articulo articulo = detallesVenta.getArticulo();
                    generacionOrdenCompra(articulo);
                }


                return savedVenta;
            }

        } catch (Exception e) {
            throw new Exception("Error saving Venta", e);
        }
            throw new Exception("No stock");


    }

    public void generacionOrdenCompra(Articulo articulo) throws Exception {

        Long idArt = articulo.getId();

        Optional<Articulo> articulo1 = articuloRepository.findActiveById(idArt);

        Articulo articuloBD = articulo1.get();

        int stockActual = articuloBD.getStockActual();


        //Obtenemos el valor del PP
        List<ArticuloDatoModeloArticulo> articuloDatoModeloArticulos = articuloDatoModeloArticuloRepository.findAllActive();

        int puntoPedido = 0;
        for (ArticuloDatoModeloArticulo  articulosDatoBD : articuloDatoModeloArticulos){
            Articulo articulo2 = articulosDatoBD.getArticulo();
            if (articulo2 == articuloBD){
                DatoModeloArticulo  articuloDatoModeloArticulo = articulosDatoBD.getDatoModeloArticulo();
                if("Punto pedido".equals(articuloDatoModeloArticulo.getNombre())){
                    System.out.println(articulosDatoBD.getValorDato());
                    puntoPedido = articulosDatoBD.getValorDato();
                    break;
                }
            }
        }

        //Obtenemos el valor del Lote Optimo

        int loteOptimo = 0;
        for (ArticuloDatoModeloArticulo  articulosDatoBD : articuloDatoModeloArticulos){
            Articulo articulo2 = articulosDatoBD.getArticulo();
            if (articulo2 == articuloBD){
                DatoModeloArticulo  articuloDatoModeloArticulo = articulosDatoBD.getDatoModeloArticulo();
                if("Lote optimo".equals(articuloDatoModeloArticulo.getNombre())){
                    loteOptimo = articulosDatoBD.getValorDato();
                    break;
                }
            }
        }


        //Si el PP es menor al stock Actual se genera la orden de compra (si esq ya no hay una pendiente)
        if(puntoPedido>=stockActual) {
            System.out.println("hola entre");
            boolean bandera = true;


            //CHEQUEAMOS QUE NO HAYA UNA ORDEN DE COMPRA PENDIENTE PARA ESE ARTICULO
            List<OrdenCompra> ordenCompras = ordenCompraRepository.findAllActive();

            for (OrdenCompra ordenes : ordenCompras) {
                if (ordenes.getFechaLlegada() == null) {
                    Articulo articulo2 = ordenes.getArticulo();
                    Long idArtOrden = articulo2.getId();
                    if (idArtOrden == idArt) {
                        bandera = false;
                        break;
                    }
                }
            }
            if ("Modelo intervalo fijo".equals(articulo.getModeloInventario().getNombre())){
                bandera = false;
            }

             //GENERAMOS LA ORDEN COMPRA
            if (bandera) {

                OrdenCompra ordenCompra = new OrdenCompra();

                ordenCompra.setCantidad(loteOptimo);
                ordenCompra.setFechaCreacion(LocalDate.now());

                Proveedor proveedor = articuloBD.getProveedorPredeterminado();


                List<DemoraProveedorArticulo> demoraProveedorArticulos = proveedor.getDemoraProveedorArticulos();

                float precioArt = 0;

                for (DemoraProveedorArticulo demoras : demoraProveedorArticulos) {


                    if (demoras.getArticulo().getId() == articuloBD.getId()) {
                        precioArt = demoras.getPrecioArt();
                        break;
                    }
                }

                float montoTotal = loteOptimo * precioArt;

                ordenCompra.setMontoTotal(montoTotal);

                EstadoOrdenCompra estadoOrdenCompra = estadoOrdenCompraRepository.findByNombre("En curso");

                ordenCompra.setEstadoOrdenCompra(estadoOrdenCompra);

                ordenCompra.setArticulo(articuloBD);

                ordenCompra.setProveedor(articuloBD.getProveedorPredeterminado());

                ordenCompraRepository.save(ordenCompra);
                //Metodo que notifica al gerente de Operaiones la generacion de un orden compra aut
                gmailService.mandarEmail(ordenCompra);
            }


        }

    }
    public float calcularMontoTotal(Venta venta){

        float total = 0;

        List<DetalleVenta> detalleVentas = venta.getDetalleVentas();

        for (DetalleVenta detalles : detalleVentas){
            int cantidadVendida = detalles.getCantidad();

            Long idArt = detalles.getArticulo().getId();

            Optional<Articulo> articulo = articuloRepository.findActiveById(idArt);

            Articulo articuloBD = articulo.get();

            float precioArt = articuloBD.getPrecioVenta();

            detalles.setSubTotal(precioArt*cantidadVendida);

            total = total + (precioArt*cantidadVendida);
        }
        return total;
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
