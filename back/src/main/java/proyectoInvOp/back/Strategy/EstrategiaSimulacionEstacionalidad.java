package proyectoInvOp.back.Strategy;

import proyectoInvOp.back.DTOS.*;

import java.util.List;

public class EstrategiaSimulacionEstacionalidad implements EstrategiaSimulacion{
    @Override
    public DTOResultadoSimu simular(List<DTOVentas> ventas, List<DTOParametroValor> parametros) {

        DTOResultadoSimu resultadoSimu = new DTOResultadoSimu();

        //if(ventas.size()<36){
          //  resultadoSimu.setNombreMetodo("Estacionalidad");
            //resultadoSimu.setErrorObtenido(10000.0);
            //resultadoSimu.setParametroUsado("no tiene");
            //return resultadoSimu;
        //}


        // Inicializamos los arrays para las sumas y los índices
        float[] sumasMensuales = new float[12];
        int[] conteoMensual = new int[12];

        // Sumamos las ventas por mes de 2021 y 2022
        for (int i = 0; i < ventas.size() - 12; i++) {
            int mes = ventas.get(i).getFecha().getMonthValue() - 1;
            sumasMensuales[mes] += ventas.get(i).getCantidadVentas();
            conteoMensual[mes]++;
        }


        // Calcular el promedio general D
        float D = 0;
        for (float suma : sumasMensuales) {
            D += suma;
        }
        D /= 24; // 24 es el número total de meses (2 años * 12 meses)

        // Calcular los índices de estacionalidad
        float[] indicesEstacionalidad = new float[12];
        for (int i = 0; i < 12; i++) {
            indicesEstacionalidad[i] = sumasMensuales[i] / D;
        }

        // Generar las predicciones para 2023 usando Yc(i) = D * I(i)
        float[] predicciones2023 = new float[12];
        for (int i = 0; i < 12; i++) {
            predicciones2023[i] = D * indicesEstacionalidad[i];
        }

        //VER ACA QUE HAY Q METER MAS DATOS EN LA BD
        // Calcular el error absoluto total
        float errorTotal = 0;
        for (int i = 0; i < 12; i++) {
            float valorReal = ventas.get(ventas.size() - 12 + i).getCantidadVentas();
            float valorPredicho = predicciones2023[i];
            errorTotal += Math.abs(valorPredicho - valorReal);

        }

        resultadoSimu.setErrorObtenido((double) errorTotal);

        resultadoSimu.setNombreMetodo("Estacionalidad");


        System.out.println("******************");
        System.out.println("simule con estacionalidad");
        System.out.println("obtuve" + resultadoSimu.getErrorObtenido());
        System.out.println("******************");
        return resultadoSimu;
    }
}
