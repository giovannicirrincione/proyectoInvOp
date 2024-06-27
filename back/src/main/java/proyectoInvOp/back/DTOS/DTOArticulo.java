package proyectoInvOp.back.DTOS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DTOArticulo {

    private Long Id;

    private String nombre;

    private int stockActual;

    private int valorLoteOptimo;

    private int valorPuntoPedido;

    private int stockSeguridad;

}
