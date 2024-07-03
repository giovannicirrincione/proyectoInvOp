package proyectoInvOp.back.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyectoInvOp.back.Entity.DatoModeloArticulo;
import proyectoInvOp.back.Services.DatoModeloArticuloServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "datoModeloArticulo")
public class DatoModeloArticuloController extends BaseControllerImpl<DatoModeloArticulo, DatoModeloArticuloServiceImpl>{

    @GetMapping("/getByArticulo/{id}")
    public ResponseEntity<?> datosModeloByArticulo(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.datosModeloByArticulo(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "{\"error\":\"Error porfavor intente mas tarde. \"}"
            );
        }
    }
}
