package proyectoInvOp.back.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyectoInvOp.back.Entity.Venta;
import proyectoInvOp.back.Services.VentaServiceImpl;

import java.time.LocalDate;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "venta")
public class VentaController extends BaseControllerImpl<Venta, VentaServiceImpl> {
    @GetMapping("/demandaHistorica/{id}")
    public ResponseEntity<?> demandaHistorica(@PathVariable Long id, @RequestParam LocalDate fechaDesde, @RequestParam LocalDate fechaHasta) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.demandaHistorica(id, fechaDesde, fechaHasta));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "{\"error\":\"Error porfavor intente mas tarde. \"}"
            );
        }
    }

}