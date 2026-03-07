/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Algoritmos;
import EDD.Cola;
import EDD.Lista;
import Grafos.Grafo;
import Grafos.Vertice;
import Grafos.Arista;
/**
 *
 * @author PC
 */
public class Dijkstra {

/**
 * clase encargada de los algoritmos de análisis sobre la red proteica,
 * permite implementar la búsqueda de componentes conexos, 
 * identificación de Hubs y el camino más corto usando el algoritmo Dijkstra.
 */
    /**
     * Identifica todos los componentes conexos del grafo.
     * Se basa en un recorrido en amplitud (BFS) para agrupar proteínas 
     * que interactúan entre sí.
     *  aqui el grafo es el que contiene la red proteica a analizar.
     * y retorna una lista de listas, donde cada sublista contiene 
     * los nombres de las proteínas de un componente.
     */
    public Lista<Lista<String>> buscarComponentesConexos(Grafo grafo) {
        Lista<Lista<String>> todosLosComponentes = new Lista<>();
        Lista<String> visitadosGlobal = new Lista<>();
        
        Vertice actual = grafo.getPrimero();
        while (actual != null) {
            if (!visitadosGlobal.existe(actual.getNombre())) {
                Lista<String> componenteActual = grafo.obtenerComplejoProteico(actual.getNombre());
                for (int i = 0; i < componenteActual.getTamaño(); i++) {
                    visitadosGlobal.insertarFinal(componenteActual.obtener(i));
                }
                todosLosComponentes.insertarFinal(componenteActual);
            }
            actual = actual.getSiguiente();
        }
        return todosLosComponentes;
    }

    /**
     * Permite identificar la proteína con el mayor número de interacciones.
     * y retorna un string con el nombre de la proteína Hub y su cantidad de conexiones.
     */
    public String identificarHub(Grafo grafo) {
        if (grafo.getPrimero() == null) return "No hay proteínas";
        
        Vertice maxHub = grafo.getPrimero();
        int maxConexiones = contarConexiones(maxHub);
        
        Vertice actual = grafo.getPrimero().getSiguiente();
        while (actual != null) {
            int conexionesActual = contarConexiones(actual);
            if (conexionesActual > maxConexiones) {
                maxConexiones = conexionesActual;
                maxHub = actual;
            }
            actual = actual.getSiguiente();
        }
        return maxHub.getNombre() + " (" + maxConexiones + " interacciones)";
    }

    /**
     * Cuenta las aristas conectadas a un vértice específico, v siendo el vertice
     * que se va a analizar y nos retorna la cantidad de aristas en su lista
     * de adyacencia.
     */
    private int contarConexiones(Vertice v) {
        int cont = 0;
        Arista aux = v.getListaAdyacencia();
        while (aux != null) {
            cont++;
            aux = aux.getSig();
        }
        return cont;
    }
    
    /**
     * El algoritmo de Dijkstra es para indicar la ruta metabólica más corta.
     * siendo el grafo de proteinas, los nombres de las proteinas tanto el de 
     * origen como el de destino y nos retorna un string que detalla el camino 
     * seguido por la ruta metabolica y el costo total acumulado.
     *
     */
    public String ejecutarDijkstra(Grafo grafo, String inicio, String destino) {
        if (grafo.buscarVertice(inicio) == null || grafo.buscarVertice(destino) == null) 
            return "Error: Proteína no encontrada.";

        int n = grafo.getCantidadVertices();
        Lista<String> nombres = new Lista<>();
        Lista<Double> dist = new Lista<>();
        Lista<String> padre = new Lista<>();
        Lista<Boolean> visitado = new Lista<>();

        Vertice auxV = grafo.getPrimero();
        while (auxV != null) {
            nombres.insertarFinal(auxV.getNombre());
            dist.insertarFinal(auxV.getNombre().equals(inicio) ? 0.0 : Double.MAX_VALUE);
            padre.insertarFinal(null);
            visitado.insertarFinal(false);
            auxV = auxV.getSiguiente();
        }

        for (int i = 0; i < n; i++) {
            int u = -1;
            double min = Double.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (!visitado.obtener(j) && dist.obtener(j) < min) {
                    min = dist.obtener(j);
                    u = j;
                }
            }

            if (u == -1) break;
            visitado.setValor(u, true);
            String nombreU = nombres.obtener(u);
            if (nombreU.equals(destino)) break;

            Vertice vU = grafo.buscarVertice(nombreU);
            Arista ady = vU.getListaAdyacencia();
            while (ady != null) {
                int vIdx = buscarIndice(nombres, ady.getNombreproteina());
                double peso = ady.getPeso();
                if (dist.obtener(u) + peso < dist.obtener(vIdx)) {
                    dist.setValor(vIdx, dist.obtener(u) + peso);
                    padre.setValor(vIdx, nombreU);
                }
                ady = ady.getSig();
            }
        }
        return formatarResultado(nombres, padre, dist, inicio, destino);
    }

    private int buscarIndice(Lista<String> lista, String nombre) {
        for (int i = 0; i < lista.getTamaño(); i++) {
            if (lista.obtener(i).equals(nombre)) return i;
        }
        return -1;
    }
    
    /**
     * Construye una representación de la ruta metabólica encontrada, 
     * recorre la lista de padres desde el destino hasta el origen para reconstruir 
     * el camino y concatena el costo total acumulado
     * nombres lista de nombres de todas las proteínas procesadas
     * padre lista que contiene la procedencia de cada nodo para reconstruir el camino
     * dist lista con las distancias mínimas finales desde el origen
     * ini nombre de la proteína inicial
     * dest nombre de la proteína destino
     * retorna una cadena formateada con la ruta (ej: P1 -> P2 -> P3) y el costo acumulado, 
     * o un mensaje de error si no hay conexión.
     */
    private String formatarResultado(Lista<String> nombres, Lista<String> padre, Lista<Double> dist, String ini, String dest) {
        int idx = buscarIndice(nombres, dest);
        if (idx == -1 || dist.obtener(idx) == Double.MAX_VALUE) return "No hay conexión entre " + ini + " y " + dest;
        
        String camino = dest;
        String actual = padre.obtener(idx);
        while (actual != null) {
            camino = actual + " -> " + camino;
            actual = padre.obtener(buscarIndice(nombres, actual));
        }
        return "Ruta: " + camino + "\nCosto total: " + dist.obtener(idx);
    }
}
