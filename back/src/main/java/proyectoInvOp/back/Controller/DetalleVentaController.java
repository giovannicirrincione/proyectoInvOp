package proyectoInvOp.back.Controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proyectoInvOp.back.Services.DetalleVentaServiceImpl;
import proyectoInvOp.back.Entity.DetalleVenta;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "detalleventa")
public class DetalleVentaController extends BaseControllerImpl<DetalleVenta,DetalleVentaServiceImpl> {
}
