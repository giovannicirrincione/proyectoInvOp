package proyectoInvOp.back.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "modeloInventario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModeloInventario extends Base{


    private String nombre;

    private String descripcion;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private List<FamiliaArticulo> familiaArticulos;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "ModeloInventarioDatosModelo",
            joinColumns = @JoinColumn(name = "modeloInventarioId"),
            inverseJoinColumns = @JoinColumn(name = "datoModeloId")
    )
    private List<DatoModeloArticulo> datosModeloArticulos;
}
