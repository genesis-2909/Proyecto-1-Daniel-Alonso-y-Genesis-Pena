/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author PC
 */
public class Pila<T> {
    private Nodo<T> tope;
    private int size;

    /** Crea una pila vacía */
    public Pila() {
        this.tope = null;
        this.size = 0;
    }
    
    public boolean es_vacio(){
        return this.tope == null;
    }
    
    /** Agrega un elemento en el tope de la pila */
    public void Apilar(T dato){
        Nodo nuevo = new Nodo(dato);
        nuevo.setPnext(this.tope);
        this.tope = nuevo;
        this.size++;
    }
    
    /** Extrae y retorna el elemento en el tope o null en caso 
     * de que pila este vacia*/
    public T Desapilar(){
        if (this.es_vacio()){
            return null;
        }else{
            T dato = this.tope.getDato();
            this.tope = this.tope.getPnext();
            this.size--;
            return dato;
        }
    }
    
    /** Permite leer el valor del dato que esta en el tope sin sacarlo
     o null en caso de que pila este vacia */
    public T vertope(){
        if (this.es_vacio()){
            return null;
        }
    return this.tope.getDato();
    }
    
}
