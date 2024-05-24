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

    @NotNull
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinColumn(name = "estadoOrdenId")
    private EstadoOrdenCompra estadoOrdenCompra;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<DetalleOrden> detallesOrden = new ArrayList<DetalleOrden>();
}
