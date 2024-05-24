package proyectoInvOp.back.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proyectoInvOp.back.Entity.Proveedor;
import proyectoInvOp.back.Services.ProveedorServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "proveedor")
public class ProveedorController extends BaseControllerImpl<Proveedor, ProveedorServiceImpl>{
}
