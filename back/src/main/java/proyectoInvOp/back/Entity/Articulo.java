package proyectoInvOp.back.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "articulo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Articulo extends Base {

    @Column(updatable = false)
    private String nombre;

    private String descripcion;

    private Integer stockActual;

    private Integer CGI;


    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinColumn(name = "modeloInventarioId")
    private ModeloInventario modeloInventario;

    @NotNull
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinColumn(name = "familiaArticuloId")
    private FamiliaArticulo familiaArticulo;

    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinColumn(name = "proveedorPredeterminadoId")
    private Proveedor proveedorPredeterminado;

    @JsonIgnore
    @ManyToMany (cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "ArticuloProveedor",
            joinColumns = @JoinColumn(name = "articuloId"),
            inverseJoinColumns = @JoinColumn(name = "proveedorId")
    )
    private List<Proveedor> proveedores;


}
