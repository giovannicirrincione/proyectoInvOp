package proyectoInvOp.back.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Base implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaAlta;
    private LocalDate fechaBaja;
    private LocalDate fechaModificacion;

    //Antes de que se persista cualquier entidad se le seteara la fechaAlta
    @PrePersist
    protected void onCreate() {
        this.fechaAlta = LocalDate.now();
    }

}
