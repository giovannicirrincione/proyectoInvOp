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
    //lO PUSE PQ SINO ME TRAE TODA LA BD
    @JsonIgnore

    @NotNull
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinColumn(name = "articuloId")
    private Articulo articulo;

    @NotNull
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinColumn(name = "metodoPrediccionId")
    private MetodoPrediccion metodoPrediccion;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<DetallePrediccion> detallePrediccions;

}
