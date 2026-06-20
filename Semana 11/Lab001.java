package util;

import java.util.Scanner;

public class Lab001 {

    private static BinaryTree arbolActual = null;
    private static String tipoArbolActual = "Ninguno";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n=================================================");
            System.out.println("          MENU: GESTION DE ARBOLES BINARIOS      ");
            System.out.println("=================================================");
            System.out.println("Arbol cargado actualmente: [" + tipoArbolActual + "]");
            System.out.println("-------------------------------------------------");
            System.out.println("1. Construir arbol de nombres");
            System.out.println("2. Construir arbol de enteros");
            System.out.println("3. Construir arbol de strings");
            System.out.println("4. Mostrar recorrido PREORDER");
            System.out.println("5. Mostrar recorrido INORDER");
            System.out.println("6. Mostrar recorrido POSTORDER");
            System.out.println("7. Mostrar recorrido LEVELORDER");
            System.out.println("8. Mostrar todos los recorridos");
            System.out.println("9. Contar nodos del arbol");
            System.out.println("10. Calcular altura del arbol");
            System.out.println("11. Contar hojas del arbol");
            System.out.println("12. Salir");
            System.out.println("=================================================");
            System.out.print("Seleccione una opcion (1-12): ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                procesarOpcion(opcion);
            } else {
                System.out.println("\n[ERROR] Opcion incorrecta. Intente de nuevo.");
                scanner.next(); // Limpia la entrada incorrecta
                opcion = 0; 
            }
        } while (opcion != 12);

        scanner.close();
        System.out.println("\nPrograma finalizado.");
    }

    private static void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                arbolActual = construirArbolNombres();
                tipoArbolActual = "Arbol de Nombres";
                System.out.println("\n[EXITO] Arbol de nombres construido.");
                break;
            case 2:
                arbolActual = construirArbolEnteros();
                tipoArbolActual = "Arbol de Enteros";
                System.out.println("\n[EXITO] Arbol de enteros construido.");
                break;
            case 3:
                arbolActual = construirArbolStrings();
                tipoArbolActual = "Arbol de Strings (Frase)";
                System.out.println("\n[EXITO] Arbol de strings construido.");
                break;
            case 4:
                if (validarArbol()) {
                    System.out.print("\nRecorrido PREORDER: ");
                    arbolActual.preOrder(arbolActual.root);
                    System.out.println();
                }
                break;
            case 5:
                if (validarArbol()) {
                    System.out.print("\nRecorrido INORDER: ");
                    arbolActual.inOrder(arbolActual.root);
                    System.out.println();
                }
                break;
            case 6:
                if (validarArbol()) {
                    System.out.print("\nRecorrido POSTORDER: ");
                    arbolActual.postOrder(arbolActual.root);
                    System.out.println();
                }
                break;
            case 7:
                if (validarArbol()) {
                    System.out.print("\nRecorrido LEVELORDER: ");
                    arbolActual.levelOrder(arbolActual.root);
                    System.out.println();
                }
                break;
            case 8:
                if (validarArbol()) {
                    mostrarTodosLosRecorridos(arbolActual);
                }
                break;
            case 9:
                if (validarArbol()) {
                    System.out.println("\nCantidad de nodos: " + contarNodos(arbolActual.root));
                }
                break;
            case 10:
                if (validarArbol()) {
                    System.out.println("\nAltura del arbol: " + altura(arbolActual.root));
                }
                break;
            case 11:
                if (validarArbol()) {
                    System.out.println("\nCantidad de hojas: " + contarHojas(arbolActual.root));
                }
                break;
            case 12:
                System.out.println("\nSaliendo del sistema...");
                break;
            default:
                System.out.println("\n[ERROR] Opcion incorrecta. Intente de nuevo.");
        }
    }

    private static boolean validarArbol() {
        if (arbolActual == null) {
            System.out.println("\n[AVISO] Primero debe seleccionar que arbol desea construir (Opcion 1, 2 o 3).");
            return false;
        }
        return true;
    }

    // --- Metodos de Construccion ---

    private static BinaryTree<String> construirArbolNombres() {
        BinaryTree<String> tree = new BinaryTree<>();
        tree.root = new NodeTree<>("Ali");
        tree.root.left = new NodeTree<>("Beli");
        tree.root.right = new NodeTree<>("Day");
        tree.root.left.left = new NodeTree<>("Cal");
        tree.root.left.right = new NodeTree<>("Hyi");
        tree.root.right.left = new NodeTree<>("Ile");
        tree.root.right.right = new NodeTree<>("Fey");
        tree.root.left.left.right = new NodeTree<>("Eli");
        tree.root.right.left.left = new NodeTree<>("Gay");
        return tree;
    }

    private static BinaryTree<Integer> construirArbolEnteros() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.root = new NodeTree<>(14);
        tree.root.left = new NodeTree<>(8);
        tree.root.right = new NodeTree<>(26);
        tree.root.left.left = new NodeTree<>(3);
        tree.root.left.right = new NodeTree<>(12);
        tree.root.right.left = new NodeTree<>(19);
        tree.root.left.right.left = new NodeTree<>(9);
        tree.root.right.left.right = new NodeTree<>(23);
        return tree;
    }

    private static BinaryTree<String> construirArbolStrings() {
        BinaryTree<String> tree = new BinaryTree<>();
        tree.root = new NodeTree<>("MIENTRAS");
        tree.root.left = new NodeTree<>("MAS");
        tree.root.right = new NodeTree<>("MAS");
        tree.root.left.left = new NodeTree<>("CONOZCO");
        tree.root.left.right = new NodeTree<>("HOMBRES");
        tree.root.right.left = new NodeTree<>("QUIERO");
        tree.root.right.right = new NodeTree<>("MI");
        tree.root.left.left.left = new NodeTree<>("A");
        tree.root.left.left.right = new NodeTree<>("LOS");
        tree.root.right.left.left = new NodeTree<>("A");
        tree.root.right.left.right = new NodeTree<>("PERRO");
        return tree;
    }

    private static void mostrarTodosLosRecorridos(BinaryTree arbol) {
        System.out.println("\n-------------------------------------------------");
        System.out.println("            RECORRIDOS DEL ARBOL ACTUAL          ");
        System.out.println("-------------------------------------------------");
        System.out.print("PREORDER:   "); arbol.preOrder(arbol.root); System.out.println();
        System.out.print("INORDER:    "); arbol.inOrder(arbol.root); System.out.println();
        System.out.print("POSTORDER:  "); arbol.postOrder(arbol.root); System.out.println();
        System.out.print("LEVELORDER: "); arbol.levelOrder(arbol.root); System.out.println();
    }

    // --- Metodos de Calculo Metric ---

    private static int contarNodos(NodeTree root) {
        if (root == null) return 0;
        return 1 + contarNodos(root.left) + contarNodos(root.right);
    }

    private static int altura(NodeTree root) {
        if (root == null) return -1;
        int altIzq = altura(root.left);
        int altDer = altura(root.right);
        return 1 + Math.max(altIzq, altDer);
    }

    private static int contarHojas(NodeTree root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        return contarHojas(root.left) + contarHojas(root.right);
    }
}
