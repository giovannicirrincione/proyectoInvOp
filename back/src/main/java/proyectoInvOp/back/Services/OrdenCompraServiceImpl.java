package proyectoInvOp.back.Services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.aspectj.weaver.ast.Or;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoInvOp.back.Entity.Articulo;
import proyectoInvOp.back.Entity.EstadoOrdenCompra;
import proyectoInvOp.back.Entity.OrdenCompra;
import proyectoInvOp.back.Entity.Proveedor;
import proyectoInvOp.back.PatronObservador.ArticuloObserver;
import proyectoInvOp.back.PatronObservador.OrdenCompraObservable;
import proyectoInvOp.back.Repositories.BaseRepository;
import proyectoInvOp.back.Repositories.EstadoOrdenCompraRepository;
import proyectoInvOp.back.Repositories.OrdenCompraRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenCompraServiceImpl extends BaseServiceImpl<OrdenCompra,Long> implements OrdenCompraService {
    @Autowired
    ArticuloObserver articuloObserver;
    @Autowired
    OrdenCompraRepository ordenCompraRepository;
    @Autowired
    EstadoOrdenCompraRepository estadoOrdenCompraRepository;
    @Autowired
    public OrdenCompraServiceImpl(BaseRepository<OrdenCompra, Long> baseRepository, ArticuloObserver articuloObserver) {
        super(baseRepository);
        this.articuloObserver = articuloObserver;

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

    @Transactional
    public boolean cambioEstado(OrdenCompra ordenCompra, Long id) throws Exception{
        try {
            //Se compara el estado seleccionado con el estado actual para verificar que se puede modificar
            //Si son iguales termina la funcion
            Optional<EstadoOrdenCompra> estadoOrdenCompraOptional = estadoOrdenCompraRepository.findActiveById(id);

            EstadoOrdenCompra estadoOrdenCompra = estadoOrdenCompraOptional.get();

                if (ordenCompra.getEstadoOrdenCompra().equals(estadoOrdenCompra)) {

                    throw new Exception("El estado seleccionado es igual al estado actual");
                }

                //Verificamos que el estado seleccionado sea uno posible

                //Estado actual
                String estadoActual = ordenCompra.getEstadoOrdenCompra().getNombre();

                //Logica para cada estado


                //Si esta en PENDIENTE, solo puede ser cambiada a EN CURSO
                if ("Pendiente".equals(estadoActual)) {
                    if ("Enviada".equals(estadoOrdenCompra.getNombre())) {
                        throw new Exception("No se puede asignar el estado");
                    }
                    //Agregar logica MODIFICACION DE ESTADO
                    ordenCompra.setEstadoOrdenCompra(estadoOrdenCompra);
                }

                //Si esta EN CURSO, solo puede ser cambiado a ENVIADO
                if ("En curso".equals(estadoActual)) {
                    if ("Pendiente".equals(estadoOrdenCompra.getNombre())) {
                        throw new Exception("No se puede asignar el estado");
                    }


                    ordenCompra.setEstadoOrdenCompra(estadoOrdenCompra);
                    super.update(ordenCompra.getId(),ordenCompra);

                    //Agregar logica de MODIFICACION DE ESTADO
                    OrdenCompraObservable ordenCompraObservable = new OrdenCompraObservable(ordenCompra);
                    ordenCompraObservable.addObserver(articuloObserver);
                    ordenCompraObservable.notifyObservers();

                }

                //Si esta ENVIADA, no se puede modificar el Estado
                if (estadoActual == "Enviada") {
                    throw new Exception("La orden de compra ya se ha entregado!");
                }

                return true;

            }
        catch(Exception e ){
                throw new Exception(e.getMessage());
            }


    }





}




