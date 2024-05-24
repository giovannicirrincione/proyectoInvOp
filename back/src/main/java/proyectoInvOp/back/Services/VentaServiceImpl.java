package proyectoInvOp.back.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyectoInvOp.back.Entity.*;
import proyectoInvOp.back.Repositories.BaseRepository;
import proyectoInvOp.back.Repositories.DetalleVentaRepository;
import proyectoInvOp.back.Repositories.VentaRepository;

@Service
public class VentaServiceImpl extends BaseServiceImpl<Venta, Long> implements VentaService {
    public VentaServiceImpl(BaseRepository<Venta, Long> baseRepository, VentaRepository ventaRepository) {
        super(baseRepository);
    }

    @Autowired
    VentaRepository ventaRepository;
    @Autowired
    DetalleVentaRepository detalleVentaRepository;

    @Override
    public Venta saveVenta(Venta venta) throws Exception {
        ventaRepository.save(venta);

        return venta;
    }

    @Override
    public String bajaVenta(Long id) throws Exception {

        ventaRepository.bajaLogicaById(id);
        return "El artículo ha sido dado de baja";
    }
    
}