package proyectoInvOp.back.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "demoraProveedorArticulo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DemoraProveedorArticulo extends Base{


    private float tiempoDemora;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn (name = "articuloId")
    private Articulo articulo;


}
