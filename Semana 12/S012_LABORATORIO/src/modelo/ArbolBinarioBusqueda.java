package modelo;

public class ArbolBinarioBusqueda<T> {

    protected NodoBinario<T> raiz;

    public ArbolBinarioBusqueda() {
        this.raiz = null;
    }

    public boolean estaVacio() {
        return raiz == null;
    }
}
