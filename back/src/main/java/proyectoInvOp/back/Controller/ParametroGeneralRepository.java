package proyectoInvOp.back.Controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proyectoInvOp.back.Entity.ParametroGeneral;
import proyectoInvOp.back.Services.ParametroGeneralServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "parametrogeneral")
public class ParametroGeneralRepository extends BaseControllerImpl<ParametroGeneral, ParametroGeneralServiceImpl> {
}
