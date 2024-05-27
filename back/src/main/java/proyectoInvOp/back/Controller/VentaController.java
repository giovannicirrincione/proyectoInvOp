package proyectoInvOp.back.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyectoInvOp.back.Entity.Venta;
import proyectoInvOp.back.Services.VentaServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "venta")
public class VentaController extends BaseControllerImpl<Venta, VentaServiceImpl> {

}