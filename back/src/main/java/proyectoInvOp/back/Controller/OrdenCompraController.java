package proyectoInvOp.back.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyectoInvOp.back.Entity.Articulo;
import proyectoInvOp.back.Entity.OrdenCompra;
import proyectoInvOp.back.Services.ArticuloServiceImpl;
import proyectoInvOp.back.Services.OrdenCompraServiceImpl;

import java.util.OptionalDouble;

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
}
