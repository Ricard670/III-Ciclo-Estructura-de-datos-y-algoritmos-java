/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registro;

import modelo.ArbolBinarioBusqueda;
import modelo.Estudiante;
import modelo.NodoBinario;

public class SistemaRegistroBST extends ArbolBinarioBusqueda<Estudiante> {
// ultima cantidad de comparaciones realizadas en busqueda

    private int ultimasComparaciones = 0;
// ══════════════════════════════════════════
// AGREGAR
// ══════════════════════════════════════════

    public boolean agregarEstudiante(int codigo, String nombre, double promedio) {
        Estudiante estudiante = new Estudiante(codigo, nombre, promedio);
        int antes = contarTotal(raiz);
        raiz = agregarRecursivo(raiz, estudiante);
        return contarTotal(raiz) > antes; // true si se insertó, false si era duplicado
    }

    private NodoBinario<Estudiante> agregarRecursivo(NodoBinario<Estudiante> nodo, Estudiante estudiante) {
        if (nodo == null) {
            return new NodoBinario<>(estudiante);
        }
        int cmp = estudiante.compareTo(nodo.dato);
        if (cmp < 0) {
            nodo.izquierdo = agregarRecursivo(nodo.izquierdo, estudiante);
        } else if (cmp > 0) {
            nodo.derecho = agregarRecursivo(nodo.derecho, estudiante);
        }
// cmp == 0 → duplicado, no se inserta
        return nodo;
    }
// ══════════════════════════════════════════
// BUSCAR
// ══════════════════════════════════════════

    public Estudiante buscarEstudiante(int codigo) {
        ultimasComparaciones = 0;
        return buscarRecursivo(raiz, codigo);
    }

    private Estudiante buscarRecursivo(NodoBinario<Estudiante> nodo, int codigo) {
        if (nodo == null) {
            return null;
        }
        ultimasComparaciones++;
        Estudiante temporal = new Estudiante(codigo, "", 0);
        int cmp = temporal.compareTo(nodo.dato);
        if (cmp == 0) {
            return nodo.dato;
        } else if (cmp < 0) {
            return buscarRecursivo(nodo.izquierdo, codigo);
        } else {
            return buscarRecursivo(nodo.derecho, codigo);
        }
    }
// ══════════════════════════════════════════
// MODIFICAR PROMEDIO
// ══════════════════════════════════════════

    public boolean modificarPromedio(int codigo, double nuevoPromedio) {
        return modificarRecursivo(raiz, codigo, nuevoPromedio);
    }

    private boolean modificarRecursivo(NodoBinario<Estudiante> nodo, int codigo,
            double nuevoPromedio) {
        if (nodo == null) {
            return false;
        }
        Estudiante temporal = new Estudiante(codigo, "", 0);
        int cmp = temporal.compareTo(nodo.dato);
        if (cmp == 0) {
            nodo.dato.setPromedioGeneral(nuevoPromedio); // solo modifica el promedio
            return true;
        } else if (cmp < 0) {
            return modificarRecursivo(nodo.izquierdo, codigo, nuevoPromedio);
        } else {
            return modificarRecursivo(nodo.derecho, codigo, nuevoPromedio);
        }
    }
// ══════════════════════════════════════════
// MOSTRAR DIRECTORIO (InOrden)
// ══════════════════════════════════════════

    public void mostrarDirectorioOrdenado() {
        recorridoInOrden(raiz);
    }

    private void recorridoInOrden(NodoBinario<Estudiante> nodo) {
        if (nodo == null) {
            return;
        }
        recorridoInOrden(nodo.izquierdo);
        System.out.println(" " + nodo.dato);
        recorridoInOrden(nodo.derecho);
    }
// ══════════════════════════════════════════
// MÉTRICAS
// ══════════════════════════════════════════

    public void mostrarMetricas() {
        System.out.println(" Total de estudiantes : " + contarTotal(raiz));
        System.out.println(" Altura del árbol : " + calcularAltura(raiz));
        System.out.println(" Código de la raíz : " + (raiz != null
                ? raiz.dato.getCodigoEstudiante() : "N/A"));
        System.out.println(" Comparaciones (última búsqueda): "
                + ultimasComparaciones);
        System.out.println(" Promedio más alto : " + String.format("%.2f",
                promedioMaximo(raiz)));
        System.out.println(" Promedio más bajo : " + String.format("%.2f",
                promedioMinimo(raiz)));
    }

    private int contarTotal(NodoBinario<Estudiante> nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contarTotal(nodo.izquierdo) + contarTotal(nodo.derecho);
    }

    private int calcularAltura(NodoBinario<Estudiante> nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + Math.max(calcularAltura(nodo.izquierdo),
                calcularAltura(nodo.derecho));
    }

    private double promedioMaximo(NodoBinario<Estudiante> nodo) {
        if (nodo == null) {
            return Double.MIN_VALUE;
        }
        double max = nodo.dato.getPromedioGeneral();
        return Math.max(max, Math.max(promedioMaximo(nodo.izquierdo),
                promedioMaximo(nodo.derecho)));
    }

    private double promedioMinimo(NodoBinario<Estudiante> nodo) {
        if (nodo == null) {
            return Double.MAX_VALUE;
        }
        double min = nodo.dato.getPromedioGeneral();
        return Math.min(min, Math.min(promedioMinimo(nodo.izquierdo),
                promedioMinimo(nodo.derecho)));
    }
// getter para el analizador

    public int getUltimasComparaciones() {
        return ultimasComparaciones;
    }
}
