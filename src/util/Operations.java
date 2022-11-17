package util;

import structures.*;

import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

public class Operations {

    private static BinaryTree<Integer> binTree;
    private static AVLTree<Integer> avlTree;
    private static Heap<Integer> heap;
    private static final Scanner sc = new Scanner(System.in);

    public static void random(int type) {
        int cant;
        Random ran = new Random();

        System.out.println("\nIngrese la cantidad de elementos del arbol");
        System.out.print("Cantidad: ");
        cant = sc.nextInt();

        if (cant <= 0) {
            System.out.println("\nNo se recomienda crear arboles vacios. Volviendo al menu...");
            Start.switchMode(type);
        }

        switch (type) {
            case 1 -> {
                BinaryTree<Integer> auxBin = new BinaryTree<>();
                System.out.println("\nArbol binario aleatorio");
                for (int i = 0; i < cant; i++) {
                    auxBin.add(ran.nextInt(100));
                }
                binTree = auxBin;
            }
            case 2 -> {
                AVLTree<Integer> auxAvl = new AVLTree<>();
                System.out.println("\nArbol AVL aleatorio");
                for (int i = 0; i < cant; i++) {
                    auxAvl.add(ran.nextInt(100));
                }
                avlTree = auxAvl;
            }
            case 3 -> {
                Heap<Integer> auxHeap = new Heap<>();
                System.out.println("\nMonticulo binario aleatorio");
                for (int i = 0; i < cant; i++) {
                    auxHeap.add(ran.nextInt(100));
                }
                heap = auxHeap;
            }
            default -> {
                System.out.println("La opcion ingresada no es valida. Volviendo al menu...");
                Start.menu();
            }
        }
    }

    public static void manual(int type) {
        int num;

        switch (type) {
            case 1 -> {
                BinaryTree<Integer> auxBin = new BinaryTree<>();
                System.out.println("\nArbol binario manual");
                System.out.println("Ingrese el dato. Ingrese un valor negativo para salir");
                do {
                    System.out.print("\nDato: ");
                    num = sc.nextInt();
                    if (num >= 0) auxBin.add(num);
                } while (num > 0);
                if (auxBin.isEmpty()) {
                    System.out.println("\nNo se recomienda crear arboles vacios. Volviendo al menu...");
                    Start.switchMode(type);
                }
                binTree = auxBin;
            }
            case 2 -> {
                AVLTree<Integer> auxAvl = new AVLTree<>();
                System.out.println("\nArbol AVL manual");
                System.out.println("Ingrese el dato. Ingrese un valor negativo para salir");
                do {
                    System.out.print("\nDato: ");
                    num = sc.nextInt();
                    if (num >= 0) auxAvl.add(num);
                } while (num > 0);
                if (auxAvl.isEmpty()) {
                    System.out.println("\nNo se recomienda crear arboles vacios. Volviendo al menu...");
                    Start.switchMode(type);
                }
                avlTree = auxAvl;
            }
            case 3 -> {
                Heap<Integer> auxHeap = new Heap<>();
                System.out.println("\nMonticulo binario manual");
                System.out.println("Ingrese el dato. Ingrese un valor negativo para salir");
                do {
                    System.out.print("\nDato: ");
                    num = sc.nextInt();
                    if (num >= 0) auxHeap.add(num);
                } while (num > 0);
                if (auxHeap.isEmpty()) {
                    System.out.println("\nNo se recomienda crear arboles vacios. Volviendo al menu...");
                    Start.switchMode(type);
                }
                heap = auxHeap;
            }
            default -> {
                System.out.println("La opcion ingresada no es valida. Volviendo al menu...");
                Start.menu();
            }
        }
    }

    public static void add(int type) {
        int num;
        System.out.println("Ingrese el valor a agregar");
        num = sc.nextInt();

        switch (type) {
            case 1 -> binTree.add(num);
            case 2 -> avlTree.add(num);
            case 3 -> heap.add(num);
            default -> {
                System.out.println("La opcion ingresada no es valida. Volviendo al menu...");
                Start.menu();
            }
        }

        Start.switchOperation(type);

    }

    public static void search(int type) {
        int num;
        System.out.println("\nIngrese el dato a buscar");
        num = sc.nextInt();

        try {
            switch (type) {
                case 1 -> System.out.println("Elemento encontrado: " + binTree.search(num));
                case 2 -> System.out.println("Elemento encontrado: " + avlTree.search(num));
                case 3 -> System.out.println("Elemento encontrado: " + heap.search(num));
                default -> {
                    System.out.println("La opcion ingresada no es valida. Volviendo al menu...");
                    Start.menu();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Start.switchOperation(type);
        }
        Start.switchOperation(type);
    }

    public static void delete(int type) {
        int num = 0;
        int op;

        if (type == 1 || type == 2) {
            System.out.println("\nIngrese el dato a borrar");
            num = sc.nextInt();
        }

        System.out.println("\nArbol antes de borrar");

        try {
            switch (type) {
                case 1 -> {
                    binTree.printInOrder();
                    binTree.delete(num);
                    System.out.println("\nArbol despues de borrar");
                    binTree.printInOrder();
                }
                case 2 -> {
                    avlTree.printInOrder();
                    avlTree.delete(num);
                    System.out.println("\nArbol despues de borrar");
                    avlTree.printInOrder();
                }
                case 3 -> {
                    System.out.println("\n1. Borrar minimo \n2. Borrar elemento");
                    System.out.print("\nOpcion: ");
                    op = sc.nextInt();

                    if(op == 1){
                        heap.printInOrder();
                        heap.deleteMin();
                        System.out.println("\nArbol despues de borrar");
                        heap.printInOrder();
                    }
                    else if(op == 2){
                        System.out.println("\nIngrese el dato a borrar");
                        num = sc.nextInt();
                        heap.printInOrder();
                        heap.delete(num);
                        System.out.println("\nArbol despues de borrar");
                        heap.printInOrder();
                    }
                    else{
                        System.out.println("La opcion ingresada no es valida.");
                    }
                }
                default -> {
                    System.out.println("La opcion ingresada no es valida. Volviendo al menu...");
                    Start.menu();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Start.switchOperation(type);
        }
        Start.switchOperation(type);
    }

    public static void print(int type) {
        System.out.println("\nArbol ordenado");
        try {
            switch (type) {
                case 1 -> binTree.printInOrder();
                case 2 -> avlTree.printInOrder();
                case 3 -> heap.printInOrder();
                default -> {
                    System.out.println("La opcion ingresada no es valida. Volviendo al menu...");
                    Start.menu();
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            Start.switchOperation(type);
        }
        Start.switchOperation(type);
    }

    public static void draw(int type) {
        System.out.println("\nDibujo de arbol");
        switch (type) {
            case 1 -> binTree.printLikeTree(new PrintStream(System.out));
            case 2 -> avlTree.printLikeTree(new PrintStream(System.out));
            case 3 -> heap.printFancyTree();
            default -> {
                System.out.println("La opcion ingresada no es valida. Volviendo al menu...");
                Start.menu();
            }
        }
        Start.switchOperation(type);
    }
}
