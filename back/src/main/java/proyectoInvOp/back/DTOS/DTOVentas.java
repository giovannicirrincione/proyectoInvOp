package proyectoInvOp.back.DTOS;

import java.time.LocalDate;

public class DTOVentas {
    private LocalDate fecha;
    private int cantidadVentas;

    public DTOVentas(LocalDate fecha, int cantidadVentas) {

        this.cantidadVentas = cantidadVentas;
        this.fecha = fecha;

    }


    public LocalDate getFecha() {
        return fecha;
    }


    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getCantidadVentas() {
        return cantidadVentas;
    }

    public void setCantidadVentas(int cantidadVentas) {
        this.cantidadVentas = cantidadVentas;
    }
}