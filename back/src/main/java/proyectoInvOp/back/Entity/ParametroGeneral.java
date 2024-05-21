package proyectoInvOp.back.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Table(name = "parametroGeneral")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParametroGeneral extends Base{


    private LocalDate fechaAlta;

    private LocalDate fechaBaja;

    private String nombreParametro;

    private int valorParametro;

}
