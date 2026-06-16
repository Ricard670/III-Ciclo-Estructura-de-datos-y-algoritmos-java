package Estructuras;

import java.util.ArrayList;
import java.util.List;

public class NodoGenerico<T> {

    private T data;
    private List<NodoGenerico<T>> hijos;

    public NodoGenerico(T data) {
        this.data = data;
        this.hijos = new ArrayList<>();
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<NodoGenerico<T>> getHijos() {
        return hijos;
    }

    public void agregarHijo(NodoGenerico<T> hijo) {
        this.hijos.add(hijo);
    }

    public void eliminarHijo(NodoGenerico<T> hijo) {
        this.hijos.remove(hijo);
    }

    @Override
    public String toString() {
        return "Nodo{"
                + "data=" + data
                + ", hijos=" + hijos.size()
                + '}';
    }
}
