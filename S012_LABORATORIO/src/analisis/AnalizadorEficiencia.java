package analisis;

import registro.SistemaRegistroBST;
import java.io.OutputStream;
import java.io.PrintStream;

public class AnalizadorEficiencia {

    private static final int REPETICIONES = 10;
// PrintStream que no imprime nada — usado para silenciar el recorrido InOrden
    private static final PrintStream SILENCIO = new PrintStream(new OutputStream() {
        public void write(int b) {
            /* descarta todo */ }
    });

    public void ejecutarPruebas() {
        int[] tamanios = {5, 10, 20};
        System.out.println();
        System.out.println(" Operacion | n | Tiempo promedio (ns)");
        System.out.println(" ---------------------|-----|---------------------");
        for (int n : tamanios) {
            medirInsercion(n);
        }
        for (int n : tamanios) {
            medirBusqueda(n);
        }
        for (int n : tamanios) {
            medirModificacion(n);
        }
        for (int n : tamanios) {
            medirInOrden(n);
        }
        System.out.println(" ---------------------|-----|---------------------");
    }

    private void medirInsercion(int n) {
        long total = 0;
        for (int r = 0; r < REPETICIONES; r++) {
            SistemaRegistroBST arbol = new SistemaRegistroBST();
            long inicio = System.nanoTime();
            for (int i = 1; i <= n; i++) {
                arbol.agregarEstudiante(i * 10, "Est" + i, 10 + (i % 10));
            }
            total += System.nanoTime() - inicio;
        }
        System.out.printf(" %-20s | %-3d | %,d%n", "Insercion", n, total
                / REPETICIONES);
    }

    private void medirBusqueda(int n) {
        SistemaRegistroBST arbol = construirArbol(n);
        long total = 0;
        for (int r = 0; r < REPETICIONES; r++) {
            long inicio = System.nanoTime();
            arbol.buscarEstudiante((n / 2) * 10);
            total += System.nanoTime() - inicio;
        }
        System.out.printf(" %-20s | %-3d | %,d%n", "Busqueda", n, total
                / REPETICIONES);
    }

    private void medirModificacion(int n) {
        SistemaRegistroBST arbol = construirArbol(n);
        long total = 0;
        for (int r = 0; r < REPETICIONES; r++) {
            long inicio = System.nanoTime();
            arbol.modificarPromedio((n / 2) * 10, 18.0);
            total += System.nanoTime() - inicio;
        }
        System.out.printf(" %-20s | %-3d | %,d%n", "Modificacion", n, total
                / REPETICIONES);
    }

    private void medirInOrden(int n) {
        SistemaRegistroBST arbol = construirArbol(n);
        long total = 0;
        PrintStream salidaReal = System.out; // guardar salida real
        for (int r = 0; r < REPETICIONES; r++) {
            System.setOut(SILENCIO); // silenciar durante medición
            long inicio = System.nanoTime();
            arbol.mostrarDirectorioOrdenado();
            total += System.nanoTime() - inicio;
            System.setOut(salidaReal); // restaurar salida real
        }
        System.out.printf(" %-20s | %-3d | %,d%n", "Recorrido InOrden", n, total
                / REPETICIONES);
    }

    private SistemaRegistroBST construirArbol(int n) {
        SistemaRegistroBST arbol = new SistemaRegistroBST();
        for (int i = 1; i <= n; i++) {
            arbol.agregarEstudiante(i * 10, "Est" + i, 10 + (i % 10));
        }
        return arbol;
    }
}
