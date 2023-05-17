package gestorAplicacion.administracion;

import baseDatos.Deserializador;
import gestorAplicacion.personas.Doctor;
import gestorAplicacion.personas.Paciente;

import java.io.Serializable;
import java.util.ArrayList;

public class Hospital implements Serializable {

    private ArrayList<Doctor> listaDoctores = new ArrayList<>();
    private ArrayList<Paciente> listaPacientes = new ArrayList<>();
    private ArrayList<Medicamento> listaMedicamentos = new ArrayList<>();
    public static ArrayList<Habitacion> habitaciones= new ArrayList<>();


    public Hospital() {
        Deserializador.deserializar(this);
    }

    public ArrayList<Doctor> buscarTipoDoctor(String especialidad, Hospital hospital) {
        ArrayList<Doctor> doctoresDisponibles = new ArrayList<Doctor>();
        for(int i=1; i<=listaDoctores.size(); i++) {
            if (listaDoctores.get(i-1).getEspecialidad() == especialidad) {
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
    public ArrayList<Medicamento> medicamentosDisponibles() {
        ArrayList<Medicamento> disponibles = new ArrayList<Medicamento>();
        for(int i=0; i<listaMedicamentos.size();i++) {
            if (listaMedicamentos.get(i).getCantidad()>0) {
                disponibles.add(listaMedicamentos.get(i));
            }
        }
        return disponibles;
    }

    public void registrarPaciente(Paciente paciente) {
        listaPacientes.add(paciente);
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
}
