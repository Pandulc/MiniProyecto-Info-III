package structures;

public class Node<AnyType extends Comparable <AnyType>>{
    private AnyType element;
    private Node<AnyType> left;
    private Node<AnyType> right;

    //Para avl
    private int height = 1;


    public Node() {
    }

    public Node(AnyType element) {
        this.element = element;
        this.left = null;
        this.right = null;
    }

    public AnyType getElement() {
        return element;
    }

    public void setElement(AnyType element) {
        this.element = element;
    }

    public Node<AnyType> getLeft() {
        return left;
    }

    public void setLeft(Node<AnyType> left) {
        this.left = left;
    }

    public Node<AnyType> getRight() {
        return right;
    }

    public void setRight(Node<AnyType> right) {
        this.right = right;
    }

    //Para avl
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
