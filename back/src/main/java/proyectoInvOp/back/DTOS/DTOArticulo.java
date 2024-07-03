package proyectoInvOp.back.DTOS;

public class DTOArticulo {

    private Long id;
    private String nombre;
    private int stockActual;
    private int valorLoteOptimo;
    private int valorPuntoPedido;
    private int stockSeguridad;

    public DTOArticulo() {
    }

    public DTOArticulo(Long id, String nombre, int stockActual, int valorLoteOptimo, int valorPuntoPedido, int stockSeguridad) {
        this.id = id;
        this.nombre = nombre;
        this.stockActual = stockActual;
        this.valorLoteOptimo = valorLoteOptimo;
        this.valorPuntoPedido = valorPuntoPedido;
        this.stockSeguridad = stockSeguridad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    public int getValorLoteOptimo() {
        return valorLoteOptimo;
    }

    public void setValorLoteOptimo(int valorLoteOptimo) {
        this.valorLoteOptimo = valorLoteOptimo;
    }

    public int getValorPuntoPedido() {
        return valorPuntoPedido;
    }

    public void setValorPuntoPedido(int valorPuntoPedido) {
        this.valorPuntoPedido = valorPuntoPedido;
    }

    public int getStockSeguridad() {
        return stockSeguridad;
    }

    public void setStockSeguridad(int stockSeguridad) {
        this.stockSeguridad = stockSeguridad;
    }
}
