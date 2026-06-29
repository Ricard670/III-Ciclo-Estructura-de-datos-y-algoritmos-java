package Estructuras;

public class BinaryTree {

    NodoTree root;
    BinaryTree() {
        root = null;
    }

    void recorridoProOrder(NodoTree node) {
        if (node == null) {
            return;
        }
        Visita(node);
        recorridoProOrder(node.left);
        recorridoProOrder(node.right);

    }

    void Visita(NodoTree node) {
        System.out.println(node.numero + " ");
    }
}
