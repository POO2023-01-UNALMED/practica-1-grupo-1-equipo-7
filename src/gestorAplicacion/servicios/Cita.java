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

    public String mensaje(){
        return "del servicio cita médica!";
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

    @Override
    public void validarPago(Paciente paciente, long idServicio) {
        for (Cita cita :
                paciente.getHistoriaClinica().getHistorialCitas()) {
            if (cita.getIdServicio() == idServicio)
                cita.setEstadoPago(true);
        }
    }
}
