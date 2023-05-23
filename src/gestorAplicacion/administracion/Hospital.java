package gestorAplicacion.administracion;

import baseDatos.Deserializador;
import gestorAplicacion.personas.Doctor;
import gestorAplicacion.personas.Paciente;
import gestorAplicacion.servicios.Habitacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Hospital implements Serializable {

    private ArrayList<Doctor> listaDoctores = new ArrayList<>();
    private ArrayList<Paciente> listaPacientes = new ArrayList<>();
    private ArrayList<Medicamento> listaMedicamentos = new ArrayList<>();
    private ArrayList<Vacuna> listaVacunas= new ArrayList<Vacuna>();
    public static ArrayList<Habitacion> habitaciones= new ArrayList<>();

    public Hospital() {
        Deserializador.deserializar(this);
    }

    public ArrayList<Doctor> buscarTipoDoctor(String especialidad) {
        ArrayList<Doctor> doctoresDisponibles = new ArrayList<Doctor>();
        for(int i=1; i<=listaDoctores.size(); i++) {
            if (Objects.equals(listaDoctores.get(i-1).getEspecialidad(), especialidad)) {
                doctoresDisponibles.add(listaDoctores.get(i-1));
            }
        }
        return doctoresDisponibles;
    }
    public Paciente buscarPaciente(int cedulaPaciente) {
        for (Paciente paciente :
                listaPacientes) {
            if (paciente.getCedula() == cedulaPaciente) {
                return paciente;
            }
        }
        return null;
    }

    public Doctor buscarDoctor(int ceduladoctor){
        for (Doctor doctor :
                listaDoctores) {
            if (doctor.getCedula() == ceduladoctor) {
                return doctor;
            }
        }
        return null;
    }

    public Vacuna buscarVacuna(String nombre){
        for (Vacuna vacuna: listaVacunas){
            if (Objects.equals(vacuna.getNombre(), nombre)){
                return vacuna;
            }
        }
        return null;
    }

    public ArrayList<Medicamento> medicamentosDisponibles() {
        ArrayList<Medicamento> disponibles = new ArrayList<Medicamento>();
        for(int i=0; i<listaMedicamentos.size();i++) {
            if (listaMedicamentos.get(i).getCantidad()>0) {
                disponibles.add(listaMedicamentos.get(i));
            }
        }
        return disponibles;
    }

    public ArrayList<Vacuna> buscarTipoVacuna(String tipo){
        ArrayList<Vacuna> vacunasDisponibles = new ArrayList<Vacuna>();
        for (int i=1; i<=listaVacunas.size(); i++){
            if (Objects.equals(listaVacunas.get(i - 1).getTipo(), tipo)){
                vacunasDisponibles.add(listaVacunas.get(i-1));
            }
        }
        return vacunasDisponibles;
    }

    public ArrayList<Doctor> getListaDoctores() {
        return listaDoctores;
    }

    public void setListaDoctores(ArrayList<Doctor> listaDoctores) {
        this.listaDoctores = listaDoctores;
    }

    public ArrayList<Paciente> getListaPacientes() {
        return listaPacientes;
    }

    public void setListaPacientes(ArrayList<Paciente> listaPacientes) {
        this.listaPacientes = listaPacientes;
    }

    public ArrayList<Medicamento> getListaMedicamentos() {
        return listaMedicamentos;
    }

    public void setListaMedicamentos(ArrayList<Medicamento> listaMedicamentos) {
        this.listaMedicamentos = listaMedicamentos;
    }

    public ArrayList<Vacuna> getListaVacunas() {
        return listaVacunas;
    }

    public void setListaVacunas(ArrayList<Vacuna> listaVacunas) {
        this.listaVacunas = listaVacunas;
    }

    public static ArrayList<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public static void setHabitaciones(ArrayList<Habitacion> habitaciones) {
        Hospital.habitaciones = habitaciones;
    }
}
