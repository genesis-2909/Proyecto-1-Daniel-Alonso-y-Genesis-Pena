/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author PC
 */
public class Pila {
    private Nodo tope;
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
    public void Apilar(int dato){
        Nodo nuevo = new Nodo(dato);
        nuevo.setPnext(this.tope);
        this.tope = nuevo;
        this.size++;
    }
    
    /** Extrae y retorna el elemento en el tope o -1 en caso 
     * de que pila este vacia*/
    public int Desapilar(){
        if (this.es_vacio()){
            return -1;
        }else{
            int dato = this.tope.getDato();
            this.tope = this.tope.getPnext();
            this.size--;
            return dato;
        }
    }
    
    /** Permite leer el valor del dato que esta en el tope sin sacarlo
     o -1 en caso de que pila este vacia */
    public int vertope(){
        if (this.es_vacio()){
            return -1;
        }
    return this.tope.getDato();
    }
    
}
