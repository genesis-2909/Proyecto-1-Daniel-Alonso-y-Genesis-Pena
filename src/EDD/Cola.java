/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author PC
 */
public class Cola<T> {
    private Nodo<T> frente;
    private Nodo<T> cola;
    private int size;

    /** Crea una cola vacia */
    public Cola() {
        this.frente = null;
        this.cola = null;
        this.size = 0;
    }
    
    public boolean es_vacio(){
        return this.frente == null;
    }
    
     /** Inserta un elemento al final de la cola */
    public void Encolar(T dato){
        Nodo nuevo = new Nodo<>(dato);
        if (this.es_vacio()){
            this.frente = nuevo;
            this.cola = nuevo;       
        }else{
            this.cola.setPnext(nuevo);
            this.cola = nuevo;
        }
    this.size++;
    }
    
    /** Extrae y retorna un elemento al frente de la cola
     o null en caso de que cola este vacia*/
    public T Desencolar(){
        if (this.es_vacio()){
            return null;
        }else{
            T dato = this.frente.getDato();
            frente = this.frente.getPnext();
            if (frente == null){
            this.cola = null;
            }
        this.size--;
    return dato;    
        }
    }
    
}
