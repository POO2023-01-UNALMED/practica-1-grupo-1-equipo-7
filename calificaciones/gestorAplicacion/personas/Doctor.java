package gestorAplicacion.personas;

import java.util.ArrayList;

public class Doctor extends Persona{

    private String especialidad;

    private ArrayList<Float> calificaciones = new ArrayList<>();

    public Doctor(long cedula, String nombre, String especialidad) {
        super(cedula, nombre);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public float getCalificacion() {
        float sumaCalificaciones = 0;
        int cantidadCalificaciones = calificaciones.size();

        for (float calificacion: calificaciones)
            sumaCalificaciones += calificacion;

        return sumaCalificaciones/cantidadCalificaciones;
    }

    public void agregarCalificacion(float calificacion) {
        this.calificaciones.add(calificacion);
    }
}
