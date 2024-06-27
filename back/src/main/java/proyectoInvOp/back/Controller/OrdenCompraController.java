package proyectoInvOp.back.Entity.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyectoInvOp.back.Entity.OrdenCompra;
import proyectoInvOp.back.Services.OrdenCompraServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "ordencompra")
public class OrdenCompraController extends BaseControllerImpl<OrdenCompra, OrdenCompraServiceImpl> {
    @Override
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody OrdenCompra ordenCompra) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.save(ordenCompra));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "{\"error\":\"Error porfavor intente mas tarde. \"}"
            );
        }
    }
    @PutMapping("/cambioEstado/{id}")
    public ResponseEntity<?> cambioEstado(@RequestBody OrdenCompra ordenCompra,@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.cambioEstado(ordenCompra, id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "{\"error\":\"Error porfavor intente mas tarde. \"}"
            );
        }
    }
}
