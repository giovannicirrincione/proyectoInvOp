package proyectoInvOp.back.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "estadoOrdenCompra")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstadoOrdenCompra extends Base{


    private String nombre;

}
