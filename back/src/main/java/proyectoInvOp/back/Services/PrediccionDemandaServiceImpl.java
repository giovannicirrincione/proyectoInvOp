package proyectoInvOp.back.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoInvOp.back.DTOS.DTOParametroValor;
import proyectoInvOp.back.DTOS.DTOResultadoSimu;
import proyectoInvOp.back.DTOS.DTOVentas;
import proyectoInvOp.back.Entity.*;
import proyectoInvOp.back.Exeptions.PrediccionesFoundException;
import proyectoInvOp.back.Factory.FactoryEstrategiaPrediccionDemanda;
import proyectoInvOp.back.Factory.FactorySimulacionSeleccionParametros;
import proyectoInvOp.back.Repositories.*;
import proyectoInvOp.back.Strategy.EstrategiaPrediccionDemanda;
import proyectoInvOp.back.Strategy.EstrategiaSimulacion;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class PrediccionDemandaServiceImpl extends BaseServiceImpl<PrediccionDemanda,Long> {
    @Autowired
    MetodoPrediccionRepository metodoPrediccionRepository;
    @Autowired
    VentaRepository ventaRepository;
    @Autowired
    PrediccionDemandaRepository prediccionDemandaRepository;
    @Autowired
    ArticuloRepository articuloRepository;
    @Autowired
    ParametroGeneralRepository parametroGeneralRepository;
    @Autowired
    ParametrosEspecificosRepository parametrosEspecificosRepository;

    public PrediccionDemandaServiceImpl(BaseRepository<PrediccionDemanda, Long> baseRepository) {
        super(baseRepository);
    }

    public PrediccionDemanda predecirDemanda(Long id, int cantPeriodos) throws Exception{


        //Fijarse si ya hay una prediccion hecha
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaFinal = fechaActual.plus(cantPeriodos, ChronoUnit.MONTHS);

        List<PrediccionDemanda> prediccionDemandas = prediccionDemandaRepository.findPrediccionesByArticuloAndPeriodo(id,fechaActual,fechaFinal);

        //si la lista contiene algo fin CU
        if(!prediccionDemandas.isEmpty()){
            throw new PrediccionesFoundException("Ya existen predicciones para ese periodo");
        }
        //Obtengo el articulo de la BD
        Optional<Articulo> optionalArticulo = articuloRepository.findActiveById(id);
        Articulo articulo = optionalArticulo.get();

        //Buscamos el parametro general de los meses atras a comparar (PeriodosHistoricos)

        List<ParametroGeneral> parametroGenerales = parametroGeneralRepository.findAllActive();



        int mesesAtras = 0;

        for(ParametroGeneral parametroGeneral : parametroGenerales){
            String nombre = parametroGeneral.getNombreParametro();
            if ("Periodos historicos".equals(nombre)){
                mesesAtras = parametroGeneral.getValorParametro();
                break;
            }
        }
        //obtenemos las ventas historicas
        List<DTOVentas> ventasHistoricas = obtenerDemandaHistorica(id,mesesAtras);

        //for (DTOVentas v: ventasHistoricas) {
            //    System.out.println(v.getCantidadVentas());
        //}

        //obtenemos los parametros
        List<DTOParametroValor> listaParametros = obtenerParametros();

        //for (DTOParametroValor par: listaParametros) {
        //    System.out.println(par.getNombreParametro());
        //}

        //Obtengo todos los metodos de prediccion
        List<MetodoPrediccion> metodoPrediccion = metodoPrediccionRepository.findAllActive();



        //simulo
        DTOResultadoSimu resultadoSimu = obtenerResultadosSimulacion(ventasHistoricas,listaParametros,metodoPrediccion);

        //Uso los parametros de la mejor simulacion para predecir el futuro

        String nombreMetodo = resultadoSimu.getNombreMetodo();

        //obtengo la factory
        FactoryEstrategiaPrediccionDemanda factoryEstrategiaPrediccionDemanda = FactoryEstrategiaPrediccionDemanda.getInstancia();

        //obtengo la estrategia a usar
        EstrategiaPrediccionDemanda estrategiaPrediccionDemanda = factoryEstrategiaPrediccionDemanda.obtenerEstrategiaPrediccionDemanda(nombreMetodo);

        //uso la estrategia para predecir la DEMANDA FUTURA
        PrediccionDemanda prediccionDemanda = estrategiaPrediccionDemanda.predecirDemanda(ventasHistoricas,cantPeriodos,resultadoSimu);

        return prediccionDemanda;







    }
    public List<DTOVentas> obtenerDemandaHistorica (Long id, int mesesAtras){


        LocalDate fechaFin = LocalDate.now();
        LocalDate fechaInicio = fechaFin.minusMonths(mesesAtras);


        List<DTOVentas> ventas = ventaRepository.findVentasMensualesByArticulo(id, fechaInicio, fechaFin);

        for (DTOVentas v: ventas) {
            System.out.println(v.getMes()+" "+v.getCantidadVentas());
        }

        return ventas;

    }

    public  List<DTOParametroValor> obtenerParametros(){

        List<DTOParametroValor> listaParametros = new ArrayList<>();

        // Obtener parámetros generales
        List<ParametroGeneral> parametrosGenerales = parametroGeneralRepository.findAllActive();
        for (ParametroGeneral parametro : parametrosGenerales) {
            DTOParametroValor dto = new DTOParametroValor(parametro.getNombreParametro(), parametro.getValorParametro());
            listaParametros.add(dto);
        }
        // Obtener parámetros específicos
        List<ParametroEspecifico> parametrosEspecificos = parametrosEspecificosRepository.findAllActive();
        for (ParametroEspecifico parametro : parametrosEspecificos) {
            DTOParametroValor dto = new DTOParametroValor(parametro.getNombreParametro(), parametro.getValorParametro());
            listaParametros.add(dto);
        }

        return listaParametros;

    }

    public DTOResultadoSimu obtenerResultadosSimulacion(List<DTOVentas> ventas, List<DTOParametroValor> parametros, List<MetodoPrediccion> metodos){
        //creamos la factory
        FactorySimulacionSeleccionParametros factorySimulacionSeleccionParametros = FactorySimulacionSeleccionParametros.getInstancia();
        //creamos la lista de las simulaciones
        List<DTOResultadoSimu> resultadoSimusList = new ArrayList<>();

        for (MetodoPrediccion metodoPrediccion : metodos){
            //obtengo la estrategia
            EstrategiaSimulacion estrategiaSimulacion = factorySimulacionSeleccionParametros.obtenerEstrategiaSimulacion(metodoPrediccion);
            //simulo
            DTOResultadoSimu resultadoSimu = estrategiaSimulacion.simular(ventas,parametros);
            //agrego el resultado a la lista
            resultadoSimusList.add(resultadoSimu);
        }

        DTOResultadoSimu resultadoMenorError = obtenerMenorError(resultadoSimusList);

        return resultadoMenorError;

    }
    public DTOResultadoSimu obtenerMenorError(List<DTOResultadoSimu> resultadoSimus){
        Optional<DTOResultadoSimu> simulacionConMenorError = resultadoSimus.stream()
                .min(Comparator.comparingDouble(DTOResultadoSimu::getErrorObtenido));

        return simulacionConMenorError.orElse(null);
    }
}
