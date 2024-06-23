package proyectoInvOp.back.Strategy;

import org.springframework.cglib.core.Local;
import proyectoInvOp.back.DTOS.*;
import proyectoInvOp.back.Entity.ParametroGeneral;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EstrategiaSimulacionPMSuavizadoExp implements EstrategiaSimulacion {
    @Override
    public DTOResultadoSimu simular(List<DTOVentas> ventas, List<DTOParametroValor> parametros) {

        System.out.println("               ");
        System.out.println("******SIMULACION PM SUAVIZADO*********");
        for (DTOParametroValor par:parametros){
            System.out.println("nombre parametro: "+par.getNombreParametro()+", valor parametro: "+par.getValorParametro());
        }

        float mesesAtras = 0;
        float alfa = 0;

        for (DTOParametroValor param : parametros) {
            String nombre = param.getNombreParametro();
            if ("Periodos historicos".equals(nombre)) {
                mesesAtras = param.getValorParametro();
            } else if ("alfa".equals(nombre)) {
                alfa = param.getValorParametro();
            }
        }

        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaDesde = fechaActual.minusMonths((long)mesesAtras);
        System.out.println(fechaActual);
        System.out.println(fechaDesde);


        float pronostico = 0;
        float sumaErrores = 0;
        boolean primerValor = true;
        float demandaReal = 0;
        while (!fechaDesde.isAfter(fechaActual)) {
            System.out.println("Procesando mes: " + fechaDesde.getMonth() + " del año " + fechaDesde.getYear());

            for (DTOVentas venta : ventas) {
                if (venta.getMes() == fechaDesde.getMonthValue()) {


                    if (primerValor) {
                        pronostico = venta.getCantidadVentas();
                        primerValor = false;
                    } else {
                        pronostico = pronostico + alfa * (demandaReal-pronostico);

                        float err =  Math.abs(demandaReal - pronostico);

                        sumaErrores += err;
                    }

                    demandaReal = venta.getCantidadVentas();
                    System.out.println("Demanda real en mes " + fechaDesde.getMonth() + ": " + demandaReal);

                    System.out.println("Pronóstico: " + pronostico);

                }
            }

            fechaDesde = fechaDesde.plusMonths(1);
        }

        System.out.println("Error: "+sumaErrores);

        DTOResultadoSimu resultadoSimu = new DTOResultadoSimu();
        resultadoSimu.setErrorObtenido(sumaErrores);


        System.out.println("**************************************");
        System.out.println("               ");
        return resultadoSimu;
    }
}