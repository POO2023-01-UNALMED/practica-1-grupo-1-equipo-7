package Prueba;

import java.util.ArrayList;

public class Hospital {
    static private ArrayList<Paciente> listaPacientes = new ArrayList<Paciente>();;
    static private ArrayList<Doctor> listaDoctores = new ArrayList<Doctor>();
    static ArrayList<Doctor> doctoresDisponibles = new ArrayList<Doctor>();
    
    static {
    	
    	listaDoctores.add(new Doctor(123,"Diego","General"));
    	listaDoctores.add(new Doctor(123,"Santiago","Oftalmologia"));
    	listaDoctores.add(new Doctor(123,"Camilo","Odontologia"));
    	listaDoctores.add(new Doctor(123,"Elian","General"));
    	listaDoctores.add(new Doctor(123,"Daniel","Oftalmologia"));
    	listaDoctores.add(new Doctor(123,"Pacho","General"));
    	
    	listaPacientes.add(new Paciente(111, "Juan", new HistoriaClinica()));
    	listaPacientes.add(new Paciente(222, "Jose", new HistoriaClinica()));
    	listaPacientes.add(new Paciente(333, "Felipe", new HistoriaClinica()));
    	listaPacientes.add(new Paciente(444, "Maria", new HistoriaClinica()));
    	listaPacientes.add(new Paciente(555, "Pedro", new HistoriaClinica()));
    }
    
    static public void buscarTipoDoctor(String especialidad) {
    	for(int i=1; i<=listaDoctores.size(); i++) {
    		if (listaDoctores.get(i-1).getEspecialidad() == especialidad) {
    			doctoresDisponibles.add(listaDoctores.get(i-1));
    		}
    }
    	for(int i=1; i<=doctoresDisponibles.size(); i++) {
    		System.out.println(i + ". " + doctoresDisponibles.get(i-1).getNombre());
    	}
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
