package proyectoInvOp.back.DTOS;

public class DTOParametroValor {
    private String nombreParametro;
    private int valorParametro;

    // Constructor
    public DTOParametroValor(String nombreParametro, int valorParametro) {
        this.nombreParametro = nombreParametro;
        this.valorParametro = valorParametro;
    }

    // Getters and setters
    public String getNombreParametro() {
        return nombreParametro;
    }

    public void setNombreParametro(String nombreParametro) {
        this.nombreParametro = nombreParametro;
    }

    public int getValorParametro() {
        return valorParametro;
    }

    public void setValorParametro(int valorParametro) {
        this.valorParametro = valorParametro;
    }
}
