package proyectoInvOp.back.PatronObservador;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
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

                    observer.update(detalle.getArticulo(), detalle.getCantidad());

                }
            }
        }
    }

