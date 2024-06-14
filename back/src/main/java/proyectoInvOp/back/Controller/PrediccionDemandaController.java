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


}
