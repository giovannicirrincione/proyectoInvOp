package proyectoInvOp.back.Services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoInvOp.back.Entity.Articulo;
import proyectoInvOp.back.Entity.EstadoOrdenCompra;
import proyectoInvOp.back.Entity.OrdenCompra;
import proyectoInvOp.back.Entity.Proveedor;
import proyectoInvOp.back.Repositories.BaseRepository;
import proyectoInvOp.back.Repositories.EstadoOrdenCompraRepository;
import proyectoInvOp.back.Repositories.OrdenCompraRepository;

import java.util.List;

@Service
public class OrdenCompraServiceImpl extends BaseServiceImpl<OrdenCompra,Long> implements OrdenCompraService {
    @Autowired
    OrdenCompraRepository ordenCompraRepository;
    @Autowired
    EstadoOrdenCompraRepository estadoOrdenCompraRepository;

    public OrdenCompraServiceImpl(BaseRepository<OrdenCompra, Long> baseRepository, OrdenCompraRepository ordenCompraRepository) {
        super(baseRepository);
    }
    @Override
    public OrdenCompra save(OrdenCompra ordenCompra) throws Exception {
        try{
            boolean bandera = true;

            List<OrdenCompra> ordenCompraList = ordenCompraRepository.findAllByArticuloId(ordenCompra.getArticulo().getId());

            for (OrdenCompra ordenCompra1 : ordenCompraList){
                EstadoOrdenCompra estadoOrdenCompra = ordenCompra.getEstadoOrdenCompra();
                String estadoActual = estadoOrdenCompra.getNombre();
                if ("Pendiente".equals(estadoActual) || "En curso".equals(estadoActual)) {
                    bandera = false;
                    break;

                }
            }

            if (bandera){
                Articulo articulo = ordenCompra.getArticulo();

                Proveedor proveedorPredeterminado = articulo.getProveedorPredeterminado();

                ordenCompra.setProveedor(proveedorPredeterminado);

                EstadoOrdenCompra estadoOrdenCompra = estadoOrdenCompraRepository.findByNombre("Pendiente");

                ordenCompra.setEstadoOrdenCompra(estadoOrdenCompra);

                OrdenCompra ordenCompra1 = ordenCompraRepository.save(ordenCompra);

                return ordenCompra1;
            }else {
                throw new Exception("Error este articulo tiene una oreden de compra");
            }
        }catch (Exception e){
            throw new Exception("Error saving Orden compra", e);
        }


    }



}




