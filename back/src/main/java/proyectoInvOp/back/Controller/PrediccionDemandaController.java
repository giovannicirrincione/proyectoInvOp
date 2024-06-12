package proyectoInvOp.back.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyectoInvOp.back.Entity.Articulo;
import proyectoInvOp.back.Entity.OrdenCompra;
import proyectoInvOp.back.Entity.PrediccionDemanda;
import proyectoInvOp.back.Services.PrediccionDemandaServiceImpl;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "predicciondemanda")
public class PrediccionDemandaController extends BaseControllerImpl<PrediccionDemanda, PrediccionDemandaServiceImpl> {

    @PostMapping("/predecirDemanda")
    public ResponseEntity<?> predecirDemanda(Articulo articulo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.predecirDemanda(articulo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "{\"error\":\"Error porfavor intente mas tarde. \"}"
            );
        }
    }
}
