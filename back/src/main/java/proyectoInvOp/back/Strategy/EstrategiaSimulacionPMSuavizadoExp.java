package proyectoInvOp.back.Strategy;

import org.springframework.cglib.core.Local;
import proyectoInvOp.back.DTOS.*;
import proyectoInvOp.back.Entity.ParametroGeneral;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
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
        List<Float> alfas = new ArrayList<>();

        for (DTOParametroValor param : parametros) {
            String nombre = param.getNombreParametro();
            if ("Periodos historicos".equals(nombre)) {
                mesesAtras = param.getValorParametro();
            } else if ("alfa".equals(nombre)) {
                alfas.add(param.getValorParametro());
            }
        }

        LocalDate fechaActual = LocalDate.now();

        System.out.println(fechaActual);



        List<Float> errores = new ArrayList<>();

        float demandaReal = 0;
        for (Float alfa: alfas){
            LocalDate fechaDesde = fechaActual.minusMonths((long)mesesAtras);
            float pronostico = 0;
            boolean primerValor = true;
            float sumaErrores = 0;
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
            errores.add(sumaErrores);

        }

        for (Float err: errores){
            System.out.println(err);
        }

        Float errMin = Collections.min(errores);
        System.out.println("error minimo:"+errMin);
        int indexMin = errores.indexOf(errMin);
        System.out.println("indice: "+indexMin);

        float alfaConMenosError = alfas.get(indexMin);
        System.out.println(alfaConMenosError);

        DTOResultadoSimu resultadoSimu = new DTOResultadoSimu();
        resultadoSimu.setErrorObtenido(errMin);
        resultadoSimu.setNombreMetodo("PM Suavizado");
        resultadoSimu.setParametroUsado("alfa");
        resultadoSimu.setValorParametro(alfaConMenosError);

        System.out.println("**************************************");
        System.out.println("               ");
        return resultadoSimu;
    }



}