package proyectoInvOp.back.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyectoInvOp.back.Entity.Articulo;
import proyectoInvOp.back.Entity.Proveedor;
import proyectoInvOp.back.Services.ProveedorServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "proveedor")
public class ProveedorController extends BaseControllerImpl<Proveedor, ProveedorServiceImpl>{

    //devuelve todos los proveedores que brindan el articulo
    @GetMapping("/getProveedores/{id}")
    public ResponseEntity<?> getProveedoresArticulo(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.getProveedoresArticulo(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "{\"error\":\"Error porfavor intente mas tarde. \"}"
            );
        }
    }
}
