package proyectoInvOp.back.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proyectoInvOp.back.Entity.Articulo;
import proyectoInvOp.back.Services.ArticuloServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "articulo")
public class ArticuloController extends BaseControllerImpl<Articulo, ArticuloServiceImpl>{

}
