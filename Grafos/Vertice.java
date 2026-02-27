/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafos;

/**git
 * Representa una proteína en la estructura del grafo
 */
public class Vertice {
    private String nombre; /** Nombre de la proteina */
    private Arista listaAdyacencia; /** Esta es la lista de adyacencia de esta proteína */
    private Vertice siguiente;      /** Puntero para la lista global de proteínas */

    public Vertice(String nombre) {
        this.nombre = nombre;
        this.listaAdyacencia = null;
        this.siguiente = null;
    }

    /**
     * Getters y setters
     */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Arista getListaAdyacencia() {
        return listaAdyacencia;
    }

    public void setListaAdyacencia(Arista listaAdyacencia) {
        this.listaAdyacencia = listaAdyacencia;
    }

    public Vertice getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Vertice siguiente) {
        this.siguiente = siguiente;
    }
    
   
    
}

