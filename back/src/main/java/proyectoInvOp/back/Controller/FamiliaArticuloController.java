package proyectoInvOp.back.Entity.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proyectoInvOp.back.Entity.FamiliaArticulo;
import proyectoInvOp.back.Services.FamiliaArticuloServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "familia")
public class FamiliaArticuloController  extends BaseControllerImpl<FamiliaArticulo, FamiliaArticuloServiceImpl>{
}
