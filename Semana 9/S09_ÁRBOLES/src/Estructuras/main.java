package Estructuras;

public class main {

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.root = new NodoTree(1);
        tree.root.left = new NodoTree(2);
        tree.root.right = new NodoTree(3);

        System.out.println(tree.root + " " + tree.root.left + " " + tree.root.right);
    }
}
