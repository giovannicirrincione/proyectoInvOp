package proyectoInvOp.back.PatronObservador;

public interface Obervable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
