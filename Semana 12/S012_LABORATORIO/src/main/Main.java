package main;

import analisis.AnalizadorEficiencia;
import modelo.Estudiante;
import registro.SistemaRegistroBST;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final SistemaRegistroBST sistema = new SistemaRegistroBST();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion(1, 7);
            procesarOpcion(opcion);
        } while (opcion != 7);
        sc.close();
    }
// ══════════════════════════════════════════
// MENU
// ══════════════════════════════════════════

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("================================================");
        System.out.println(" SISTEMA DE REGISTRO ACADEMICO - BST ");
        System.out.println("================================================");
        System.out.println(" [1] Agregar estudiante");
        System.out.println(" [2] Buscar estudiante por codigo");
        System.out.println(" [3] Modificar promedio general");
        System.out.println(" [4] Mostrar directorio de estudiantes");
        System.out.println(" [5] Mostrar metricas del arbol");
        System.out.println(" [6] Ejecutar pruebas de eficiencia");
        System.out.println(" [7] Salir");
        System.out.println("================================================");
        System.out.print(" Seleccione una opcion: ");
    }

    private static void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 ->
                opcionAgregar();
            case 2 ->
                opcionBuscar();
            case 3 ->
                opcionModificar();
            case 4 ->
                opcionMostrar();
            case 5 ->
                opcionMetricas();
            case 6 ->
                opcionEficiencia();
            case 7 ->
                System.out.println("\n Sistema cerrado. Hasta luego.\n");
        }
    }
// ══════════════════════════════════════════
// OPCIONES DEL MENU
// ══════════════════════════════════════════

    private static void opcionAgregar() {
        separador("AGREGAR ESTUDIANTE");
        int codigo = leerCodigoValido();
        sc.nextLine();
        String nombre = leerNombreValido();
        double promedio = leerPromedioValido();
        boolean agregado = sistema.agregarEstudiante(codigo, nombre, promedio);
        System.out.println();
        if (agregado) {
            System.out.println(" >> Estudiante agregado correctamente.");
        } else {
            System.out.println(" >> Ya existe un estudiante con el codigo " + codigo
                    + ".");
        }
    }

    private static void opcionBuscar() {
        if (sistema.estaVacio()) {
            System.out.println("\n >> El arbol esta vacio. Agregue estudiantes primero.");
            return;
        }
        separador("BUSCAR ESTUDIANTE");
        int codigo = leerCodigoValido();
        Estudiante encontrado = sistema.buscarEstudiante(codigo);
        System.out.println();
        if (encontrado != null) {
            System.out.println(" >> Estudiante encontrado:");
            System.out.println(" " + encontrado);
            System.out.println(" >> Comparaciones realizadas: "
                    + sistema.getUltimasComparaciones());
        } else {
            System.out.println(" >> No existe ningun estudiante con el codigo " + codigo
                    + ".");
        }
    }

    private static void opcionModificar() {
        if (sistema.estaVacio()) {
            System.out.println("\n >> El arbol esta vacio. Agregue estudiantes primero.");
            return;
        }
        separador("MODIFICAR PROMEDIO");
        int codigo = leerCodigoValido();
        double nuevoPromedio = leerPromedioValido();
        boolean modificado = sistema.modificarPromedio(codigo, nuevoPromedio);
        System.out.println();
        if (modificado) {
            System.out.println(" >> Promedio actualizado correctamente.");
        } else {
            System.out.println(" >> Estudiante con codigo " + codigo + " no encontrado.");
        }
    }

    private static void opcionMostrar() {
        if (sistema.estaVacio()) {
            System.out.println("\n >> El arbol esta vacio. Agregue estudiantes primero.");
            return;
        }
        separador("DIRECTORIO DE ESTUDIANTES");
        System.out.println(" (Ordenado de menor a mayor por codigo)");
        System.out.println();
        sistema.mostrarDirectorioOrdenado();
    }

    private static void opcionMetricas() {
        if (sistema.estaVacio()) {
            System.out.println("\n >> El arbol esta vacio. Agregue estudiantes primero.");
            return;
        }
        separador("METRICAS DEL ARBOL");
        sistema.mostrarMetricas();
    }

    private static void opcionEficiencia() {
        separador("PRUEBAS DE EFICIENCIA");
        new AnalizadorEficiencia().ejecutarPruebas();
    }
// ══════════════════════════════════════════
// UTILIDAD
// ══════════════════════════════════════════

    private static void separador(String titulo) {
        System.out.println();
        System.out.println(" ----------------------------------------");
        System.out.println(" " + titulo);
        System.out.println(" ----------------------------------------");
    }
// ══════════════════════════════════════════
// VALIDACIONES
// ══════════════════════════════════════════

    private static int leerOpcion(int min, int max) {
        while (true) {
            try {
                int op = sc.nextInt();
                sc.nextLine();
                if (op >= min && op <= max) {
                    return op;
                }
                System.out.print(" Opcion invalida. Ingrese entre " + min + " y " + max + ":");
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.print(" Solo se permiten numeros. Intente de nuevo: ");
            }
        }
    }

    private static int leerCodigoValido() {
        while (true) {
            try {
                System.out.print(" Codigo (entero positivo): ");
                int codigo = sc.nextInt();
                if (codigo > 0) {
                    return codigo;
                }
                System.out.println(" El codigo debe ser un numero positivo.");
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println(" El codigo debe ser numerico. Intente de nuevo.");
            }
        }
    }

    private static String leerNombreValido() {
        while (true) {
            System.out.print(" Nombre completo: ");
            String nombre = sc.nextLine().trim();
            if (!nombre.isEmpty()) {
                return nombre;
            }
            System.out.println(" El nombre no puede estar vacio.");
        }
    }

    private static double leerPromedioValido() {
        while (true) {
            try {
                System.out.print(" Promedio (0.0 a 20.0): ");
                double p = sc.nextDouble();
                sc.nextLine();
                if (p >= 0.0 && p <= 20.0) {
                    return p;
                }
                System.out.println(" El promedio debe estar entre 0.0 y 20.0.");
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println(" El promedio debe ser numerico. Intente de nuevo.");
            }
        }
    }
}
