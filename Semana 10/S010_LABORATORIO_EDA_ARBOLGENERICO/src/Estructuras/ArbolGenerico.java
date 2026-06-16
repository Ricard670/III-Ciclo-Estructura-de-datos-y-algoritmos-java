package Estructuras;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ArbolGenerico<T> {

    public static void main(String[] args) {
        // Creacion del Catalogo Principal (Raiz)
        ElementoCatalogo raizCatalogo = new ElementoCatalogo("Catalogo Principal", "Categoria");
        ArbolGenerico<ElementoCatalogo> catalogo = new ArbolGenerico<>(raizCatalogo);

        System.out.println("--- 1. PROBANDO AGREGAR ELEMENTOS ---");
        // Insertamos categorias de nivel 1
        catalogo.agregarElemento("Catalogo Principal", "Electronica", "Categoria");
        catalogo.agregarElemento("Catalogo Principal", "Ropa", "Categoria");

        // ---> NUEVA CATEGORIA AGREGADA AQUI <---
        catalogo.agregarElemento("Catalogo Principal", "Deportes", "Categoria");
        catalogo.agregarElemento("Deportes", "Calzado Deportivo", "Categoria");
        catalogo.agregarElemento("Calzado Deportivo", "Zapatillas de Correr", "Producto");

        // Insertamos en subcategorias de Electronica
        catalogo.agregarElemento("Electronica", "Laptops", "Categoria");
        catalogo.agregarElemento("Electronica", "TV", "Producto");

        // Insertamos un producto final en Laptops
        catalogo.agregarElemento("Laptops", "Laptop Gamer XYZ", "Producto");

        System.out.println("\n--- PROBANDO VALIDACIONES DE ERROR ---");
        // Intento 1: Padre no existe
        catalogo.agregarElemento("Hogar", "Licuadora", "Producto");
        // Intento 2: Insertar dentro de un Producto terminal
        catalogo.agregarElemento("TV", "SmartTV 4K", "Producto");

        System.out.println("\n--- 2. PROBANDO MOSTRAR CATALOGO ---");
        // Mostramos la jerarquia completa empezando desde nivel 0
        catalogo.mostrarCatalogo(catalogo.getRaiz(), 0);

        System.out.println("\n--- 3. PROBANDO BUSQUEDAS ---");
        // Busqueda 1: Buscar una categoria existente (Electronica)
        catalogo.buscarElemento("Electronica");
        System.out.println();

        // Busqueda 2: Buscar la nueva categoria agregada (Deportes)
        catalogo.buscarElemento("Deportes");
        System.out.println();

        // Busqueda 3: Buscar un producto terminal
        catalogo.buscarElemento("Laptop Gamer XYZ");
        System.out.println();

        // Busqueda 4: Buscar algo que no existe
        catalogo.buscarElemento("Zapatos");
    }

    private NodoGenerico<T> raiz;

    public ArbolGenerico(T raizData) {
        this.raiz = new NodoGenerico<>(raizData);
    }

    public ArbolGenerico() {
        this.raiz = null;
    }

    public NodoGenerico<T> getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoGenerico<T> raiz) {
        this.raiz = raiz;
    }

    public void imprimirPreOrden(NodoGenerico<T> nodo) {
        if (nodo != null) {
            System.out.print(nodo.getData() + " ");
            for (NodoGenerico<T> hijo : nodo.getHijos()) {
                imprimirPreOrden(hijo);
            }
        }
    }

    public void imprimirPostOrden(NodoGenerico<T> nodo) {
        if (nodo != null) {
            for (NodoGenerico<T> hijo : nodo.getHijos()) {
                imprimirPostOrden(hijo);
            }
            System.out.print(nodo.getData() + " ");
        }
    }

    public void imprimirPorNiveles() {
        if (raiz == null) {
            return;
        }

        Deque<NodoGenerico<T>> cola = new ArrayDeque<>();
        cola.add(raiz);

        while (!cola.isEmpty()) {
            NodoGenerico<T> nodoActual = cola.poll();
            System.out.print(nodoActual.getData() + " ");
            cola.addAll(nodoActual.getHijos());
        }
        System.out.println();
    }

    private NodoGenerico<T> buscarNodo(NodoGenerico<T> actual, String nombreBuscar) {
        if (actual == null) {
            return null;
        }

        ElementoCatalogo dato = (ElementoCatalogo) actual.getData();
        if (dato.getNombre().equals(nombreBuscar)) {
            return actual;
        }

        for (NodoGenerico<T> hijo : actual.getHijos()) {
            NodoGenerico<T> encontrado = buscarNodo(hijo, nombreBuscar);
            if (encontrado != null) {
                return encontrado;
            }
        }
        return null;
    }

    //2. AGREGAR ELEMENTO 
    @SuppressWarnings("unchecked")
    public void agregarElemento(String categoriaDestino, String nombreNuevoElemento, String tipoElemento) {
        if (raiz == null) {
            return;
        }

        // Usamos el auxiliar para buscar sin ensuciar este método
        NodoGenerico<T> nodoDestino = buscarNodo(raiz, categoriaDestino);

        if (nodoDestino == null) {
            System.out.println("Operacion no valida: La categoria destino no fue encontrada.");
            return;
        }

        ElementoCatalogo datoPadre = (ElementoCatalogo) nodoDestino.getData();

        if (datoPadre.getTipo().equals("Categoria")) {
            ElementoCatalogo nuevoDato = new ElementoCatalogo(nombreNuevoElemento, tipoElemento);
            nodoDestino.agregarHijo(new NodoGenerico<>((T) nuevoDato));
            System.out.println("Exito: Elemento agregado correctamente.");
        } else {
            System.out.println("Operacion no valida: No se puede agregar un elemento dentro de un 'Producto'.");
        }
    }

    // 3. MOSTRAR CATÁLOGO (Indentación Base 0)
    public void mostrarCatalogo(NodoGenerico<T> nodoActual, int nivel) {
        if (nodoActual == null) {
            return;
        }
        String espacios = "  - ".repeat(nivel);

        ElementoCatalogo dato = (ElementoCatalogo) nodoActual.getData();
        System.out.println(espacios + dato.getNombre() + " (" + dato.getTipo() + ")");

        for (NodoGenerico<T> hijo : nodoActual.getHijos()) {
            mostrarCatalogo(hijo, nivel + 1);
        }
    }

    // 4. BUSCAR ELEMENTO (Reutilizando el auxiliar)
    public void buscarElemento(String nombre) {
        if (this.raiz == null) {
            return;
        }
        // Reutilizamos la búsqueda limpia
        NodoGenerico<T> nodoEncontrado = buscarNodo(this.raiz, nombre);

        if (nodoEncontrado != null) {
            ElementoCatalogo dato = (ElementoCatalogo) nodoEncontrado.getData();
            System.out.println("Elemento Encontrado: " + dato.getNombre() + " (" + dato.getTipo() + ")");

            if (dato.getTipo().equals("Categoria")) {
                System.out.println("Contenido directo:");
                for (NodoGenerico<T> hijo : nodoEncontrado.getHijos()) {
                    ElementoCatalogo datoHijo = (ElementoCatalogo) hijo.getData();
                    System.out.println("  - " + datoHijo.getNombre());
                }
            }
        } else {
            System.out.println("Operacion no valida: El elemento '" + nombre + "' no existe en el catalogo.");
        }
    }
}
