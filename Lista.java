/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 * Clase lista simplemente enlazada para poder 
 * manejar datos, proteinas, rutas, etc.
 */
public class Lista<T> {
   
    private Nodo<T> pfirst;
    private int size;

    public Lista() {
        this.pfirst = null;
        this.size = 0;
    }

    /** Si la lista esta vacia retorna null */
    public boolean es_vacio() { 
        return this.pfirst == null; 
    }
    
    /** Retorna el tamaño de la lista */
    public int getTamaño(){ 
        return this.size; 
    }
    
    /**
     * Se grega un elemento al final de la lista
     */
    public void insertarFinal(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (this.pfirst == null) {
            this.pfirst = nuevo;
        } else {
            Nodo<T> aux = this.pfirst;
            while (aux.getPnext() != null) {
                aux = aux.getPnext();
            }
            aux.setPnext(nuevo);
        }
        this.size++;
    }

    /**
     * Busca un elemento en la lista para ver si existe.
     */
    public boolean existe(T dato) {
        Nodo<T> aux = pfirst;
        while (aux != null) {
            if (aux.getDato().equals(dato)) {
                return true;
            }
        aux = aux.getPnext();
        }
    return false;
    }

    /**
     * Elimina un elemento específico de la lista
     */
    public void eliminar(T dato) {
        if (this.pfirst == null) {
            return;
        }
        if (this.pfirst.getDato().equals(dato)) {
            this.pfirst = this.pfirst.getPnext();
            this.size--;
            return;
        }
        Nodo<T> aux = this.pfirst;
        while (aux.getPnext() != null) {
            if (aux.getPnext().getDato().equals(dato)) {
                aux.setPnext(aux.getPnext().getPnext());
                this.size--;
                return;
                }
        aux = aux.getPnext();
        }
    }

    /**
     * Obtiene el dato en una posición específica que se pida
     */
    public T obtener(int indice) {
        if (indice < 0 || indice >= this.size){
            return null;
        }
        Nodo<T> aux = this.pfirst;
        for (int i = 0; i < indice; i++) {
            aux = aux.getPnext();
            }
        return aux.getDato();
        }
    
}
    

