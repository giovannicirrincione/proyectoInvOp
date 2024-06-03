package proyectoInvOp.back.PatronObservador;

import proyectoInvOp.back.Entity.OrdenCompra;

import java.util.ArrayList;
import java.util.List;

public class OrdenCompraObservable implements  Obervable{
    private List<Observer> observers = new ArrayList<>();

    private OrdenCompra ordenCompra;

    public OrdenCompraObservable(OrdenCompra ordenCompra){this.ordenCompra = ordenCompra;}

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    //Metodo de notificacion
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.updateCompra(ordenCompra.getArticulo(), ordenCompra.getCantidad());

        }

    }


}