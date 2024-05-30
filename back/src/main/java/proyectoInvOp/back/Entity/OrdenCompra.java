package proyectoInvOp.back.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "ordenCompra")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class OrdenCompra extends Base{


    private LocalDate fechaCreacion;

    private LocalDate fechaLlegada;

    private float montoTotal;

    private int cantidad;

    @NotNull
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinColumn(name = "estadoOrdenId")
    private EstadoOrdenCompra estadoOrdenCompra;

    @NotNull
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinColumn(name = "proveedorId")
    private Proveedor proveedor;

    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinColumn(name = "articuloId")
    private Articulo articulo;






}
