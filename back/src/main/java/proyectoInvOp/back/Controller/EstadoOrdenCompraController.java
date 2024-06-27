package proyectoInvOp.back.Controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proyectoInvOp.back.Entity.EstadoOrdenCompra;
import proyectoInvOp.back.Services.EstadoOrdenCompraServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "estadoOC")
public class EstadoOrdenCompraController extends BaseControllerImpl<EstadoOrdenCompra, EstadoOrdenCompraServiceImpl>{
}
