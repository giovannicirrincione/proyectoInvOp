package proyectoInvOp.back.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoInvOp.back.Entity.PrediccionDemanda;
import proyectoInvOp.back.Repositories.BaseRepository;
import proyectoInvOp.back.Repositories.PrediccionDemandaRepository;

import java.time.YearMonth;
import java.util.List;

@Service
public class PrediccionDemandaServiceImpl extends BaseServiceImpl<PrediccionDemanda, Long> implements PrediccionDemandaService{

    @Autowired
    private PrediccionDemandaRepository prediccionDemandaRepository;

    public PrediccionDemandaServiceImpl(BaseRepository<PrediccionDemanda, Long> baseRepository, PrediccionDemandaRepository prediccionDemandaRepository) {
        super(baseRepository);
        this.prediccionDemandaRepository = prediccionDemandaRepository;
    }

    @Override
    public List<Double> searchPorFecha(YearMonth desde, YearMonth hasta) throws Exception{
        try{
            List<Double> demandas = prediccionDemandaRepository.searchPorFecha(desde, hasta);
            return demandas;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}