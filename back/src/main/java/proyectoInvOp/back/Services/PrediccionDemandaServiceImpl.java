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
}
