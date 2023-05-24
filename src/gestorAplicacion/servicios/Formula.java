/* Clase que crea las formulas que se han generado para un paciente.
Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
y Santiago Arboleda Acevedo */
package gestorAplicacion.servicios;

import gestorAplicacion.administracion.Medicamento;
import gestorAplicacion.personas.Doctor;
import gestorAplicacion.personas.Paciente;

import java.text.Normalizer;
import java.util.ArrayList;

public class Formula extends Servicio {
    // Atributos
    private ArrayList<Medicamento> listaMedicamentos = new ArrayList<Medicamento>();
    private Doctor doctor;

    // Constructor
    public Formula(ArrayList<Medicamento> listaMedicamentos, Doctor doctor, Paciente paciente) {
        super(paciente);
        this.listaMedicamentos = listaMedicamentos;
        this.doctor = doctor;
    }
    // Sobrecarga de constructor
    public Formula(Paciente paciente){
        this(null, null, paciente);
    }

    // Getters y setters
    public ArrayList<Medicamento> getListaMedicamentos() {
        return listaMedicamentos;
    }

    public void setListaMedicamentos(ArrayList<Medicamento> listaMedicamentos) {
        this.listaMedicamentos = listaMedicamentos;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    // Metodos

    // Método sobreescrito
    @Override
    public void validarPago(Paciente paciente, long idServicio) {
        for (Formula formula :
                paciente.getHistoriaClinica().getListaFormulas()) {
            if (formula.getIdServicio() == idServicio)
                formula.setEstadoPago(true);
        }
    }

    // Metodo que imprime una descripcion del servicio
    @Override
    public String descripcionServicio() {
        return idServicio + " - Formula prescrita por " + doctor.getNombre();
    }

    // toString
    @Override
    public String toString() {
        return "Hola "+paciente.getNombre()+"\nEstos son tus medicamentos formulados:\n"+listaMedicamentos;
    }
}
