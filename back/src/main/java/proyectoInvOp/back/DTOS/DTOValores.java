package proyectoInvOp.back.DTOS;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DTOValores {
    private int month;
    private int year;
    private int cantidad;

    public DTOValores(int month, int year, int cantidad) {
        this.month = month;
        this.year = year;
        this.cantidad = cantidad;
    }

    // Getters y setters
}
