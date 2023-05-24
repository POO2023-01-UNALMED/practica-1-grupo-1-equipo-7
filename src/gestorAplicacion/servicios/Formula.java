/* Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
y Santiago Arboleda Acevedo */
package gestorAplicacion.servicios;

import gestorAplicacion.administracion.Medicamento;
import gestorAplicacion.personas.Doctor;
import gestorAplicacion.personas.Paciente;

import java.text.Normalizer;
import java.util.ArrayList;

public class Formula extends Servicio {
    private ArrayList<Medicamento> listaMedicamentos = new ArrayList<Medicamento>();
    private Doctor doctor;

    public Formula(ArrayList<Medicamento> listaMedicamentos, Doctor doctor, Paciente paciente) {
        super(paciente);
        this.listaMedicamentos = listaMedicamentos;
        this.doctor = doctor;
    }
    public Formula(Paciente paciente){
        this(null, null, paciente);
    }

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

    @Override
    public void validarPago(Paciente paciente, long idServicio) {
        for (Formula formula :
                paciente.getHistoriaClinica().getListaFormulas()) {
            if (formula.getIdServicio() == idServicio)
                formula.setEstadoPago(true);
        }
    }

    @Override
    public String toString() {
        return "Hola "+paciente.getNombre()+"\nEstos son tus medicamentos formulados:\n"+listaMedicamentos;
    }
}
