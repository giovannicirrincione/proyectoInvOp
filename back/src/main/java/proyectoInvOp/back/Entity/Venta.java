package proyectoInvOp.back.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "venta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Venta extends Base{


    private LocalDate fechaVenta;

    private float montoTotal;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<DetalleVenta> detalleVentas;
}
