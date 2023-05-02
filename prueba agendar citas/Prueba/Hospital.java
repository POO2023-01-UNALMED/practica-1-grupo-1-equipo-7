package Prueba;

import java.util.ArrayList;

public class Hospital {
    static private ArrayList<Paciente> listaPacientes = new ArrayList<Paciente>();
    static private ArrayList<Doctor> listaDoctores = new ArrayList<Doctor>();
    
    static {
    	
    	listaDoctores.add(new Doctor(123,"Diego", "Subsidiado", "General"));
    	listaDoctores.add(new Doctor(123,"Santiago","Contributivo", "Oftalmologia"));
    	listaDoctores.add(new Doctor(123,"Camilo","Particular", "Odontologia"));
    	listaDoctores.add(new Doctor(123,"Elian","Contributivo", "General"));
    	listaDoctores.add(new Doctor(123,"Daniel", "Subsidiado","Oftalmologia"));
    	listaDoctores.add(new Doctor(123,"Pacho", "Particular", "General"));
    	
    	listaPacientes.add(new Paciente(111, "Juan", "Subsidiado", new HistoriaClinica()));
    	listaPacientes.add(new Paciente(222, "Jose", "Contributivo", new HistoriaClinica()));
    	listaPacientes.add(new Paciente(333, "Felipe", "Particular", new HistoriaClinica()));
    	listaPacientes.add(new Paciente(444, "Maria","Subsidiado", new HistoriaClinica()));
    	listaPacientes.add(new Paciente(555, "Pedro", "Particular", new HistoriaClinica()));
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
    
    static public Paciente buscarPaciente(int numeroCedula) {
    	Paciente pacienteAsignado = null;
    	for(int i=1; i<=listaPacientes.size(); i++) {
    		if (listaPacientes.get(i-1).getCedula() == numeroCedula) {
    			pacienteAsignado = listaPacientes.get(i-1);
    		}
    	}
    	return pacienteAsignado;
    }
}
