/* Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
y Santiago Arboleda Acevedo */

package gestorAplicacion.personas;

import java.io.Serializable;
import java.util.ArrayList;

/* Clase que crea enfermedades, las cuales son usadas para recomendarle al paciente
distintos servicios que estén relacionados con su enfermedad */
public class Enfermedad implements Serializable {
    // Atributos
    private String especialidad;
    private String nombre;
    private String tipologia;
    private int enfermos = 1;
    // Lista de las enfermedades registradas en el hospital
    private static ArrayList<Enfermedad> enfermedadesRegistradas = new ArrayList<>();

    // Constructor
    public Enfermedad(String especialidad, String nombre, String tipologia) {
        this.especialidad = especialidad;
        this.nombre = nombre;
        this.tipologia = tipologia;
        enfermedadesRegistradas.add(this);
    }

    // Métodos

    // Método que aumenta los enfermos en una unidad
    public void nuevoEnfermo(){
        enfermos+=1;
    }

    // Getters y Setters
    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public static ArrayList<Enfermedad> getEnfermedadesRegistradas() {
        return enfermedadesRegistradas;
    }

    public static void setEnfermedadesRegistradas(ArrayList<Enfermedad> enfermedadesRegistradas) {
        Enfermedad.enfermedadesRegistradas = enfermedadesRegistradas;
    }

    public int getEnfermos() {
        return enfermos;
    }

    public void setEnfermos(int enfermos) {
        this.enfermos = enfermos;
    }

    // toString
    @Override
    public String toString(){
        return nombre + " " + tipologia + " Especialidad que la trata: " + especialidad;
    }
}
