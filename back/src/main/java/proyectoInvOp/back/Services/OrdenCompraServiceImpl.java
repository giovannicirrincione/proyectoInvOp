package proyectoInvOp.back.Services;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoInvOp.back.Entity.*;
import proyectoInvOp.back.PatronObservador.ArticuloObserver;
import proyectoInvOp.back.PatronObservador.OrdenCompraObservable;
import proyectoInvOp.back.Repositories.ArticuloRepository;
import proyectoInvOp.back.Repositories.BaseRepository;
import proyectoInvOp.back.Repositories.EstadoOrdenCompraRepository;
import proyectoInvOp.back.Repositories.OrdenCompraRepository;

import java.time.LocalDate;
import java.util.ArrayList;
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
    ArticuloRepository articuloRepository;
    @Autowired
    public OrdenCompraServiceImpl(BaseRepository<OrdenCompra, Long> baseRepository, ArticuloObserver articuloObserver) {
        super(baseRepository);
        this.articuloObserver = articuloObserver;

    }
    @Override
    public OrdenCompra save(OrdenCompra ordenCompra) throws Exception {
        try{
            System.out.println("entre al save");
            boolean bandera = true;

            List<OrdenCompra> ordenCompraList = new ArrayList<>();

            ordenCompraList = ordenCompraRepository.findAllByArticuloId(ordenCompra.getArticulo().getId());

            //check de otra orden de compra activa para el articulo
            for (OrdenCompra ordenCompra1 : ordenCompraList){

                EstadoOrdenCompra estadoOrdenCompra = ordenCompra1.getEstadoOrdenCompra();
                String estadoActual = estadoOrdenCompra.getNombre();
                if ("Pendiente".equals(estadoActual) || "En curso".equals(estadoActual)) {
                    bandera = false;
                    break;

                }
            }

            if (bandera){

                Long idArt = ordenCompra.getArticulo().getId();

                Optional<Articulo> articuloBD = articuloRepository.findActiveById(idArt);

                Articulo articulo = articuloBD.get();

                Proveedor proveedorPredeterminado = articulo.getProveedorPredeterminado();
                System.out.println(proveedorPredeterminado.getNombre());

                //Busco el precio del articulo con ese proveedor

                float precioArt = 0;

                List<DemoraProveedorArticulo> demoraProveedorArticulos = proveedorPredeterminado.getDemoraProveedorArticulos();

                for (DemoraProveedorArticulo demoraProveedorArticulo : demoraProveedorArticulos){

                    Articulo articulo1 = demoraProveedorArticulo.getArticulo();

                    if (articulo1.getId() == articulo.getId()){
                        precioArt = demoraProveedorArticulo.getPrecioArt();
                        break;

                    }


                }

                ordenCompra.setProveedor(proveedorPredeterminado);

                ordenCompra.setMontoTotal(ordenCompra.getCantidad()*precioArt);

                EstadoOrdenCompra estadoOrdenCompra = estadoOrdenCompraRepository.findByNombre("En curso");


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

                    ordenCompra.setFechaLlegada(LocalDate.now());
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




