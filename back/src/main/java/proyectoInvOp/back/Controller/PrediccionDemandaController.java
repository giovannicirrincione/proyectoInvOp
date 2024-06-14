package proyectoInvOp.back.Controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyectoInvOp.back.Entity.PrediccionDemanda;
import proyectoInvOp.back.Services.PrediccionDemandaServiceImpl;

import java.time.Year;
import java.time.YearMonth;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "prediccionDemanda")
public class PrediccionDemandaController extends BaseControllerImpl<PrediccionDemanda, PrediccionDemandaServiceImpl>{

    @GetMapping("/searchPorFecha")
    public ResponseEntity<?> searchPorFecha(@RequestParam YearMonth desde, YearMonth hasta){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.searchPorFecha(desde,hasta));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "{\"error\":\"Error porfavor intente mas tarde. \"}"
            );
        }
    }
}
