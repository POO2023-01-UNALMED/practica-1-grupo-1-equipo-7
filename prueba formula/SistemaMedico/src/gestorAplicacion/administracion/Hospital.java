package gestorAplicacion.administracion;

import java.util.ArrayList;

import gestorAplicacion.personas.Doctor;
import gestorAplicacion.personas.Paciente;
import gestorAplicacion.administracion.Medicamento;


public class Hospital {
	static private ArrayList<Paciente> listaPacientes = new ArrayList<Paciente>();;
    static private ArrayList<Doctor> listaDoctores = new ArrayList<Doctor>();
    static ArrayList<Doctor> doctoresDisponibles = new ArrayList<Doctor>();
    static private ArrayList<Medicamento> listaMedicamentos = new ArrayList<Medicamento>();    
    
    static {
    	    	
    	listaPacientes.add(new Paciente(111, "Juan", new HistoriaClinica()));
    	listaPacientes.add(new Paciente(222, "Jose", new HistoriaClinica()));
    	listaPacientes.add(new Paciente(333, "Felipe", new HistoriaClinica()));
    	listaPacientes.add(new Paciente(444, "Maria", new HistoriaClinica()));
    	listaPacientes.add(new Paciente(555, "Pedro", new HistoriaClinica()));
    	
    	listaMedicamentos.add(new Medicamento("Omeprazol","General","Sirve pa algo",5));
    	listaMedicamentos.add(new Medicamento("Ibuprofeno","General","Sirve pa algo",5));
    	listaMedicamentos.add(new Medicamento("Acetaminofen","General","Sirve pa algo",5));
    	listaMedicamentos.add(new Medicamento("Xanax","General","Sirve pa algo",5));
    }
    
    static public Paciente buscarPaciente(int numeroCedula) {
    	Paciente pacienteAsignado = null;
    	for(int i=0; i<listaPacientes.size(); i++) {
    		if (listaPacientes.get(i).getCedula() == numeroCedula) {
    			pacienteAsignado = listaPacientes.get(i);
    		}
    	}
    	return pacienteAsignado;
    }
	static public void buscarTipoDoctor(String especialidad) {
    	for(int i=0; i<listaDoctores.size(); i++) {
    		if (listaDoctores.get(i).getEspecialidad() == especialidad) {
    			doctoresDisponibles.add(listaDoctores.get(i));
    		}
    }
    	for(int i=1; i<=doctoresDisponibles.size(); i++) {
    		System.out.println(i + ". " + doctoresDisponibles.get(i-1).getNombre());
    	}
    }
	static public ArrayList<Medicamento> medicamentosDisponibles(){
		ArrayList<Medicamento> disponibles = new ArrayList<Medicamento>();
		for(int i=0; i<listaMedicamentos.size();i++) {
			if (listaMedicamentos.get(i).getCantidad()>0) {
				disponibles.add(listaMedicamentos.get(i));
			}
		}
		return disponibles;
	}
	
	
    
	public static ArrayList<Paciente> getListaPacientes() {
		return listaPacientes;
	}
	public static void setListaPacientes(ArrayList<Paciente> listaPacientes) {
		Hospital.listaPacientes = listaPacientes;
	}
	public static ArrayList<Doctor> getListaDoctores() {
		return listaDoctores;
	}
	public static void setListaDoctores(ArrayList<Doctor> listaDoctores) {
		Hospital.listaDoctores = listaDoctores;
	}
	public static ArrayList<Medicamento> getListaMedicamentos() {
		return listaMedicamentos;
	}
	public static void setListaMedicamentos(ArrayList<Medicamento> listaMedicamentos) {
		Hospital.listaMedicamentos = listaMedicamentos;
	}

}
