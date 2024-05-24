package proyectoInvOp.back.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyectoInvOp.back.Entity.Venta;
import proyectoInvOp.back.Services.VentaServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "venta")
public class VentaController extends BaseControllerImpl<Venta, VentaServiceImpl> {
    @PostMapping("/alta")
    public ResponseEntity<?> saveVenta(@RequestBody Venta venta) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.saveVenta(venta));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                "{\"error\":\"Error porfavor intente mas tarde. \"}"
            );
        }
    }

    @PostMapping("/baja/{id}")
        public ResponseEntity<?> bajaVenta(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.bajaVenta(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "{\"error\":\"Error porfavor intente mas tarde. \"}"
            );
        }
    }

}
