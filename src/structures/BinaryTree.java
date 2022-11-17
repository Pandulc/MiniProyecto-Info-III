package structures;

import java.io.PrintStream;

public class BinaryTree<AnyType extends Comparable<AnyType>> {
    private Node<AnyType> raiz;
    //private Integer depth;

    public BinaryTree() {}

    /*
     *
     * Operaciones
     *
     */

    public void add(AnyType x) {
        if (isEmpty()) raiz = new Node<>(x);
        else if (x.compareTo(raiz.getElement()) < 0) {
            if (raiz.getLeft() == null) {
                raiz.setLeft(new Node<>(x));
            } else {
                add(x, raiz.getLeft());
            }
        } else {
            if (raiz.getRight() == null) {
                raiz.setRight(new Node<>(x));
            } else {
                add(x, raiz.getRight());
            }
        }
    }

    private void add(AnyType x, Node<AnyType> node) {
        if (x.compareTo(node.getElement()) < 0) {
            if (node.getLeft() == null) {
                node.setLeft(new Node<>(x));
            } else {
                add(x, node.getLeft());
            }
        } else {
            if (node.getRight() == null) {
                node.setRight(new Node<>(x));
            } else {
                add(x, node.getRight());
            }
        }
    }

    public void delete(AnyType data) throws Exception{
        if(isEmpty()){
            throw new Exception("El arbol esta vacio");
        }
        raiz = delete(data, raiz);
    }

    private Node<AnyType> delete(AnyType data, Node<AnyType> node) throws Exception{
        if (node == null) {
            throw new Exception("El elemento no esta en el arbol");
        }
        if (data.compareTo(node.getElement()) < 0) {
            node.setLeft(delete(data, node.getLeft()));
        } else if (data.compareTo(node.getElement()) > 0) {
            node.setRight(delete(data, node.getRight()));
        } else {
            // One child or No children
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }
            // Two children
            node.setElement(getMax(node.getLeft()));
            node.setLeft(delete(node.getElement(), node.getLeft()));
        }
        return node;
    }
    private AnyType getMax(Node<AnyType> node) {
        if (node.getRight() != null) {
            return getMax(node.getRight());
        }
        return node.getElement();
    }

    public AnyType search(AnyType x) throws Exception {
        if(isEmpty()){
            throw new Exception("El arbol esta vacio");
        }
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
     * Para imprimir (prints)
     *
     */

    public void printInOrder() throws Exception{
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
        if (node.getLeft() != null) {
            printInOrder(node.getLeft());
        }

        System.out.print(node.getElement() + "\t");

        ///////////////////////////////////
        if (node.getRight() != null) {
            printInOrder(node.getRight());
        }
    }

    public void printLikeTree(PrintStream os) {
        os.print(traversePreOrder(raiz));
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

    public void traverseNodes(StringBuilder sb, String padding, String pointer, Node<AnyType> node, boolean hasRightSibling) {
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
}
