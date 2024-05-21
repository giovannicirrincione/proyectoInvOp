package proyectoInvOp.back.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "datosModeloArticulo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DatoModeloArticulo extends Base{
    private String nombreDato;
}
