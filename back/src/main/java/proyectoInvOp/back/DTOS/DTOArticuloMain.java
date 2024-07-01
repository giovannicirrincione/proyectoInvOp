package proyectoInvOp.back.DTOS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import proyectoInvOp.back.Entity.Articulo;
import proyectoInvOp.back.Entity.Proveedor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class DTOArticuloMain {
    private Long id;
    private int tpoEntreControlesStock;
    private String nombre;
    private String descripcion;
    private Integer stockActual;
    private float CGI;
    private float precioVenta;
    private Integer demanda;
    private Float costoAlmacenamiento;
    private Long modeloInventarioId;
    private Long familiaArticuloId;
    private Long proveedorPredeterminadoId;

    public DTOArticuloMain(Articulo articulo) {
        this.id = articulo.getId();
        this.tpoEntreControlesStock = articulo.getTpoEntreControlesStock();
        this.nombre = articulo.getNombre();
        this.descripcion = articulo.getDescripcion();
        this.stockActual = articulo.getStockActual();
        this.CGI = articulo.getCGI();
        this.precioVenta = articulo.getPrecioVenta();
        this.demanda = articulo.getDemanda();
        this.costoAlmacenamiento = articulo.getCostoAlmacenamiento();
        this.modeloInventarioId = articulo.getModeloInventario() != null ? articulo.getModeloInventario().getId() : null;
        this.familiaArticuloId = articulo.getFamiliaArticulo().getId();
        this.proveedorPredeterminadoId = articulo.getProveedorPredeterminado() != null ? articulo.getProveedorPredeterminado().getId() : null;
    }
}
