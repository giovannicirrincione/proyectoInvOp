package proyectoInvOp.back.DTOS;

public class DTOVentas {
    private int mes;
    private int cantidadVentas;

    public DTOVentas(int mes, int cantidadVentas) {
        this.mes = mes;
        this.cantidadVentas = cantidadVentas;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getCantidadVentas() {
        return cantidadVentas;
    }

    public void setCantidadVentas(int cantidadVentas) {
        this.cantidadVentas = cantidadVentas;
    }
}