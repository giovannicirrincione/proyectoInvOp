package proyectoInvOp.back.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoInvOp.back.DTOS.DTOArticulo;
import proyectoInvOp.back.Entity.*;
import proyectoInvOp.back.Repositories.*;

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

    public ArticuloServiceImpl(BaseRepository<Articulo, Long> baseRepository, ArticuloRepository articuloRepository) {
        super(baseRepository);
    }

    @Override
    public Articulo saveArticulo(Articulo articulo) throws Exception {
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
        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }

    @Override
    public String bajaArticulo(Long id) throws Exception {

        try {
            Optional<Articulo> articulo = articuloRepository.findActiveById(id);

            if (articulo.isPresent()) {
                Articulo articuloEcontrado = articulo.get();
                List<OrdenCompra> ordenComprasList = ordenCompraRepository.findAllByArticuloId(id);

                for (OrdenCompra ordenCompra : ordenComprasList) {
                    EstadoOrdenCompra estadoOrdenCompra = ordenCompra.getEstadoOrdenCompra();
                    String estadoActual = estadoOrdenCompra.getNombre();
                    if ("Pendiente".equals(estadoActual) || "En Curso".equals(estadoActual)) {
                        return "No se puede dar de baja porque tiene una orden de compra pendiente o en curso";
                    }
                }

                // Aquí puedes proceder a dar de baja el artículo.
                articuloRepository.bajaLogicaById(id);
                return "El artículo ha sido dado de baja";
            } else {
                return "El artículo no fue encontrado";

            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    @Override
    public List<DTOArticulo> listarArticulos() throws Exception {
        try {
            return articuloRepository.findArticulosConValores();
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public DTOArticulo listarArticuloById(Long id) throws Exception {
        try {
            return articuloRepository.findArticuloConValoresById(id);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}