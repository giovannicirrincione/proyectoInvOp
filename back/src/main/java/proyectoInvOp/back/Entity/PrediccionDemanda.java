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
@Table(name = "prediccionDemanda")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrediccionDemanda extends  Base{


    private LocalDate fechaDesde;

    private LocalDate fechaHasta;

    private float valorPredecido;

    private List<Double> predicciones;

    @NotNull
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinColumn(name = "articuloId")
    private Articulo articulo;

    @NotNull
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinColumn(name = "metodoPrediccionId")
    private MetodoPrediccion metodoPrediccion;

    public PrediccionDemanda(List<Double> predicciones) {
        this.predicciones = predicciones;
    }

    public List<Double> getPredicciones() {
        return predicciones;
    }

}
