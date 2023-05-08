package gestorAplicacion.servicios;

import gestorAplicacion.personas.Doctor;
import gestorAplicacion.personas.Paciente;

public class Cita extends Servicio {
    private Doctor doctor;
    private String fecha;

    public Cita(Doctor doctor, String fecha, Paciente paciente){
        super(paciente);
        this.doctor = doctor;
        this.fecha = fecha;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
