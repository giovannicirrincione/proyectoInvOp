package proyectoInvOp.back.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoInvOp.back.Entity.Articulo;
import proyectoInvOp.back.Entity.FamiliaArticulo;
import proyectoInvOp.back.Entity.ModeloInventario;
import proyectoInvOp.back.Repositories.ArticuloRepository;
import proyectoInvOp.back.Repositories.BaseRepository;
import proyectoInvOp.back.Repositories.FamiliaArticuloRepository;
import proyectoInvOp.back.Repositories.ModeloInventarioRepository;

import java.util.List;

@Service
public class ArticuloServiceImpl extends BaseServiceImpl<Articulo,Long> implements ArticuloService {

    @Autowired
    ArticuloRepository articuloRepository;
    @Autowired
    ModeloInventarioRepository modeloInventarioRepository;

    public ArticuloServiceImpl(BaseRepository<Articulo, Long> baseRepository, ArticuloRepository articuloRepository) {
        super(baseRepository);
    }

    @Override
    public Articulo saveArticulo(Articulo articulo) throws Exception {

        //implementar logica

        return articulo;
    }
}