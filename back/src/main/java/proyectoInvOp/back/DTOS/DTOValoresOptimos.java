package proyectoInvOp.back.DTOS;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DTOValoresOptimos {
    public float factorPonderacion;
    public float cantidadPeriodosHistoricos;
    public Double alpha;
    public float prediccionRaiz;
}
