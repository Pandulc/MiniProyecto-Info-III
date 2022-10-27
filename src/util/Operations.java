package util;

import structures.*;

import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

public class Operations {

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

        System.out.println("Arbol binario aleatorio creado satisfactoriamente");
        return binTree;
    }

    public static BinaryTree<Integer> manualBinary() {
        int num;
        BinaryTree<Integer> binTree = new BinaryTree<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("Arbol binario manual");
        System.out.println("Ingrese el dato a ingresar. Ingrese un valor negativo para salir");

        do {
            System.out.print("\nDato: ");
            num = sc.nextInt();
            if(num >= 0) binTree.add(num);
        } while (num >= 0);

        System.out.println("Arbol binario manual creado satisfactoriamente");
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

        System.out.println("Arbol AVL aleatorio creado satisfactoriamente");
        return avlTree;
    }

    public static AVLTree<Integer> manualAVL() {
        int num;
        AVLTree<Integer> avlTree = new AVLTree<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("Arbol binario manual");
        System.out.println("Ingrese el dato a ingresar. Ingrese un valor negativo para salir");

        do {
            System.out.print("\nDato: ");
            num = sc.nextInt();
            if(num >= 0) avlTree.add(num);
        } while (num > 0);

        System.out.println("Arbol AVL manual creado satisfactoriamente");
        return avlTree;
    }

    public static void orderTree(BinaryTree<Integer> binTree, AVLTree<Integer> avlTree) {
        int op2;
        Scanner sc = new Scanner(System.in);

        System.out.println("\nArbol ordenado");
        System.out.println("\n¿Que arbol desea imprimir?\n1- Arbol binario de busqueda\n2- Arbol AVL");
        op2 = sc.nextInt();

        if (op2 == 1) {
            if (binTree.isEmpty()) {
                System.out.println("\nEl arbol esta vacio");
                return;
            }
            binTree.printInOrder();
        } else if (op2 == 2) {
            if (avlTree.isEmpty()) {
                System.out.println("\nEl arbol esta vacio");
                return;
            }
            avlTree.printInOrder();
        } else System.out.println("Opcion invalida");
    }

    public static void drawTree(BinaryTree<Integer> binTree, AVLTree<Integer> avlTree) {
        int op2;
        Scanner sc = new Scanner(System.in);

        System.out.println("\nArbol ordenado");
        System.out.println("¿Que arbol desea imprimir?\n1- Arbol binario de busqueda\n2- Arbol AVL");
        op2 = sc.nextInt();

        if (op2 == 1) {
            if (binTree.isEmpty()) {
                System.out.println("\nEl arbol esta vacio");
                return;
            }

            binTree.printLikeTree(new PrintStream(System.out));
        } else if (op2 == 2) {
            if (avlTree.isEmpty()) {
                System.out.println("\nEl arbol esta vacio");
                return;
            }

            avlTree.printLikeTree(new PrintStream(System.out));

        }
    }
}
