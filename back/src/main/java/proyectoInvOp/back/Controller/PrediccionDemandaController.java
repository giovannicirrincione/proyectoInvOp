package proyectoInvOp.back.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyectoInvOp.back.Entity.Articulo;
import proyectoInvOp.back.Entity.OrdenCompra;
import proyectoInvOp.back.Entity.PrediccionDemanda;
import proyectoInvOp.back.Exeptions.PrediccionesFoundException;
import proyectoInvOp.back.Services.PrediccionDemandaServiceImpl;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "predicciondemanda")
public class PrediccionDemandaController extends BaseControllerImpl<PrediccionDemanda, PrediccionDemandaServiceImpl> {

    @PostMapping("/predecirDemanda/id")
    public ResponseEntity<?> predecirDemanda(@PathVariable Long id,@RequestBody int cantPeriodos) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.predecirDemanda(id, cantPeriodos));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "{\"error\":\"Error porfavor intente mas tarde. \"}"
            );
        }
    }

    @ExceptionHandler(PrediccionesFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // O el código de estado que desees
    public String handleNoPrediccionesFound(PrediccionesFoundException e) {
        return e.getMessage();
    }


}
