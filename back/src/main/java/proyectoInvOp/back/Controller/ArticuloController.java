package proyectoInvOp.back.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyectoInvOp.back.Entity.Articulo;
import proyectoInvOp.back.Services.ArticuloServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "articulo")
public class ArticuloController extends BaseControllerImpl<Articulo, ArticuloServiceImpl>{

    @PostMapping("/alta")
    public ResponseEntity<?> saveArticulo(@RequestBody Articulo articulo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.saveArticulo(articulo));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "{\"error\":\"Error porfavor intente mas tarde. \"}"
            );
        }
    }



}
