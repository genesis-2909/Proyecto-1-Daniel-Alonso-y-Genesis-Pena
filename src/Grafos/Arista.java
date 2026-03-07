/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafos;

/**
 *
 * La clase arista sirve para representar la conexión entre proteínas
 */
public class Arista {
    
    private String nombreproteina; /** Nombre de la proteína con la que conecta*/
    private double peso;          /** Costo o resistencia de la interacción */
    private Arista sig;     /** Puntero para la lista de vecinos */

    public Arista(String nombreproteina, double peso) {
        this.nombreproteina = nombreproteina;
        this.peso = peso;
        this.sig = null;
    }

    /** Getters y setters */
    
    public String getNombreproteina() {
        return nombreproteina;
    }

    public void setNombreproteina(String nombreproteina) {
        this.nombreproteina = nombreproteina;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Arista getSig() {
        return sig;
    }

    public void setSig(Arista sig) {
        this.sig = sig;
    }

  
}