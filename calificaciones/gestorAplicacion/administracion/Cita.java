package gestorAplicacion.administracion;

import gestorAplicacion.personas.Doctor;
import gestorAplicacion.personas.Paciente;

import javax.print.Doc;

public class Cita {

    private String fecha;
    private Paciente paciente;
    private Doctor doctor;

    public Cita (String fecha, Paciente paciente, Doctor doctor){
        this.fecha = fecha;
        this.paciente = paciente;
        this.doctor = doctor;
    }

    public Doctor getDoctor() {
        return doctor;
    }
}
