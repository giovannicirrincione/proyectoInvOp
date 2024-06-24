package proyectoInvOp.back.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proyectoInvOp.back.Entity.ProveedorArticulo;
import proyectoInvOp.back.Services.DemoraProveedorArticuloServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "demoraProveedorArticulo")
public class DemoraProveedorArticuloController  extends BaseControllerImpl<ProveedorArticulo, DemoraProveedorArticuloServiceImpl>{
}
