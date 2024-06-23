package proyectoInvOp.back.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proyectoInvOp.back.Entity.Articulo;
import proyectoInvOp.back.Entity.ParametroEspecifico;
import proyectoInvOp.back.Services.ArticuloServiceImpl;
import proyectoInvOp.back.Services.ParametroEspecificoServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "parametroEspecifico")
public class ParametroEspecificoController  extends BaseControllerImpl<ParametroEspecifico, ParametroEspecificoServiceImpl> {
}
