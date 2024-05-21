package proyectoInvOp.back.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "articulo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Articulo extends Base {


    private String nombre;

    private String descripcion;

    private int stockActual;

    private int CGI;

    private LocalDate fechaAlta;

    private LocalDate fechaBaja;

    private LocalDate fechaModificacion;

    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinColumn(name = "modeloInventarioId")
    private ModeloInventario modeloInventario;

    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinColumn(name = "familiaArticuloId")
    private FamiliaArticulo familiaArticulo;

    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinColumn(name = "provedorDeterminadoId")
    private Proveedor proveedorPredeterminado;

    @ManyToMany (cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "ArticuloProveedor",
            joinColumns = @JoinColumn(name = "articuloId"),
            inverseJoinColumns = @JoinColumn(name = "proveedorId")
    )
    private List<Proveedor> proveedores;


}
