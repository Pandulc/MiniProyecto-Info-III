package util;

import structures.*;

import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

public class Operations {

    public static BinaryTree<Integer> randomBinary() throws Exception {
        int cant;
        BinaryTree<Integer> binTree = new BinaryTree<>();
        Scanner sc = new Scanner(System.in);
        Random ran = new Random();

        System.out.println("\nArbol binario aleatorio");
        System.out.println("Ingrese la cantidad de elementos del arbol binario");
        System.out.print("\nCantidad: ");
        cant = sc.nextInt();

        for (int i = 0; i < cant; i++) {
            binTree.add(ran.nextInt(100));
        }

        if (!binTree.isEmpty()) System.out.println("Arbol binario aleatorio creado satisfactoriamente");
        else throw new Exception("No pudo crearse el arbol binario o se creo vacio");
        return binTree;
    }

    public static BinaryTree<Integer> manualBinary() throws Exception {
        int num;
        BinaryTree<Integer> binTree = new BinaryTree<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("\nArbol binario manual");
        System.out.println("Ingrese el dato a ingresar. Ingrese un valor negativo para salir");

        do {
            System.out.print("\nDato: ");
            num = sc.nextInt();
            if (num >= 0) binTree.add(num);
        } while (num >= 0);

        if (!binTree.isEmpty()) System.out.println("Arbol binario manual creado satisfactoriamente");
        else throw new Exception("No pudo crearse el arbol binario o se creo vacio");
        return binTree;

    }

    public static AVLTree<Integer> randomAVL() throws Exception {
        int cant;
        AVLTree<Integer> avlTree = new AVLTree<>();
        Scanner sc = new Scanner(System.in);
        Random ran = new Random();

        System.out.println("\nArbol AVL aleatorio");
        System.out.println("Ingrese la cantidad de elementos del arbol AVL");
        System.out.print("\nCantidad: ");
        cant = sc.nextInt();

        for (int i = 0; i < cant; i++) {
            avlTree.add(ran.nextInt(100));
        }

        if (!avlTree.isEmpty()) System.out.println("Arbol binario AVL aleatorio creado satisfactoriamente");
        else throw new Exception("No pudo crearse el arbol binario AVL o se creo vacio");
        return avlTree;
    }

    public static AVLTree<Integer> manualAVL() throws Exception {
        int num;
        AVLTree<Integer> avlTree = new AVLTree<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("\nArbol AVL manual");
        System.out.println("Ingrese el dato a ingresar. Ingrese un valor negativo para salir");

        do {
            System.out.print("\nDato: ");
            num = sc.nextInt();
            if (num >= 0) avlTree.add(num);
        } while (num > 0);

        if (!avlTree.isEmpty()) System.out.println("Arbol binario AVL manual creado satisfactoriamente");
        else throw new Exception("No pudo crearse el arbol binario AVL o se creo vacio");
        return avlTree;
    }

    public static void orderTree(BinaryTree<Integer> binTree, AVLTree<Integer> avlTree) throws Exception {
        int op2;
        Scanner sc = new Scanner(System.in);

        System.out.println("\nArbol ordenado");
        System.out.println("\n¿Que arbol desea imprimir?\n1- Arbol binario de busqueda\n2- Arbol AVL");
        op2 = sc.nextInt();

        if (op2 == 1) {
            if (binTree == null) {
                throw new Exception("\nEl arbol es nulo");
            }
            binTree.printInOrder();
        } else if (op2 == 2) {
            if (avlTree == null) {
                throw new Exception("\nEl arbol es nulo");
            }
            avlTree.printInOrder();
        } else System.out.println("Opcion invalida");
    }

    public static void drawTree(BinaryTree<Integer> binTree, AVLTree<Integer> avlTree) throws Exception {
        int op2;
        Scanner sc = new Scanner(System.in);

        System.out.println("\nArbol ordenado");
        System.out.println("¿Que arbol desea imprimir?\n1- Arbol binario de busqueda\n2- Arbol AVL");
        op2 = sc.nextInt();

        if (op2 == 1) {
            if (binTree == null) {
                throw new Exception("\nEl arbol es nulo");
            }

            binTree.printLikeTree(new PrintStream(System.out));
        } else if (op2 == 2) {
            if (avlTree == null) {
                throw new Exception("\nEl arbol es nulo");
            }
            avlTree.printLikeTree(new PrintStream(System.out));
        }
    }
}
