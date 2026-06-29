package modelo;

public class NodoBinario<T> {

    public T dato;
    public NodoBinario<T> izquierdo;
    public NodoBinario<T> derecho;

    public NodoBinario(T dato) {
        this.dato = dato;
        this.izquierdo = null;
        this.derecho = null;
    }
}
