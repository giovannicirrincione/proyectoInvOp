package proyectoInvOp.back.DTOS;

public interface DTOArticulo {
    Long getId();
    String getNombre();
    int getStockActual();
    int getValorLoteOptimo();
    int getValorPuntoPedido();
    int getStockSeguridad();

}
