package gestorAplicacion.administracion;

import java.util.ArrayList;

import gestorAplicacion.personas.Doctor;
import gestorAplicacion.personas.Paciente;
import gestorAplicacion.administracion.Medicamento;



public class Hospital {

	static private ArrayList<Paciente> listaPacientes = new ArrayList<Paciente>();;
    static private ArrayList<Doctor> listaDoctores = new ArrayList<Doctor>();
    static private ArrayList<Medicamento> listaMedicamentos = new ArrayList<Medicamento>();    
    
    static {
		ArrayList<String> enfe = new ArrayList<>();
		enfe.add("Taquicardia");
		enfe.add("Hipertension");
		listaPacientes.add(new Paciente(111, "Daniel", new HistoriaClinica(), "Sisben", enfe));

		listaMedicamentos.add(new Medicamento("Corazon","Taquicardia","afaef",4,1000));
		listaMedicamentos.add(new Medicamento("Riñon","TAsfaef","afaef",4,1000));
		listaMedicamentos.add(new Medicamento("Azucar","General","afaef",4,1000));
		listaMedicamentos.add(new Medicamento("Agua","Hipertension","afaef",4,2000));

    	listaDoctores.add(new Doctor(123,"Diego","General",new ArrayList<String>()));
    	listaDoctores.add(new Doctor(123,"Santiago","Oftalmologia",new ArrayList<String>()));
    	listaDoctores.add(new Doctor(123,"Camilo","Odontologia",new ArrayList<String>()));
    	listaDoctores.add(new Doctor(123,"Elian","General",new ArrayList<String>()));
    	listaDoctores.add(new Doctor(123,"Daniel","Oftalmologia",new ArrayList<String>()));
    	listaDoctores.add(new Doctor(123,"Pacho","General",new ArrayList<String>()));
    	

    }
    
    //public Hospital() {
   // 	Deserializador.deserializar(this);
    //}
    
    static public Paciente buscarPaciente(int numeroCedula) {
    	Paciente pacienteAsignado = null;
    	for(int i=0; i<listaPacientes.size(); i++) {
    		if (listaPacientes.get(i).getCedula() == numeroCedula) {
    			pacienteAsignado = listaPacientes.get(i);
    		}
    	}
    	return pacienteAsignado;
    }
    static public ArrayList<Doctor> buscarTipoDoctor(String especialidad) {
    	ArrayList<Doctor> doctoresDisponibles = new ArrayList<Doctor>();
    	for(int i=1; i<=listaDoctores.size(); i++) {
    		if (listaDoctores.get(i-1).getEspecialidad() == especialidad) {
    			doctoresDisponibles.add(listaDoctores.get(i-1));
    		}
    	}
    	return doctoresDisponibles;
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
