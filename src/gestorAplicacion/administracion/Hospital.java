package gestorAplicacion.administracion;

import baseDatos.Deserializador;
import gestorAplicacion.personas.Doctor;
import gestorAplicacion.personas.Paciente;

import java.io.Serializable;
import java.util.ArrayList;

public class Hospital implements Serializable {

    private ArrayList<Doctor> listaDoctores = new ArrayList<>();
    private ArrayList<Paciente> listaPacientes = new ArrayList<>();

    public Hospital() {
        Deserializador.deserializar(this);
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
}
