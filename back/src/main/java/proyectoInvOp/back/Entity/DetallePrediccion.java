package proyectoInvOp.back.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "detalle prediccion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetallePrediccion extends Base{

    public int mes;
    public int año;
    public Double valorPredecido;
}
