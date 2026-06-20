package util;


public class NodeTree<T> {
    public T element;
    public NodeTree<T> left;
    public NodeTree<T> right;

    public NodeTree(T element) {
        this.element = element;
        this.left = null;
        this.right = null;
    }
}
