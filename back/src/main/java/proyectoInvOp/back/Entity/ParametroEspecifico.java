package proyectoInvOp.back.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Table(name = "parametroEspecifico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class ParametroEspecifico extends Base{

    private String nombreParametro;

    private int valorParametro;
}
