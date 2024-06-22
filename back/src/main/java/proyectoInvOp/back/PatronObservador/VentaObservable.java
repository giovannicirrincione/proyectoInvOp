package proyectoInvOp.back.PatronObservador;

import proyectoInvOp.back.Entity.DetalleVenta;
import proyectoInvOp.back.Entity.Venta;

import java.util.ArrayList;
import java.util.List;

public class VentaObservable implements Obervable{

        private List<Observer> observers = new ArrayList<>();
        private Venta venta;

        public VentaObservable(Venta venta) {
            this.venta = venta;
        }

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

            for (DetalleVenta detalle : venta.getDetalleVentas()) {

                for (Observer observer : observers) {

                    observer.updateVenta(detalle.getArticulo(), detalle.getCantidad());

                }
            }
        }
    }

