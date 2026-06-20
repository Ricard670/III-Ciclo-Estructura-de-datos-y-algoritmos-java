package util;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree<T> {
    public NodeTree<T> root;

    public BinaryTree() {
        this.root = null;
    }

    // Recorrido PREORDER
    public void preOrder(NodeTree<T> node) {
        if (node == null) return;
        System.out.print(node.element + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    // Recorrido INORDER
    public void inOrder(NodeTree<T> node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.print(node.element + " ");
        inOrder(node.right);
    }

    // Recorrido POSTORDER
    public void postOrder(NodeTree<T> node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.element + " ");
    }

    // Recorrido LEVELORDER
    public void levelOrder(NodeTree<T> node) {
        if (node == null) return;

        List<NodeTree<T>> queue = new ArrayList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            NodeTree<T> current = queue.remove(0);
            System.out.print(current.element + " ");

            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
    }
}

