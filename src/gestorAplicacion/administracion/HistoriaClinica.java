/* Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
y Santiago Arboleda Acevedo */
package gestorAplicacion.administracion;

import gestorAplicacion.personas.Doctor;
import gestorAplicacion.personas.Enfermedad;
import gestorAplicacion.personas.Paciente;
import gestorAplicacion.servicios.Cita;
import gestorAplicacion.servicios.CitaVacuna;
import gestorAplicacion.servicios.Formula;

import java.io.Serializable;
import java.util.ArrayList;

//Clase destinada a crear historias clínicas a pacientes
public class HistoriaClinica implements Serializable {
    //Atributos
    private final Paciente paciente;
    private ArrayList<Formula> listaFormulas = new ArrayList<>();
    private ArrayList<Cita> historialCitas = new ArrayList<>();
    private ArrayList<Enfermedad> enfermedades = new ArrayList<>();
    private ArrayList<CitaVacuna> historialVacunas= new ArrayList<>();

    //Constructor
    public HistoriaClinica(Paciente paciente) {
        this.paciente = paciente;
    }

    //Métodos

    /* Método que busca los doctores que estén disponibles y también los doctores con los que el paciente
    asociado a esta historia haya tenido alguna cita */
    public ArrayList<Doctor> buscarCitaDoc(String especialidad, Hospital hospital) {
        ArrayList<Doctor> doctoresDisp = hospital.buscarTipoDoctor(especialidad);
        ArrayList<Doctor> docCita = new ArrayList<>();
        for (Doctor doc : doctoresDisp){
            for (Cita cita : historialCitas){
                if (doc.getCedula() == cita.getDoctor().getCedula()){
                    docCita.add(doc);
                }
            }
        }
        return docCita;
    }

    // Método que agrega las fórmulas médicas ingresadas como parámetro a listaFormulas
    public void agregarFormula(Formula formulaPaciente) {
        listaFormulas.add(formulaPaciente);
    }


    //Getter y setter
    public Paciente getPaciente() {
        return paciente;
    }

    public ArrayList<Formula> getListaFormulas() {
        return listaFormulas;
    }

    public void setListaFormulas(ArrayList<Formula> listaFormulas) {
        this.listaFormulas = listaFormulas;
    }

    public ArrayList<Cita> getHistorialCitas() {
        return historialCitas;
    }

    public void setHistorialCitas(ArrayList<Cita> historialCitas) {
        this.historialCitas = historialCitas;
    }

    public ArrayList<Enfermedad> getEnfermedades() {
        return enfermedades;
    }

    public void setEnfermedades(ArrayList<Enfermedad> enfermedades) {
        this.enfermedades = enfermedades;
    }

    public ArrayList<CitaVacuna> getHistorialVacunas() {
        return historialVacunas;
    }

    public void setHistorialVacunas(ArrayList<CitaVacuna> historialVacunas) {
        this.historialVacunas = historialVacunas;
    }
}
