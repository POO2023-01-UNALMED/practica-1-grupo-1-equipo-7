package FuncionalidadVacunas;

public class Cita {
    private String fecha;
    private Paciente paciente;
    private Doctor doctor;

    public Cita(String fecha, Paciente paciente, Doctor doctor) {
        this.fecha = fecha;
        this.paciente = paciente;
        this.doctor = doctor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
