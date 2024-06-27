package proyectoInvOp.back.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proyectoInvOp.back.Entity.DatoModeloArticulo;
import proyectoInvOp.back.Services.DatoModeloArticuloServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "datoModeloArticulo")
public class DatoModeloArticuloController extends BaseControllerImpl<DatoModeloArticulo, DatoModeloArticuloServiceImpl>{
}
