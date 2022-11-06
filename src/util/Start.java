package util;

import java.util.Scanner;

public class Start {
    private static int op;
    private static final Scanner sc = new Scanner(System.in);

    public static void menu() {
        int tree;

        tree = switchTree();
        switchMode(tree);
    }

    private static int switchTree() {
        System.out.println("Ingrese el tipo de estructura con la cual desea trabajar:\n\t0- Salir del programa\n\t1- Arbol Binario de Busqueda\n\t2- Arbol AVL\n\t3- Monticulo Binario");
        System.out.print("\n\tOpcion: ");
        op = sc.nextInt();

        if(op == 0){
            System.out.println("Cierre del programa");
            System.exit(0);
        }

        if (op < 0 || op > 3) {
            System.out.println("La opcion ingresada es invalida");
            switchTree();
        }


        return op;
    }

    public static void switchMode(int tree) {
        System.out.println("Ingrese la forma de carga de valores al arbol:\n\t0- Salir del programa\n\t1- Manual\n\t2- Aleatoria\n\t3- Volver al menu anterior");
        System.out.print("\n\tOpcion: ");
        int mode = sc.nextInt();

        switch (mode) {
            case 0 -> {
                System.out.println("Cierre del programa");
                System.exit(0);
            }
            case 1 -> Operations.manual(tree);
            case 2 -> Operations.random(tree);
            case 3 -> menu();
            default -> {
                System.out.println("La opcion ingresada no es valida.");
                switchMode(tree);
            }
        }
        switchOperation(tree);
    }

    public static void switchOperation(int tree) {
        System.out.println("Seleccione la operacion a realizar:\n\t0- Salir del programa\n\t1- Agregar dato\n\t2- Buscar dato\n\t3- Borrar dato\n\t4- Imprimir arbol en orden\n\t5- Dibujar forma de arbol (solo ABB y AVL)\n\t6- Volver al menu anterior");
        System.out.print("\n\tOpcion: ");
        op = sc.nextInt();

        switch (op) {
            case 0 -> {
                System.out.println("Cierre del programa");
                System.exit(0);
            }
            case 1 -> Operations.add(tree);
            case 2 -> Operations.search(tree);
            case 3 -> Operations.delete(tree);
            case 4 -> Operations.print(tree);
            case 5 -> Operations.draw(tree);
            case 6 -> switchMode(tree);
            default -> {
                System.out.println("\nOpcion invalida. Volviendo al menu...");
                switchOperation(tree);
            }
        }
    }
}


