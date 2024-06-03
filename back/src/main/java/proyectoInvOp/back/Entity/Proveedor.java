package proyectoInvOp.back.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "proveedor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Proveedor extends Base{


    private String nombre;

    private String direccion;

    private String telefono;

    private String email;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<DemoraProveedorArticulo> demoraProveedorArticulos;


}
