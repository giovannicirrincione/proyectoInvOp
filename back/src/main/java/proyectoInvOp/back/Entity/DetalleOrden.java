package proyectoInvOp.back.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "detalleOrden")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleOrden extends Base{


    private int cantidad;

    private float subTotal;

    @NotNull
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinColumn(name = "articuloId")
    private Articulo articulo;

    @NotNull
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinColumn(name = "proveedorId")
    private Proveedor proveedor;
}
