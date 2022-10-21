import structures.*;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int op;
        BinaryTree<Integer> binTree = new BinaryTree<>();
        AVLTree<Integer> avlTree = new AVLTree<>();

        do {
            op = menu();
            switch (op) {
                case 0 -> System.out.println("Cierre del programa");
                case 1 -> binTree = randomBinary();
                case 2 -> binTree = manualBinary();
                case 3 -> avlTree = randomAVL();
                case 4 -> avlTree = manualAVL();
                case 5 -> orderTree(binTree, avlTree);
                case 6 -> drawTree(binTree, avlTree);
                default -> System.out.println("Se ha ingresado una opcion invalida. Intentelo nuevamente");
            }
        } while (op != 0);
    }

    public static int menu() {
        int op;
        Scanner sc = new Scanner(System.in);

        System.out.println("\nIngrese la opcion a realizar\n");

        System.out.println("1. Crear arbol aleatorio");
        System.out.println("2. Crear arbol manual");
        System.out.println("3. Crear arbol AVL aleatorio");
        System.out.println("4. Crear arbol AVL manual");
        System.out.println("5. Mostrar arbol ordenado");
        System.out.println("6. Mostrar dibujo del arbol\n");
        System.out.println("0. Salir");

        System.out.print("\nOpcion: ");
        op = sc.nextInt();

        return op;
    }

    public static BinaryTree<Integer> randomBinary() {
        int cant;
        BinaryTree<Integer> binTree = new BinaryTree<>();
        Scanner sc = new Scanner(System.in);
        Random ran = new Random();

        System.out.println("Arbol binario aleatorio");
        System.out.println("Ingrese la cantidad de elementos del arbol binario");
        System.out.print("\nCantidad: ");
        cant = sc.nextInt();

        for (int i = 0; i < cant; i++) {
            binTree.add(ran.nextInt(100));
        }

        return binTree;
    }

    public static BinaryTree<Integer> manualBinary() {
        int cant;
        int num;
        BinaryTree<Integer> binTree = new BinaryTree<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("Arbol binario manual");
        System.out.println("Ingrese la cantidad de elementos del arbol binario");
        System.out.print("\nCantidad: ");
        cant = sc.nextInt();

        System.out.println("Ingrese los valores");

        for (int i = 0; i < cant; i++) {
            System.out.println("Valor: ");
            num = sc.nextInt();
            binTree.add(num);
        }

        return binTree;

    }

    public static AVLTree<Integer> randomAVL() {
        int cant;
        AVLTree<Integer> avlTree = new AVLTree<>();
        Scanner sc = new Scanner(System.in);
        Random ran = new Random();

        System.out.println("Arbol AVL aleatorio");
        System.out.println("Ingrese la cantidad de elementos del arbol AVL");
        System.out.print("\nCantidad: ");
        cant = sc.nextInt();

        for (int i = 0; i < cant; i++) {
            avlTree.add(ran.nextInt(100));
        }

        return avlTree;
    }

    public static AVLTree<Integer> manualAVL() {
        int cant, element;
        AVLTree<Integer> avlTree = new AVLTree<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("Arbol AVL manual");
        System.out.println("Ingrese la cantidad de elementos del arbol AVL");
        System.out.print("\nCantidad: ");
        cant = sc.nextInt();

        System.out.println("Ingrese los valores a insertar");

        for (int i = 0; i < cant; i++) {
            System.out.print("\nValor: ");
            element = sc.nextInt();
            avlTree.add(element);
        }

        return avlTree;
    }

    public static void orderTree(BinaryTree<Integer> binTree, AVLTree<Integer> avlTree){
        int op2;
        Scanner sc = new Scanner(System.in);

        System.out.println("\nArbol ordenado");
        System.out.println("\n¿Que arbol desea imprimir?\n1- Arbol binario de busqueda\n2- Arbol AVL");
        op2 = sc.nextInt();

        if (op2 == 1){
            if(binTree.isEmpty()){
                System.out.println("\nEl arbol esta vacio");
                return;
            }
            binTree.printfTree();
        }
        else if (op2 == 2){
            if(avlTree.isEmpty()){
                System.out.println("\nEl arbol esta vacio");
                return;
            }
            avlTree.print();
        }
        else System.out.println("Opcion invalida");
    }

    public static void drawTree(BinaryTree<Integer> binTree, AVLTree<Integer> avlTree){
        int op2;
        Scanner sc = new Scanner(System.in);

        System.out.println("\nArbol ordenado");
        System.out.println("¿Que arbol desea imprimir?\n1- Arbol binario de busqueda\n2- Arbol AVL");
        op2 = sc.nextInt();

        if (op2 == 1){
            if(binTree.isEmpty()){
                System.out.println("\nEl arbol esta vacio");
                return;
            }
            System.out.println("Pandulciano");
            binTree.printAsTree();

            System.out.println("algo que encontre tirado");
            binTree.printLikeTree(new PrintStream(System.out));
        }
        else if (op2 == 2){
            if(avlTree.isEmpty()){
                System.out.println("\nEl arbol esta vacio");
                return;
            }


            avlTree.printAsTree();

        }
    }
}
