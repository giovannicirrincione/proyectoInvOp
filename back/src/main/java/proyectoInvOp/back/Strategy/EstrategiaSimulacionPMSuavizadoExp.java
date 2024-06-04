package proyectoInvOp.back.Strategy;

import proyectoInvOp.back.DTOS.DTOValores;
import proyectoInvOp.back.DTOS.DTOValoresOptimos;

import java.util.ArrayList;
import java.util.List;

public class EstrategiaSimulacionPMSuavizadoExp implements EstrategiaSimulacion {
    @Override
    public DTOValoresOptimos simular(List<DTOValores> dtoValores) {
        DTOValoresOptimos dtoValoresOptimos = new DTOValoresOptimos();
        List<Integer> valoresSumatoria = new ArrayList<>();
        Double[] posiblesValoresAlpha = {0.05, 0.1, 0.15, 0.20, 0.25, 0.30};
        //VAMOS A HACER LA PRUEBA PARA TODOS LOS VALORES DE ALPHA
        for (Double i : posiblesValoresAlpha) {
            List<Integer> pronostico = new ArrayList<>();
            List<Integer> desviacion = new ArrayList<>();
            pronostico.add(0);
            desviacion.add(0);
            //Probamos con todos los valores de alpha
            for (int j = 1; j < dtoValores.size(); j++) {
                int demandaPron = dtoValores.get(j-1).getcantidad();
                pronostico.add(demandaPron);
                int demandaReal = dtoValores.get(j-1).getcantidad();
                desviacion.add(demandaPron - demandaReal);
                //calculo de la prediccion para el proximo periodo

                double demandaPredecica = pronostico.get(j) + i * (dtoValores.get(j).getcantidad() - pronostico.get(j));
                pronostico.add((int) demandaPredecica);

            }
            //obtengo la sumatoria que obtube con ese alpha
            int sumatoria = sumarLista(desviacion);
            //lo agrego a la lista de alphas
            valoresSumatoria.add(sumatoria);
        }
        int menorValor = Integer.MAX_VALUE; // Inicializamos con el valor máximo de Integer
        int posicion = -1; // Inicializamos con -1, lo cual indica que no se ha encontrado aún
        //veo en q posicion esta el minimo valor de sumatoria
        for (int i = 0; i < valoresSumatoria.size(); i++) {
            if (valoresSumatoria.get(i) < menorValor) {
                menorValor = valoresSumatoria.get(i);
                posicion = i;
            }
        }
        //obtengo en valor minimo de alpha
        Double valorAlpha = posiblesValoresAlpha[posicion];

        dtoValoresOptimos.setAlpha(valorAlpha);

        return dtoValoresOptimos;

    }

    public static int sumarLista(List<Integer> lista) {
        int suma = 0;
        for (int elemento : lista) {
            suma += elemento;
        }
        return suma;
    }
}
