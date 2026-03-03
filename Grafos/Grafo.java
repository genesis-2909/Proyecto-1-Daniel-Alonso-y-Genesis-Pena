/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafos;
import EDD.Cola;
import EDD.Pila;
import EDD.Lista;
import javax.swing.JOptionPane;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.swing_viewer.SwingViewer;

/**
 * Clase grafo que va a implementar un grafo no dirigido mediante una lista
 * de adyacencia para visualizar la interacción de las proteinas
 */
public class Grafo {
    private Vertice primero;
    private int cantidadVertices;

    public Grafo() {
        this.primero = null;
        this.cantidadVertices = 0;
    }
    
    /**
     * Se crea un nuevo objeto vertice si no existe en el grafo
     */
    private void insertarVertice(String nombre) {
        if (buscarVertice(nombre) == null) {
            Vertice nuevo = new Vertice(nombre);
            nuevo.setSiguiente(primero);
            primero = nuevo;
            cantidadVertices++;
        }
    }
    /**
     * Se establece una conexión dirigida desde un origen a un destino
     * origen proteína que tendrá la arista en su lista
     * destino nombre de la proteina destino
     * peso valor de la interacción
     */
    private void vincular(String origen, String destino, double peso) {
        Vertice vertice = buscarVertice(origen);
        if (vertice != null) {
            Arista nueva = new Arista(destino, peso);
            nueva.setSig(vertice.getListaAdyacencia());
            vertice.setListaAdyacencia(nueva);
        }
    }
    
    /**
     * Busca una proteína en la lista global de vertices.
     */
    public Vertice buscarVertice(String nombre) {
        Vertice aux = primero;
        while (aux != null) {
            if (aux.getNombre().equalsIgnoreCase(nombre)) {
                return aux;
            }
            aux = aux.getSiguiente();
        }
        return null;
    }

   /**
    * Elimina una proteína y todas sus interacciones en el grafo
    */
public void eliminarProteina(String nombre) {
    if (primero == null) return;

    /** Quitar el vértice de la lista principal */
    if (primero.getNombre().equals(nombre)) {
        primero = primero.getSiguiente();
        cantidadVertices--;
    } else {
        Vertice ant = primero;
        Vertice act = primero.getSiguiente();
        while (act != null) {
            if (act.getNombre().equals(nombre)) {
                ant.setSiguiente(act.getSiguiente());
                cantidadVertices--;
                break;
            }
            ant = act;
            act = act.getSiguiente();
        }
    }

    // 2. Limpiar las listas de adyacencia de TODOS los demás vértices
    Vertice auxV = primero;
    while (auxV != null) {
        Arista ady = auxV.getListaAdyacencia();
        if (ady != null) {
            if (ady.getNombreproteina().equals(nombre)) {
                auxV.setListaAdyacencia(ady.getSig());
            } else {
                Arista antA = ady;
                Arista actA = ady.getSig();
                while (actA != null) {
                    if (actA.getNombreproteina().equals(nombre)) {
                        antA.setSig(actA.getSig());
                        break;
                    }
                    antA = actA;
                    actA = actA.getSig();
                }
            }
        }
        auxV = auxV.getSiguiente();
    }
}

    /**
     * Elimina una arista específica de la lista de adyacencia de un vertice dado.
     */
    private void eliminarAristaDeLista(Vertice v, String nombreDestino) {
        Arista actual = v.getListaAdyacencia();
        if (actual == null) return;

        if (actual.getNombreproteina().equalsIgnoreCase(nombreDestino)) {
            v.setListaAdyacencia(actual.getSig());
        } else {
            Arista anterior = actual;
            actual = actual.getSig();
            while (actual != null) {
                if (actual.getNombreproteina().equalsIgnoreCase(nombreDestino)) {
                    anterior.setSig(actual.getSig());
                    break;
                }
                anterior = actual;
                actual = actual.getSig();
            }
        }
    }

    /**
     * Identifica un complejo proteico utilizando BFS
     * Retorna una lista de nombres para que la interfaz la muestre
     */
    public Lista<String> obtenerComplejoProteico(String nombreInicio) {
        Lista<String> visitados = new Lista<>();
        Cola<String> cola = new Cola<>();
        
        if (buscarVertice(nombreInicio) == null) return visitados;

        cola.Encolar(nombreInicio);
        visitados.insertarFinal(nombreInicio);

        while (!cola.es_vacio()) {
            String actual = cola.Desencolar();
            Vertice vAct = buscarVertice(actual);
            
            Arista ady = vAct.getListaAdyacencia();
            while (ady != null) {
                if (!visitados.existe(ady.getNombreproteina())) {
                    visitados.insertarFinal(ady.getNombreproteina());
                    cola.Encolar(ady.getNombreproteina());
                }
                ady = ady.getSig();
            }
        }
        return visitados;
    }
    
