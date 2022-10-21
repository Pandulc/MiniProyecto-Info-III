package structures;

public class AVLTree<AnyType extends Comparable<AnyType>> {
    private Node<AnyType> raiz;

    /*
     *
     * Agregar y borrar
     *
     */

    public AVLTree<AnyType> add(AnyType data) {
        raiz = add(data, raiz);
        return this;
    }

    private Node<AnyType> add(AnyType data, Node<AnyType> node) {
        if (node == null) {
            return new Node<>(data);
        }
        if (data.compareTo(node.getElement()) < 0) {
            node.setLeft(add(data, node.getLeft()));
        } else if (data.compareTo(node.getElement()) > 0) {
            node.setRight(add(data, node.getRight()));
        } else {
            return node;
        }
        updateHeight(node);
        return applyRotation(node);
    }

    public void delete(AnyType data) {
        raiz = delete(data, raiz);
    }

    private Node<AnyType> delete(AnyType data, Node<AnyType> node) {
        if (node == null) {
            return null;
        }
        if (data.compareTo(node.getElement()) < 0) {
            node.setLeft(delete(data, node.getLeft()));
        } else if (data.compareTo(node.getElement()) > 0) {
            node.setRight(delete(data, node.getRight()));
        } else {
            // One Child or Leaf Node (no children)
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }
            // Two Children
            node.setElement(getMax(node.getLeft()));
            node.setLeft(delete(node.getElement(), node.getLeft()));
        }
        updateHeight(node);
        return applyRotation(node);
    }

    /*
     *
     * Para imprimir (prints)
     *
     */

    public void print() {
        printInOrder(raiz);
    }

    private void printInOrder(Node<AnyType> node) {
        if (node != null) {
            printInOrder(node.getLeft());
            System.out.print(node.getElement() + "\t");
            printInOrder(node.getRight());
        }

        System.out.println();
    }

    public void printAsTree() {
        System.out.println(raiz.getElement());
        if (raiz.getLeft() != null) printAsTree(raiz.getLeft(), 1);
        else System.out.print(" ├─");
        if (raiz.getRight() != null) printAsTree(raiz.getRight(), 1);
        else System.out.print(" ├─");
    }

    private void printAsTree(Node<AnyType> node, int i) {
        for (int j = 0; j < i; j++) {
            if (i == 1) System.out.print("├─");
            else if (j < i - 1) System.out.print("│  ");
            else System.out.print("├─ ");
        }
        if (node != null) {
            System.out.println(node.getElement());
            printAsTree(node.getLeft(), i + 1);
            printAsTree(node.getRight(), i + 1);
        } else System.out.println();
    }

    /*
     *
     * Get min y max
     *
     */

    public AnyType getMax() {
        if (isEmpty()) {
            return null;
        }
        return getMax(raiz);
    }

    private AnyType getMax(Node<AnyType> node) {
        if (node.getRight() != null) {
            return getMax(node.getRight());
        }
        return node.getElement();
    }

    public AnyType getMin() {
        if (isEmpty()) {
            return null;
        }
        return getMin(raiz);
    }

    private AnyType getMin(Node<AnyType> node) {
        if (node.getLeft() != null) {
            return getMin(node.getLeft());
        }
        return node.getElement();
    }

    public boolean isEmpty() {
        return raiz == null;
    }

    /*
     *
     * Rotaciones
     *
     */

    private Node<AnyType> applyRotation(Node<AnyType> node) {
        int balance = balance(node);
        if (balance > 1) {
            if (balance(node.getLeft()) < 0) {
                node.setLeft(rotateLeft(node.getLeft()));
            }
            return rotateRight(node);
        }
        if (balance < -1) {
            if (balance(node.getRight()) > 0) {
                node.setRight(rotateRight(node.getRight()));
            }
            return rotateLeft(node);
        }
        return node;
    }

    private Node<AnyType> rotateRight(Node<AnyType> node) {
        Node<AnyType> leftNode = node.getLeft();
        Node<AnyType> centerNode = leftNode.getRight();
        leftNode.setRight(node);
        node.setLeft(centerNode);
        updateHeight(node);
        updateHeight(leftNode);
        return leftNode;
    }

    private Node<AnyType> rotateLeft(Node<AnyType> node) {
        Node<AnyType> rightNode = node.getRight();
        Node<AnyType> centerNode = rightNode.getLeft();
        rightNode.setLeft(node);
        node.setRight(centerNode);
        updateHeight(node);
        updateHeight(rightNode);
        return rightNode;
    }

    /*
     *
     * Altura y balance
     *
     */

    private void updateHeight(Node<AnyType> node) {
        int maxHeight = Math.max(
                height(node.getLeft()),
                height(node.getRight())
        );
        node.setHeight(maxHeight + 1);
    }

    private int balance(Node<AnyType> node) {
        return node != null ? height(node.getLeft()) - height(node.getRight()) : 0;
    }

    private int height(Node<AnyType> node) {
        return node != null ? node.getHeight() : 0;
    }
}
