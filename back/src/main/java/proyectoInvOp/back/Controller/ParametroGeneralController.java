package proyectoInvOp.back.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proyectoInvOp.back.Entity.Articulo;
import proyectoInvOp.back.Entity.ParametroGeneral;
import proyectoInvOp.back.Services.ArticuloServiceImpl;
import proyectoInvOp.back.Services.ParametroGeneralServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "parametroGeneral")
public class ParametroGeneralController  extends BaseControllerImpl<ParametroGeneral, ParametroGeneralServiceImpl> {
}
