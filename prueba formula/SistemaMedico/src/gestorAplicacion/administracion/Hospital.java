package gestorAplicacion.administracion;

import java.lang.reflect.Array;
import java.util.ArrayList;

import gestorAplicacion.personas.Doctor;
import gestorAplicacion.personas.Enfermedad;
import gestorAplicacion.personas.Paciente;
import gestorAplicacion.administracion.Medicamento;



public class Hospital {

	static private ArrayList<Paciente> listaPacientes = new ArrayList<Paciente>();;
    static private ArrayList<Doctor> listaDoctores = new ArrayList<Doctor>();
    static private ArrayList<Medicamento> listaMedicamentos = new ArrayList<Medicamento>();    
    
    static {
		ArrayList<Enfermedad> enf = new ArrayList<>();
		enf.add(new Enfermedad("Oftalmologia", "Miopia", "I"));
		enf.add(new Enfermedad("Oftalmologia", "Cataratas", "II"));
		enf.add(new Enfermedad("Odontologia", "Caries", "I"));
		enf.add(new Enfermedad("Odontologia", "Muelas del juicio", "II"));
		Paciente pac = new Paciente(111, "Pepe", "Contributiva");
		pac.getHistoriaClinica().setEnfermedades(enf);
		listaPacientes.add(pac);

    	listaDoctores.add(new Doctor(123,"Diego","General",new ArrayList<String>()));
    	listaDoctores.add(new Doctor(123,"Santiago","Oftalmologia",new ArrayList<String>()));
    	listaDoctores.add(new Doctor(123,"Camilo","Odontologia",new ArrayList<String>()));
    	listaDoctores.add(new Doctor(123,"Elian","General",new ArrayList<String>()));
    	listaDoctores.add(new Doctor(123,"Daniel","Oftalmologia",new ArrayList<String>()));
    	listaDoctores.add(new Doctor(123,"Pacho","General",new ArrayList<String>()));

		listaMedicamentos.add(new Medicamento("Miopia", enf.get(0),"aef", 1, 10000));
		listaMedicamentos.add(new Medicamento("Cataratas", enf.get(1),"aef", 1, 10000));
		listaMedicamentos.add(new Medicamento("Muelas del juicio", enf.get(2),"aef", 1, 10000));
		listaMedicamentos.add(new Medicamento("Caries", enf.get(3),"aef", 1, 20000));

		Cita cit = new Cita("1414", pac, listaDoctores.get(4));
		pac.getHistoriaClinica().getHistorialCitas().add(cit);

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
