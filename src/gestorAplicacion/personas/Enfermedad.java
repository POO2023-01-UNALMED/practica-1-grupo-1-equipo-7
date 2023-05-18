package gestorAplicacion.personas;

import java.util.ArrayList;

public class Enfermedad {
    private String especialidad;
    private String nombre;
    private String tipologia;
    private int enfermos = 1;
    private static ArrayList<Enfermedad> enfermedadesRegistradas = new ArrayList<>();

    public Enfermedad(String especialidad, String nombre, String tipologia) {
        this.especialidad = especialidad;
        this.nombre = nombre;
        this.tipologia = tipologia;
        enfermedadesRegistradas.add(this);
    }

    public static Enfermedad registroEnfermedad(String nombre, String tipologia){
        Enfermedad enfermedad = null;
        for(Enfermedad enf : enfermedadesRegistradas) {
            if (enf.getNombre() == nombre && enf.getTipologia() == tipologia) {
                enfermedad = enf;
            }
        }
        return enfermedad;
    }
    public void nuevoEnfermo(){
        enfermos+=1;
    }
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

    @Override
    public String toString(){
        return nombre + " " + tipologia + " Especialidad que la trata: " + especialidad;
    }
}
