/* Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
y Santiago Arboleda Acevedo */
package gestorAplicacion.servicios;

import gestorAplicacion.personas.Doctor;
import gestorAplicacion.personas.Paciente;

//Clase destinada a crear citas médicas
public class Cita extends Servicio {
    //Atributos
    private Doctor doctor;
    protected String fecha;

    //Constructor
    public Cita(Doctor doctor, String fecha, Paciente paciente){
        super(paciente);
        this.doctor = doctor;
        this.fecha = fecha;
    }

    //Métodos

    //Métodos que busca el estado de pago de una cita médica y lo cambia
    @Override
    public void validarPago(Paciente paciente, long idServicio) {
        for (Cita cita :
                paciente.getHistoriaClinica().getHistorialCitas()) {
            if (cita.getIdServicio() == idServicio)
                cita.setEstadoPago(true);
        }
    }

    // Metodo que imprime una descripcion del servicio
    @Override
    public String descripcionServicio() {
        return idServicio + " - Cita medica con " + doctor.getNombre() + " (" + fecha + ")";
    }


    //Getters y Setters

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

}
