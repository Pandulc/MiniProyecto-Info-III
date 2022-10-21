package structures;

import java.io.PrintStream;

public class BinaryTree<AnyType extends Comparable<AnyType>> {
    private Node<AnyType> raiz;
    private Integer depth;

    public BinaryTree() {
        this.depth = 0;
    }

    public void add(AnyType x) {
        if(isEmpty()) raiz = new Node<>(x);
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

    public void addNoRep(AnyType x) throws Exception {
        if (x.compareTo(raiz.getElement()) < 0) {
            if (raiz.getLeft() == null) {
                raiz.setLeft(new Node<>(x));
            } else {
                addNoRep(x, raiz.getLeft());
            }
        } else if (x.compareTo(raiz.getElement()) > 0) {
            if (raiz.getRight() == null) {
                raiz.setRight(new Node<>(x));
            } else {
                addNoRep(x, raiz.getRight());
            }
        } else {
            throw new Exception("El elemento ya esta en el arbol");
        }
    }

    private void addNoRep(AnyType x, Node<AnyType> node) throws Exception {
        if (x.compareTo(raiz.getElement()) < 0) {
            if (node.getLeft() == null) {
                node.setLeft(new Node<>(x));
            } else {
                addNoRep(x, node.getLeft());
            }
        } else if (x.compareTo(raiz.getElement()) > 0) {
            if (node.getRight() == null) {
                node.setRight(new Node<>(x));
            } else {
                addNoRep(x, node.getRight());
            }
        } else {
            throw new Exception("El elemento ya esta en el arbol");
        }
    }

    public void delete(AnyType x) throws Exception {
        if (x.equals(raiz.getElement())) {
            if (raiz.getLeft() == null && raiz.getRight() == null) raiz = null;
            else if (raiz.getRight() == null) raiz = raiz.getLeft();
            else if (raiz.getLeft() == null) raiz = raiz.getRight();
            else {
                Node<AnyType> aux = raiz.getLeft();
                raiz = raiz.getRight();
                Node<AnyType> aux2 = raiz.getLeft();
                if (aux2 != null) {
                    while (aux2.getLeft() != null) {
                        aux2 = aux2.getLeft();
                    }
                    aux2.setLeft(aux);
                } else raiz.setLeft(aux);
            }

        } else if (x.compareTo(raiz.getElement()) < 0  && raiz.getLeft() != null) raiz.setLeft(delete(x, raiz.getLeft()));
        else if (x.compareTo(raiz.getElement()) > 0 && raiz.getRight() != null) raiz.setRight(delete(x, raiz.getRight()));
        else throw new Exception("El elemento no esta en el arbol");
    }

    private Node<AnyType> delete(AnyType x, Node<AnyType> node) throws Exception {
        if (x.equals(node.getElement())) {
            if (node.getLeft() == null && node.getRight() == null) node = null;
            else if (node.getRight() == null) node = node.getLeft();
            else if (node.getLeft() == null) node = node.getRight();
            else {
                Node<AnyType> aux = node.getLeft();
                node = node.getRight();
                Node<AnyType> aux2 = node.getLeft();
                if (aux2 != null) {
                    while (aux2.getLeft() != null) {
                        aux2 = aux2.getLeft();
                    }
                    aux2.setLeft(aux);
                } else node.setLeft(aux);
            }
        } else if (x.compareTo(raiz.getElement()) < 0 && node.getLeft() != null) node.setLeft(delete(x, node.getLeft()));
        else if (x.compareTo(raiz.getElement()) > 0 && node.getRight() != null) node.setRight(delete(x, node.getRight()));
        else throw new Exception("El elemento no esta en el arbol");
        return node;
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

    public Integer depth() {
        int depthl = 0;
        if (raiz.getLeft() != null) {
            depth(raiz.getLeft(), depthl + 1);
        }
        if (raiz.getRight() != null) {
            depth(raiz.getRight(), depthl + 1);
        }
        return depthl;
    }

    private Integer depth(Node<AnyType> node, Integer depthl) {
        if (node.getLeft() != null) {
            depth(node.getLeft(), depthl + 1);
        }
        if (node.getRight() != null) {
            depth(node.getRight(), depthl + 1);
        } else {
            if (depth < depthl) depth = depthl;
        }
        return depthl;
    }

    public AnyType search(AnyType x) throws Exception {
        if (x.equals(raiz.getElement())) return raiz.getElement();
        else if (x.compareTo(raiz.getElement()) < 0 && raiz.getLeft() != null) return search(x, raiz.getLeft());
        else if (x.compareTo(raiz.getElement()) > 0 && raiz.getRight() != null) return search(x, raiz.getRight());
        else throw new Exception("El elemento no esta en el arbol");
    }

    private AnyType search(AnyType x, Node<AnyType> node) throws Exception {
        if (x.equals(node.getElement())) return node.getElement();
        else if (x.compareTo(raiz.getElement()) < 0 && node.getLeft() != null) return search(x, node.getLeft());
        else if (x.compareTo(raiz.getElement()) > 0 && node.getRight() != null) return search(x, node.getRight());
        else throw new Exception("El elemento no esta en el arbol");
    }

    public void printfTree() {
        System.out.println("Arbol de menor a mayor:");
        if (raiz.getLeft() != null) {
            printfTree(raiz.getLeft());
        }

        System.out.print(raiz.getElement() + "\t");

        if (raiz.getRight() != null) {
            printfTree(raiz.getRight());
        }

        System.out.println();
    }

    private void printfTree(Node<AnyType> node) {
        if (node.getLeft() != null) {
            printfTree(node.getLeft());
        }

        System.out.print(node.getElement() + "\t");

        ///////////////////////////////////
        if (node.getRight() != null) {
            printfTree(node.getRight());
        }
    }

    public boolean isEmpty(){
        return raiz == null;
    }

    public void makeEmpty(){
        raiz = null;
    }

    public Node<AnyType> getRaiz() {
        return raiz;
    }

    public void setRaiz(Node<AnyType> raiz) {
        this.raiz = raiz;
    }

    public void traversePreOrder(StringBuilder sb, String padding, String pointer, Node<AnyType> node) {
        if (node != null) {
            sb.append(padding);
            sb.append(pointer);
            sb.append(node.getElement());
            sb.append("\n");

            StringBuilder paddingBuilder = new StringBuilder(padding);
            paddingBuilder.append("│  ");

            String paddingForBoth = paddingBuilder.toString();
            String pointerForRight = "└──";
            String pointerForLeft = (node.getRight() != null) ? "├──" : "└──";

            traversePreOrder(sb, paddingForBoth, pointerForLeft, node.getLeft());
            traversePreOrder(sb, paddingForBoth, pointerForRight, node.getRight());
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


}
