package proyectoInvOp.back.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import java.util.List;

@Entity
@Table(name = "metodoPrediccion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MetodoPrediccion extends Base{


    private String Nombre;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ParametroEspecifico> parametrosEspecificos;
}
