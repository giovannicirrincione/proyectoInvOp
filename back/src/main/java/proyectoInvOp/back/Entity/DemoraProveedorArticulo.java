package proyectoInvOp.back.Entity;

import jakarta.persistence.*;

@Entity
public class DemoraProveedorArticulo extends Base{
    private float tiempoDemora;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn (name = "articuloId")
    private Articulo articulo;
}
