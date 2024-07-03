package proyectoInvOp.back.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proyectoInvOp.back.Entity.ArticuloDatoModeloArticulo;
import proyectoInvOp.back.Services.ArticuloDatoModeloArticuloServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "articuloDatoModeloArticulo")
public class ArticuloDatoModeloArticuloController extends BaseControllerImpl<ArticuloDatoModeloArticulo, ArticuloDatoModeloArticuloServiceImpl> {
}
