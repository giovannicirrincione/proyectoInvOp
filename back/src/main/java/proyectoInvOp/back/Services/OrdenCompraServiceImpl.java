package proyectoInvOp.back.Services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoInvOp.back.Entity.EstadoOrdenCompra;
import proyectoInvOp.back.Entity.OrdenCompra;
import proyectoInvOp.back.PatronObservador.ArticuloObserver;
import proyectoInvOp.back.PatronObservador.OrdenCompraObservable;
import proyectoInvOp.back.Repositories.BaseRepository;
import proyectoInvOp.back.Repositories.OrdenCompraRepository;

@Service
public class OrdenCompraServiceImpl extends BaseServiceImpl<OrdenCompra,Long> implements OrdenCompraService {
    @Autowired
    OrdenCompraRepository ordenCompraRepository;

    @Autowired
    ArticuloObserver articuloObserver;

    public OrdenCompraServiceImpl(BaseRepository<OrdenCompra, Long> baseRepository, OrdenCompraRepository ordenCompraRepository, ArticuloObserver articuloObserver) {
        super(baseRepository);
        this.articuloObserver = articuloObserver;
    }

    //Funcion para el cambio de estado de una OrdenCompra
    @Transactional
    public boolean cambioEstado(OrdenCompra ordenCompra, EstadoOrdenCompra estadoOrdenCompra) throws Exception{
        try {
            //Se compara el estado seleccionado con el estado actual para verificar que se puede modificar
            //Si son iguales termina la funcion
            if(!ordenCompra.getEstadoOrdenCompra().equals(estadoOrdenCompra)){
                throw new Exception("El estado seleccionado es igual al estado actual");
            }

            //Verificamos que el estado seleccionado sea uno posible

            //Estado actual
            String estadoActual = ordenCompra.getEstadoOrdenCompra().getNombre();

            //Logica para cada estado


            //Si esta en PENDIENTE, solo puede ser cambiada a EN CURSO
            if(estadoActual == "Pendiente"){
                if(estadoOrdenCompra.getNombre() == "Enviada"){
                    throw new Exception("No se puede asignar el estado");
                }
                //Agregar logica MODIFICACION DE ESTADO
                ordenCompra.setEstadoOrdenCompra(estadoOrdenCompra);
            }

            //Si esta EN CURSO, solo puede ser cambiado a ENVIADO
            if(estadoActual == "En curso"){
                if(estadoOrdenCompra.getNombre() == "Pendiente"){
                    throw new Exception("No se puede asignar el estado");
                }
                //Agregar logica de MODIFICACION DE ESTADO
                ordenCompra.setEstadoOrdenCompra(estadoOrdenCompra);
                //Agregar OBSERVADOR

                OrdenCompraObservable ordenCompraObservable = new OrdenCompraObservable(ordenCompra);
                ordenCompraObservable.addObserver(articuloObserver);
                ordenCompraObservable.notifyObservers();


            }

            //Si esta ENVIADA, no se puede modificar el Estado
            if(estadoActual == "Enviada"){
                throw new Exception("La orden de compra ya se ha entregado!");
            }

            return true;
        }
        catch (Exception e ){
            throw  new Exception(e.getMessage());
        }


    }



}




