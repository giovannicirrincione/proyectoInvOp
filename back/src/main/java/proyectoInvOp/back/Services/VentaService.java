package proyectoInvOp.back.Services;

import proyectoInvOp.back.Entity.Venta;

import java.time.LocalDate;

public interface VentaService extends BaseService<Venta, Long> {
    int demandaHistorica(Long id, LocalDate fechaDesde, LocalDate fechaHasta) throws Exception;
}