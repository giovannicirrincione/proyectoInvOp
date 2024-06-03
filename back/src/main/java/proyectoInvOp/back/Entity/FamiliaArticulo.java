package proyectoInvOp.back.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Objects;

@Entity
@Table(name = "familiaArticulo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class FamiliaArticulo extends Base{


    private String nombre;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FamiliaArticulo that = (FamiliaArticulo) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(nombre, that.nombre);
    }

}