    public int getCantidadVertices(){ 
        return cantidadVertices; 
    }
    
    public Vertice getPrimero(){ 
        return primero; 
    }
    
    /**
     * Agrega una interacción bidireccional entre dos proteínas
     * pA Nombre de la primera proteína
     * pB Nombre de la segunda proteína
     * peso Costo o resistencia de la interacción física
     */
    public void agregarInteraccion(String pA, String pB, double peso) {
        insertarVertice(pA);
        insertarVertice(pB);
        vincular(pA, pB, peso);
        vincular(pB, pA, peso);
    }
    /**
 * Recorre el grafo completo y genera una cadena de texto con el formato
 * de origen, destino y peso, lista para ser guardada en el archivo TXT.
 * * @return String con todas las interacciones del grafo.
 */
public String obtenerContenidoGrafoParaArchivo() {
    StringBuilder sb = new StringBuilder();
    Vertice vAux = primero;

    while (vAux != null) {
        Arista aAux = vAux.getListaAdyacencia();
        
        while (aAux != null) {
            if (vAux.getNombre().compareToIgnoreCase(aAux.getNombreproteina()) < 0) {
                sb.append(vAux.getNombre()).append(",")
                  .append(aAux.getNombreproteina()).append(",")
                  .append((int)aAux.getPeso()).append("\n");
            }
            aAux = aAux.getSig();
        }
        vAux = vAux.getSiguiente();
    }
    return sb.toString();
    }
/**
 * Crea una ventana externa interactiva para visualizar la red de proteinas.
 * Identifica los complejos proteicos mediante el layout automatico.
 */
public void mostrarGrafoAnimado() {
    /**Configuramos la libreria para que use la interfaz Swing de Java*/
    System.setProperty("org.graphstream.ui", "swing");
    Graph grafoviz = new SingleGraph("Red de Interacciones Proteicas");

    String estilo = "node { fill-color: #00FFFF; size: 25px; text-size: 14; text-alignment: at-right; } "
                  + "edge { fill-color: grey; size: 2px; }";
    grafoviz.setAttribute("ui.stylesheet", estilo);

    /** Agregar todos los vértices (proteínas)*/
    Vertice vAux = primero; 
    while (vAux != null) {
        Node n = grafoviz.addNode(vAux.getNombre());
        n.setAttribute("ui.label", vAux.getNombre());
        vAux = vAux.getSiguiente();
    }

    /** Agregamos todas las aristas (interacciones)*/
    vAux = primero;
    while (vAux != null) {
        Arista aAux = vAux.getListaAdyacencia();
        while (aAux != null) {
            try {
                String idArista = vAux.getNombre() + "-" + aAux.getNombreproteina();
                Edge e = grafoviz.addEdge(idArista, vAux.getNombre(), aAux.getNombreproteina());
                e.setAttribute("ui.label", (int)aAux.getPeso()); // Mostramos el peso
            } catch (EdgeRejectedException | IdAlreadyInUseException e) {
            }
            aAux = aAux.getSig();
        }
        vAux = vAux.getSiguiente();
    }

    /** Abrir la ventana con autolayout y habilitar interacción*/
    Viewer viewer = grafoviz.display(true);
    viewer.getDefaultView().enableMouseOptions();
   
    }
public String encontrarComplejos() {
    Vertice v = primero;
    while (v != null) {
        v.setVisitado(false);
        v = v.getSiguiente();
    }

    String reporte = "COMPLEJOS DETECTADOS:\n";
    int numComplejo = 1;
    v = primero;

    while (v != null) {
        if (!v.isVisitado()) {
            reporte += "Complejo #" + numComplejo + ": ";
            reporte = recorrerYAgrupar(v, reporte);
            reporte += "\n";
            numComplejo++;
        }
        v = v.getSiguiente();
    }
    return reporte;
}

private String recorrerYAgrupar(Vertice v, String res) {
    v.setVisitado(true);
    res += "[" + v.getNombre() + "] ";
    
    Arista a = v.getListaAdyacencia();
    while (a != null) {
        Vertice vecino = buscarVertice(a.getNombreproteina());
        if (vecino != null && !vecino.isVisitado()) {
            res = recorrerYAgrupar(vecino, res);
        }
        a = a.getSig();
    }
    return res;
}
}
