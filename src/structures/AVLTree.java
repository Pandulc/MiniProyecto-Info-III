package structures;

import java.io.PrintStream;

public class AVLTree<AnyType extends Comparable<AnyType>> {
    private Node<AnyType> raiz;

    /*
     *
     * Operaciones
     *
     */

    public void add(AnyType data) {
        raiz = add(data, raiz);
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

    public void delete(AnyType data) throws Exception {
        raiz = delete(data, raiz);
    }

    private Node<AnyType> delete(AnyType data, Node<AnyType> node) throws Exception {
        if (node == null) {
            throw new Exception("El elemento no esta en el arbol");
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

    private AnyType getMax(Node<AnyType> node) {
        if (node.getRight() != null) {
            return getMax(node.getRight());
        }
        return node.getElement();
    }

    private AnyType getMin(Node<AnyType> node) {
        if (node.getLeft() != null) {
            return getMin(node.getLeft());
        }
        return node.getElement();
    }

    public AnyType search(AnyType x) throws Exception {
        if (x.equals(raiz.getElement())) return raiz.getElement();
        else if (x.compareTo(raiz.getElement()) < 0 && raiz.getLeft() != null) return search(x, raiz.getLeft());
        else if (x.compareTo(raiz.getElement()) > 0 && raiz.getRight() != null) return search(x, raiz.getRight());
        else throw new Exception("El elemento no esta en el arbol");
    }

    private AnyType search(AnyType x, Node<AnyType> node) throws Exception {
        if (x.equals(node.getElement())) return node.getElement();
        else if (x.compareTo(node.getElement()) < 0 && node.getLeft() != null) return search(x, node.getLeft());
        else if (x.compareTo(node.getElement()) > 0 && node.getRight() != null) return search(x, node.getRight());
        else throw new Exception("El elemento no esta en el arbol");
    }


    /*
     *
     * Estados
     *
     */
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

    /*
     *
     * Para imprimir (prints)
     *
     */

    public void printInOrder() throws Exception {
        if(isEmpty()){
            throw new Exception("El arbol esta vacio");
        }

        if (raiz.getLeft() != null) {
            printInOrder(raiz.getLeft());
        }

        System.out.print(raiz.getElement() + "\t");

        if (raiz.getRight() != null) {
            printInOrder(raiz.getRight());
        }

        System.out.println();
    }

    private void printInOrder(Node<AnyType> node) {
        if (node != null) {
            printInOrder(node.getLeft());
            System.out.print(node.getElement() + "\t");
            printInOrder(node.getRight());
        }
    }

    public void printLikeTree(PrintStream os) {
        os.print(traversePreOrder(raiz));
        System.out.println();
    }

    public String traversePreOrder(Node<AnyType> raiz) {

        if (raiz == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(raiz.getElement());

        String pointerRight = "└──";
        String pointerLeft = (raiz.getRight() != null) ? "├──" : "└──";

        traverseNodes(sb, "", pointerLeft, raiz.getLeft(), raiz.getRight() != null);
        traverseNodes(sb, "", pointerRight, raiz.getRight(), false);

        return sb.toString();
    }

    public void traverseNodes(StringBuilder sb, String padding, String pointer, Node<AnyType> node,
                              boolean hasRightSibling) {
        if (node != null) {
            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
            sb.append(node.getElement());

            StringBuilder paddingBuilder = new StringBuilder(padding);
            if (hasRightSibling) {
                paddingBuilder.append("│  ");
            } else {
                paddingBuilder.append("   ");
            }

            String paddingForBoth = paddingBuilder.toString();
            String pointerRight = "└──";
            String pointerLeft = (node.getRight() != null) ? "├──" : "└──";

            traverseNodes(sb, paddingForBoth, pointerLeft, node.getLeft(), node.getRight() != null);
            traverseNodes(sb, paddingForBoth, pointerRight, node.getRight(), false);
        }
    }

    /* Sin usar

      public void makeEmpty() {
      raiz = null;
    }

    public AnyType getMax() {
        if (isEmpty()) {
            return null;
        }
        return getMax(raiz);
    }

    public AnyType getMin() {
        if (isEmpty()) {
            return null;
        }
        return getMin(raiz);
    }
    */
}
