package proyectoInvOp.back.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.YearMonth;

@Entity
@Table(name = "prediccionDemanda")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrediccionDemanda extends  Base{


    private YearMonth mesAnio;

    private float valorPredecido;

    @NotNull
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinColumn(name = "articuloId")
    private Articulo articulo;

    @NotNull
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinColumn(name = "metodoPrediccionId")
    private MetodoPrediccion metodoPrediccion;
}
