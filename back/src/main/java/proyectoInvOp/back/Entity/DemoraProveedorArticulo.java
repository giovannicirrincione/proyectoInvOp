package proyectoInvOp.back.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "demoraProveedorArticulo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DemoraProveedorArticulo extends Base{

    private float costoPedido;

    private float tiempoDemora;

    private float precioArt;

    @NotNull
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn (name = "articuloId")
    private Articulo articulo;


}
