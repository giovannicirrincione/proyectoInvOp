package proyectoInvOp.back.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoInvOp.back.DTOS.DTOValores;
import proyectoInvOp.back.DTOS.DTOValoresOptimos;
import proyectoInvOp.back.Entity.Articulo;
import proyectoInvOp.back.Entity.MetodoPrediccion;
import proyectoInvOp.back.Entity.PrediccionDemanda;
import proyectoInvOp.back.Factory.FactoryEstrategiaPrediccionDemanda;
import proyectoInvOp.back.Factory.FactorySimulacionSeleccionParametros;
import proyectoInvOp.back.Repositories.BaseRepository;
import proyectoInvOp.back.Repositories.MetodoPrediccionRepository;
import proyectoInvOp.back.Repositories.VentaRepository;
import proyectoInvOp.back.Strategy.EstrategiaSimulacion;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrediccionDemandaServiceImpl extends BaseServiceImpl<PrediccionDemanda,Long> {
    @Autowired
    MetodoPrediccionRepository metodoPrediccionRepository;
    @Autowired
    VentaRepository ventaRepository;

    public PrediccionDemandaServiceImpl(BaseRepository<PrediccionDemanda, Long> baseRepository) {
        super(baseRepository);
    }

    public PrediccionDemanda predecirDemanda(Articulo articulo) {

        //Trameos los metodos de preciccion de la BD
        List<MetodoPrediccion> metodoPrediccions = metodoPrediccionRepository.findAllActive();
        //Llamamos a la factory q luego nos servira dentro del bucle FOR
        FactorySimulacionSeleccionParametros factorySimulacion = FactorySimulacionSeleccionParametros.getInstancia();
        //Cuantos meses atras vamos a predecir
        LocalDate fechaInicio = LocalDate.now().minusMonths(12);
        //Recorremos los metodos de predicccion
        for (MetodoPrediccion metodo : metodoPrediccions) {

            EstrategiaSimulacion estrategiaSimulacion = factorySimulacion.obtenerEstrategiaSimulacion(metodo);
            //Le mandamos las ventas reales
            //Lo q haria aca es mandarle una lista tipo asi [1,2,3,5,2,32,4,5] que cada campo sean las ventas reales pasadas por mes
            List<DTOValores> DTOvalores = ventaRepository.findCantidadVendidaPorMes(articulo.getId(),fechaInicio);
            DTOValoresOptimos valoresOptimos = estrategiaSimulacion.simular(DTOvalores);


        }

        return new PrediccionDemanda();
    }
}
