package proyectoInvOp.back;

import jakarta.persistence.EntityResult;
import jakarta.persistence.FieldResult;
import jakarta.persistence.SqlResultSetMapping;


public interface DTOArticulo {
    Long getId();
    String getNombre();
    int getStockActual();
    int getValorLoteOptimo();
    int getValorPuntoPedido();
    int getStockSeguridad();

}
