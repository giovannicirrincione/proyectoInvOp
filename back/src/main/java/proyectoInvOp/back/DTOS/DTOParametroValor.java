package proyectoInvOp.back.DTOS;

public class DTOParametroValor {
    private String nombreParametro;
    private float valorParametro;

    // Constructor
    public DTOParametroValor(String nombreParametro, float valorParametro) {
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

    public float getValorParametro() {
        return valorParametro;
    }

    public void setValorParametro(float valorParametro) {
        this.valorParametro = valorParametro;
    }
}
