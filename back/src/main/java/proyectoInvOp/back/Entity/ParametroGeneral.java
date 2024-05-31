package proyectoInvOp.back.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "parametroGeneral")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParametroGeneral extends Base{

    private String nombreParametro;

    private int valorParametro;

}

