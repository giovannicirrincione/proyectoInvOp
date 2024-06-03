package proyectoInvOp.back.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proyectoInvOp.back.Entity.Articulo;
import proyectoInvOp.back.Entity.ModeloInventario;
import proyectoInvOp.back.Services.ArticuloServiceImpl;
import proyectoInvOp.back.Services.ModeloInventarioServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "modInv")
public class ModeloInventarioController extends BaseControllerImpl<ModeloInventario, ModeloInventarioServiceImpl>{
}
