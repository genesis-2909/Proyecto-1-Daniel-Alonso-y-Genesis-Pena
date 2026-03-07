/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author PC
 */
public class Nodo<T> {
    private T dato;
    private Nodo<T> pnext;

    /** Constructor */
    
    public Nodo (T dato) {
        this.dato = dato;
        this.pnext = null;
    }

    /** Getters y setters */  
    
    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Nodo getPnext() {
        return pnext;
    }

    public void setPnext(Nodo pnext) {
        this.pnext = pnext;
    }
       
    
}
