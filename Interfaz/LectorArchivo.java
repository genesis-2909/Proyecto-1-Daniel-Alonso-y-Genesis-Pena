package Interfaz;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import Grafos.Grafo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileWriter;
import java.io.PrintWriter;
import Grafos.Vertice;
import Grafos.Arista;
import EDD.Lista;
/**
 * Clase encargada de la lectura del archivo y carga de datos
 * Utiliza JFileChooser para permitir al usuario seleccionar el repositorio.
 */

public class LectorArchivo {
    /**
     * Abre el selector de archivos y procesa el txt seleccionado
     */
    private String contenidoTexto = "";
    
    public Grafo cargarRepositorio() {
        JFileChooser selector = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de Red Proteica (.txt)", "txt");
        selector.setFileFilter(filtro);
        selector.setDialogTitle("Seleccionar archivo de interacciones");

        int seleccion = selector.showOpenDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivo = selector.getSelectedFile();
            return LeerLineas(archivo);
            } 
        return null;
        }

    /**
     * Lee el archivo línea por línea
     */
    private Grafo LeerLineas(File archivo) {
        Grafo grafoNuevo = new Grafo();
        this.contenidoTexto = "";
       
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                
                this.contenidoTexto += linea + "\n";
                /**el formato del txt: ProteinaA, ProteinaB, Peso
                 * Ejemplo: P1, P4, 10
                 */
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    String p1 = partes[0].trim();
                    String p2 = partes[1].trim();
                    
                    try {
                        double peso = Double.parseDouble(partes[2].trim());
                        grafoNuevo.agregarInteraccion(p1, p2, peso);
                    } catch (NumberFormatException e) {
                    }
                }
            }
        } catch (IOException e) {
            return null;
        }
    return grafoNuevo;
    }
    
    public String getContenidoTexto() {
        return contenidoTexto;
    }
    
    public void actualizarRepositorio(Grafo grafo, String path) {
    try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
        Vertice vAct = grafo.getPrimero();
        Lista<String> escritas = new Lista<>(); /** Para no duplicar A,B y B,A */

        while (vAct != null) {
            Arista ady = vAct.getListaAdyacencia();
            while (ady != null) {
                /** Como el grafo es no dirigido, solo se escribe si no hemos escrito la pareja inversa */
                String relacion = vAct.getNombre() + "-" + ady.getNombreproteina();
                String relacionInversa = ady.getNombreproteina() + "-" + vAct.getNombre();
                
                if (!escritas.existe(relacion) && !escritas.existe(relacionInversa)) {
                    pw.println(vAct.getNombre() + ", " + ady.getNombreproteina() + ", " + ady.getPeso());
                    escritas.insertarFinal(relacion);
                }
                ady = ady.getSig();
            }
            vAct = vAct.getSiguiente();
        }
    } catch (IOException e) {
        System.out.println("Error al guardar el repositorio: " + e.getMessage());
    }
}
}
    

