package proyectoInvOp.back.Services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import proyectoInvOp.back.DTOS.DTOArticulo;
import proyectoInvOp.back.DTOS.DTOArticuloMain;
import proyectoInvOp.back.Entity.*;
import proyectoInvOp.back.Repositories.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Autowired
    FamiliaArticuloRepository familiaArticuloRepository;
    @Autowired
    ProveedorRepository proveedorRepository;

    public ArticuloServiceImpl(BaseRepository<Articulo, Long> baseRepository, ArticuloRepository articuloRepository) {
        super(baseRepository);
    }

    //PARA EVITAR EL STACK OVERFLOW Q GENERA EL PROVEEDOR PRED
    @Transactional
    public List<DTOArticuloMain> findAllByDTO() throws Exception{
        try {
            List<Articulo> articulos = articuloRepository.findAll();
            return articulos.stream()
                    .map(DTOArticuloMain::new)
                    .collect(Collectors.toList());
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @Transactional
    public DTOArticuloMain createArticulo(DTOArticuloMain articuloRequestDTO) {
        Articulo articulo = new Articulo();

        // Establece los campos desde el DTO
        articulo.setNombre(articuloRequestDTO.getNombre());
        articulo.setDescripcion(articuloRequestDTO.getDescripcion());
        articulo.setStockActual(articuloRequestDTO.getStockActual());
        articulo.setCGI(articuloRequestDTO.getCGI());
        articulo.setPrecioVenta(articuloRequestDTO.getPrecioVenta());

        // Busca y establece relaciones
        Optional<FamiliaArticulo> familiaArticuloBD = familiaArticuloRepository.findById(articuloRequestDTO.getFamiliaArticuloId());
        FamiliaArticulo familiaArticulo = familiaArticuloBD.get();
        articulo.setFamiliaArticulo(familiaArticulo);


            Optional<Proveedor> proveedorPredeterminadoBD = proveedorRepository.findById(articuloRequestDTO.getProveedorPredeterminadoId());
            Proveedor proveedorPredeterminado = proveedorPredeterminadoBD.get();
            articulo.setProveedorPredeterminado(proveedorPredeterminado);




        List<ModeloInventario> modeloInventarioList = modeloInventarioRepository.findAllActive();
        for (ModeloInventario modeloInventario : modeloInventarioList) {
            if (modeloInventario.getFamiliaArticulos().contains(familiaArticulo)) {
                articulo.setModeloInventario(modeloInventario);
                break; // Sale del bucle una vez que se encuentra y se asigna un modelo inventario
            }
        }

        // Guarda el artículo
        articulo = articuloRepository.save(articulo);

        return new DTOArticuloMain(articulo);
    }

    @Transactional
    public DTOArticuloMain updateArticulo(Long id, DTOArticuloMain articuloRequestDTO) {
        Optional<Articulo> articuloBD = articuloRepository.findById(id);

        Articulo articulo = articuloBD.get();


        // Actualiza los campos desde el DTO
        articulo.setNombre(articuloRequestDTO.getNombre());
        articulo.setDescripcion(articuloRequestDTO.getDescripcion());
        articulo.setStockActual(articuloRequestDTO.getStockActual());
        articulo.setCGI(articuloRequestDTO.getCGI());
        articulo.setPrecioVenta(articuloRequestDTO.getPrecioVenta());

        // Actualiza relaciones
        Optional<FamiliaArticulo> familiaArticuloBD = familiaArticuloRepository.findById(articuloRequestDTO.getFamiliaArticuloId());
        FamiliaArticulo familiaArticulo = familiaArticuloBD.get();
        articulo.setFamiliaArticulo(familiaArticulo);


            Optional<Proveedor> proveedorPredeterminadoBD = proveedorRepository.findById(articuloRequestDTO.getProveedorPredeterminadoId());

            Proveedor proveedorPredeterminado = proveedorPredeterminadoBD.get();
            System.out.println(proveedorPredeterminado.getNombre());

            articulo.setProveedorPredeterminado(proveedorPredeterminado);



        // Guarda los cambios
        articulo = articuloRepository.save(articulo);

        return new DTOArticuloMain(articulo);
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

                    articuloFaltante.setStockSeguridad(articuloDatoModeloArticulo.getValorDato());


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
                    if(estadoOrdenCompra.getNombre().equals("En curso")){
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
    public Float calcularCGI(Long id) throws Exception{

        try {
            Optional<Articulo> articulo = articuloRepository.findActiveById(id);


            Articulo articuloEcontrado = articulo.get();
            List<DemoraProveedorArticulo> proveedorArticulos = articuloEcontrado.getProveedorPredeterminado().getDemoraProveedorArticulos();

            for (DemoraProveedorArticulo prov : proveedorArticulos) {
                System.out.println(prov.getPrecioArt());
            }


            float costoPedido = 0;

            float precioArt = 0;

            for (DemoraProveedorArticulo proveedorArticulo : proveedorArticulos) {
                System.out.println(proveedorArticulo.getArticulo().getNombre());
                if (proveedorArticulo.getArticulo().getId().equals(id)){
                    System.out.println(proveedorArticulo.getArticulo().getId());
                    costoPedido = proveedorArticulo.getCostoPedido();
                    precioArt = proveedorArticulo.getPrecioArt();
                }
            }

            Integer demanda = articuloEcontrado.getDemanda();

            Float costoAlmacenamiento = articuloEcontrado.getCostoAlmacenamiento();

            List<ArticuloDatoModeloArticulo> articulosDatoModeloArticulo = articuloDatoModeloArticuloRepository.findArticulosDatoModeloArticuloPorArticulo(id);

            int loteOptimo = 1;
            for (ArticuloDatoModeloArticulo articuloDatoModeloArticulo: articulosDatoModeloArticulo){
                if(articuloDatoModeloArticulo.getDatoModeloArticulo().getNombreDato().equals("Lote optimo")){
                    loteOptimo = articuloDatoModeloArticulo.getValorDato();
                }
            }
            System.out.println("before");
            System.out.println(precioArt);
            System.out.println(demanda);
            System.out.println(costoAlmacenamiento);
            System.out.println(loteOptimo);
            System.out.println(costoPedido);

            if (demanda == null || costoAlmacenamiento == null){
                demanda=0;
                costoAlmacenamiento= 0.0F;
            }
            Float cgi = (precioArt) *demanda + costoAlmacenamiento*(loteOptimo/2) + costoPedido*(demanda/loteOptimo);

            articuloEcontrado.setCGI(cgi);

            update(articuloEcontrado.getId(),articuloEcontrado);

            return cgi;


        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
