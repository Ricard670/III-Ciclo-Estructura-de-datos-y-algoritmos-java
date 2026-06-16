package Estructuras;

public class NodoTree {

    int numero;
    NodoTree left;
    NodoTree right;

    public NodoTree(int item) {
        numero = item;
        left = null;
        right = null;
    }

    @Override
    public String toString() {
        return String.valueOf(this.numero);
    }

}
