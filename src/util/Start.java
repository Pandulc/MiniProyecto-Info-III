package util;

import structures.*;

import java.util.Scanner;

public class Start {

    private static BinaryTree<Integer> bTree;
    private static AVLTree<Integer> avlTree;

    public static void menu (){

        int option;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("\nIngrese la opcion a realizar\n");

            System.out.println("1. Crear arbol aleatorio");
            System.out.println("2. Crear arbol manual");
            System.out.println("3. Crear arbol AVL aleatorio");
            System.out.println("4. Crear arbol AVL manual");
            System.out.println("5. Mostrar arbol ordenado");
            System.out.println("6. Mostrar dibujo del arbol\n");
            System.out.println("0. Salir");

            System.out.print("\nOpcion: ");

            option = sc.nextInt();
            switch (option) {
                case 0 -> System.out.println("Cierre del programa");
                case 1 -> bTree = Operations.randomBinary();
                case 2 -> bTree = Operations.manualBinary();
                case 3 -> avlTree = Operations.randomAVL();
                case 4 ->  avlTree = Operations.manualAVL();
                case 5 -> Operations.orderTree(bTree, avlTree);
                case 6 -> Operations.drawTree(bTree, avlTree);
                default -> System.out.println("Se ha ingresado una opcion invalida. Intentelo nuevamente");
            }
        } while (option != 0);

    }
}
