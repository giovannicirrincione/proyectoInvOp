package proyectoInvOp.back.Controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proyectoInvOp.back.Entity.ParametroEspecifico;
import proyectoInvOp.back.Services.BaseServiceImpl;
import proyectoInvOp.back.Services.ParametroEspecificoServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "parametroespecifico")
public class ParametroEspecificoController extends BaseControllerImpl<ParametroEspecifico, ParametroEspecificoServiceImpl>{
}

