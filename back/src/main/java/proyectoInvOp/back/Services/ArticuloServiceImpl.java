package proyectoInvOp.back.Services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import proyectoInvOp.back.DTOS.DTOArticulo;
import proyectoInvOp.back.Entity.*;
import proyectoInvOp.back.Repositories.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticuloServiceImpl extends BaseServiceImpl<Articulo,Long> implements ArticuloService {

    @Autowired
    ArticuloRepository articuloRepository;
    @Autowired
    ModeloInventarioRepository modeloInventarioRepository;
    @Autowired
    OrdenCompraRepository ordenCompraRepository;
    @Autowired
    ArticuloDatoModeloArticuloRepository articuloDatoModeloArticuloRepository;
    @Autowired
    DatoModeloArticuloRepository datoModeloArticuloRepository;

    public ArticuloServiceImpl(BaseRepository<Articulo, Long> baseRepository, ArticuloRepository articuloRepository) {
        super(baseRepository);
    }

    @Override
    public Articulo save(Articulo articulo) throws Exception {
        try {
            FamiliaArticulo familiaArticulo = articulo.getFamiliaArticulo();

            List<ModeloInventario> modeloInventarioList = modeloInventarioRepository.findAllActive();


            for (ModeloInventario modeloInventario : modeloInventarioList) {

                List<FamiliaArticulo> familiaArticuloList = modeloInventario.getFamiliaArticulos();

                for (FamiliaArticulo familiaArticulo1 : familiaArticuloList) {

                    if (familiaArticulo1.equals(familiaArticulo)) {

                        articulo.setModeloInventario(modeloInventario);
                    }
                }
            }
            articuloRepository.save(articulo);

            return articulo;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean bajaArticulo(Long id) throws Exception {

        try {
            Optional<Articulo> articulo = articuloRepository.findActiveById(id);

            if (articulo.isPresent()) {
                Articulo articuloEcontrado = articulo.get();
                List<OrdenCompra> ordenComprasList = ordenCompraRepository.findAllByArticuloId(id);

                for (OrdenCompra ordenCompra : ordenComprasList) {
                    EstadoOrdenCompra estadoOrdenCompra = ordenCompra.getEstadoOrdenCompra();
                    String estadoActual = estadoOrdenCompra.getNombre();
                    if ("Pendiente".equals(estadoActual) || "En Curso".equals(estadoActual)) {
                        return false;
                    }
                }

                // Aquí puedes proceder a dar de baja el artículo.
                articuloRepository.bajaLogicaById(id);
                return true;
            } else {
                return false;

            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<DTOArticulo> listarArticulos() throws Exception {
        try {
            return articuloRepository.findArticulosConValores();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public DTOArticulo listarArticuloById(Long id) throws Exception {
        try {
            return articuloRepository.findArticuloConValoresById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<DTOArticulo> listarArticulosFaltantes() throws Exception {

        List<DTOArticulo> listaArticulosFaltantes = new ArrayList<>();

        //Me traigo todos los datos
        List<ArticuloDatoModeloArticulo> articuloDatoModeloArticulos = articuloDatoModeloArticuloRepository.findAllActive();
        //Por cada dato chequeo si el stock SEGURIDAD esta por encima del stock acutual del articulo
        for (ArticuloDatoModeloArticulo articuloDatoModeloArticulo : articuloDatoModeloArticulos) {

            Articulo articulo = articuloDatoModeloArticulo.getArticulo();

            int stockActual = articulo.getStockActual();

            DatoModeloArticulo datoModeloArticulo1 = articuloDatoModeloArticulo.getDatoModeloArticulo();

            if ("Stock seguridad".equals(datoModeloArticulo1.getNombreDato())) {
                //Chekeo q el stock de seguridad este por debajo del stockActual
                if (stockActual <= articuloDatoModeloArticulo.getValorDato()) {

                    DTOArticulo articuloFaltante = new DTOArticulo();

                    Long idArt = articuloDatoModeloArticulo.getArticulo().getId();

                    articuloFaltante.setId(idArt);

                    String nombreArticulo = articuloDatoModeloArticulo.getArticulo().getNombre();

                    articuloFaltante.setNombre(nombreArticulo);

                    int stockActualArticulo = articuloDatoModeloArticulo.getArticulo().getStockActual();

                    articuloFaltante.setStockActual(stockActualArticulo);

                    int valorLoteOptimo = articuloDatoModeloArticulo.getValorDato();

                    articuloFaltante.setValorLoteOptimo(valorLoteOptimo);

                    listaArticulosFaltantes.add(articuloFaltante);


                }

            }

        }
        return listaArticulosFaltantes;
    }
    @Override
    public List<DTOArticulo> listarArticuloReponer() throws Exception {
        try {

            List<DTOArticulo> articulosAPedir = new ArrayList<DTOArticulo>();

            String nombreDato = "Punto pedido";

            DatoModeloArticulo puntoPedido = datoModeloArticuloRepository.findDatoModeloArticuloConNombre(nombreDato);

            Long idDato = puntoPedido.getId();

            List<ArticuloDatoModeloArticulo> articulosDatoModeloArticulo = articuloDatoModeloArticuloRepository.findArticulosDatoModeloArticulo(idDato);


            for(ArticuloDatoModeloArticulo articuloDatoModeloArticulo : articulosDatoModeloArticulo){

                Articulo articulo = articuloDatoModeloArticulo.getArticulo();

                List<OrdenCompra> ordenesCompras = ordenCompraRepository.findAllByArticuloId(articulo.getId());

                Boolean sinOrdenes = true;

                for (OrdenCompra ordenCompra : ordenesCompras) {
                    EstadoOrdenCompra estadoOrdenCompra = ordenCompra.getEstadoOrdenCompra();
                    if(estadoOrdenCompra.getNombre().equals("Pendiente")){
                        sinOrdenes = false;
                    }

                }


                if(articulo.getStockActual() <= articuloDatoModeloArticulo.getValorDato() && sinOrdenes){

                    DTOArticulo articuloAPedir = new DTOArticulo();

                    articuloAPedir.setId(articulo.getId());
                    articuloAPedir.setNombre(articulo.getNombre());
                    articuloAPedir.setStockActual(articulo.getStockActual());
                    articuloAPedir.setValorPuntoPedido(articuloDatoModeloArticulo.getValorDato());
                    articulosAPedir.add(articuloAPedir);

                }
            }
            return articulosAPedir;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public float calcularCGI(long id) throws Exception{
        try {
            Optional<Articulo> articulo = articuloRepository.findActiveById(id);

            Articulo articuloEcontrado = articulo.get();

            List<DemoraProveedorArticulo> proveedorArticulos = articuloEcontrado.getProveedorPredeterminado().getDemoraProveedorArticulos();

            float cp = 0;

            float p = 0;

            for (DemoraProveedorArticulo proveedorArticulo : proveedorArticulos) {
                if (proveedorArticulo.getArticulo().getId().equals(id)){
                    cp = proveedorArticulo.getCostoPedido();
                    p = proveedorArticulo.getPrecioArt();
                }
            }

            int d = articuloEcontrado.getDemanda();

            float ca = articuloEcontrado.getCostoAlmacenamiento();

            List<ArticuloDatoModeloArticulo> articulosDatoModeloArticulo = articuloDatoModeloArticuloRepository.findArticulosDatoModeloArticuloPorArticulo(id);

            int q = 0;
            for (ArticuloDatoModeloArticulo articuloDatoModeloArticulo: articulosDatoModeloArticulo){
                if(articuloDatoModeloArticulo.getDatoModeloArticulo().getNombreDato().equals("Lote Optimo")){
                    q = articuloDatoModeloArticulo.getValorDato();
                }
            }

            float cgi = p*d + ca*q/2 + cp*d/q;

            articuloEcontrado.setCGI(cgi);
            return (cgi);

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
