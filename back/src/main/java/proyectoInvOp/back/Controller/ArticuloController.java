package proyectoInvOp.back.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyectoInvOp.back.DTOS.DTOArticuloMain;
import proyectoInvOp.back.Entity.Articulo;
import proyectoInvOp.back.Services.ArticuloServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "articulo")
public class ArticuloController extends BaseControllerImpl<Articulo, ArticuloServiceImpl> {

    @PostMapping("createArticulo")
    public ResponseEntity<?> createArticulo(@RequestBody DTOArticuloMain articuloRequestDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.createArticulo(articuloRequestDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "{\"error\":\"Error porfavor intente mas tarde. \"}"
            );
        }
    }
    @PutMapping("updateArticulo/{id}")
    public ResponseEntity<?> updateArticulo(@RequestBody DTOArticuloMain articuloRequestDTO, @PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.updateArticulo(id,articuloRequestDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "{\"error\":\"Error porfavor intente mas tarde. \"}"
            );
        }
    }
    @Override
    @PutMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            if(servicio.bajaArticulo(id)) {
                return ResponseEntity.status(HttpStatus.OK).body(servicio.bajaArticulo(id));
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        "{\"error\":\"estaEnOrden \"}"
                );

            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "{\"error\":\"Error porfavor intente mas tarde. \"}"
            );
        }
    }
    @GetMapping("getAll")
    public ResponseEntity<?> listarArticulos() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.listarArticulos());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "{\"error\":\"Error porfavor intente mas tarde. \"}"
            );
        }
    }
    @GetMapping("/getOne/{id}")
    public ResponseEntity<?> listarArticulos(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.listarArticuloById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "{\"error\":\"Error porfavor intente mas tarde. \"}"
            );
        }
    }

    @GetMapping("getFaltantes")
    public ResponseEntity<?> listarArticulosFaltantes() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.listarArticulosFaltantes());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "{\"error\":\"Error porfavor intente mas tarde. \"}"
            );
        }
    }

    @GetMapping("/reponer")
    public ResponseEntity<?> listarArticuloReponer(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.listarArticuloReponer());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "{\"error\":\"Error porfavor intente mas tarde. \"}"
            );
        }
    }

    @PutMapping("/cgi/{id}")
    public ResponseEntity<?> calcularCGI(@PathVariable Long id){

        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.calcularCGI(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "{\"error\":\"Error porfavor intente mas tarde. \"}"
            );
        }
    }

    @GetMapping("getAllDTO")
    public ResponseEntity<?> listarArticulosDTO() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findAllByDTO());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "{\"error\":\"Error porfavor intente mas tarde. \"}"
            );
        }
    }


}
