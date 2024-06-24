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


        List<Float> errores = new ArrayList<>();

        float demandaReal = 0;
        for (Float alfa: alfas){
            LocalDate fechaDesde = fechaActual.minusMonths((long)mesesAtras);
            float pronostico = 0;
            boolean primerValor = true;
            float sumaErrores = 0;
            while (!fechaDesde.isAfter(fechaActual)) {

                for (DTOVentas venta : ventas) {
                    if (venta.getFecha().getMonthValue() == fechaDesde.getMonthValue()) {


                        if (primerValor) {
                            pronostico = venta.getCantidadVentas();
                            primerValor = false;
                        } else {
                            pronostico = pronostico + alfa * (demandaReal-pronostico);

                            float err =  Math.abs(demandaReal - pronostico);

                            sumaErrores += err;
                        }

                        demandaReal = venta.getCantidadVentas();

                    }
                }
                fechaDesde = fechaDesde.plusMonths(1);
            }
            errores.add(sumaErrores);

        }


        Float errMin = Collections.min(errores);

        int indexMin = errores.indexOf(errMin);


        float alfaConMenosError = alfas.get(indexMin);


        DTOResultadoSimu resultadoSimu = new DTOResultadoSimu();
        resultadoSimu.setErrorObtenido((double)errMin);
        resultadoSimu.setNombreMetodo("PMSuavizadoExp");
        resultadoSimu.setParametroUsado("alfa");
        resultadoSimu.setValorParametro(alfaConMenosError);



        System.out.println("******************");
        System.out.println("simule con SUAVIZADO");
        System.out.println("obtuve" + resultadoSimu.getErrorObtenido());
        System.out.println("******************");
        return resultadoSimu;
    }
}