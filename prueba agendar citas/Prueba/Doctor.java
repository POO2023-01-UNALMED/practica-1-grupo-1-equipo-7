package Prueba;

import java.util.ArrayList;

public class Doctor extends Persona{
    private String especialidad;
    static private ArrayList<Cita> agenda = new ArrayList<Cita>();
    
    static {
    	agenda.add(new Cita("3 de Abril, 8:00 am", null));
    	agenda.add(new Cita("3 de Abril, 11:00 am", new Paciente(123, "juan", null)));
    	agenda.add(new Cita("4 de Abril, 3:00 pm", null));
    	agenda.add(new Cita("5 de Abril, 10:00 am", null));
    }
    public Doctor(int cedula, String nombre, String especialidad) {
    	super(cedula, nombre);
    	this.especialidad = especialidad;
    	}
    
    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    
    public ArrayList<Cita> getAgenda() {
        return agenda;
    }

    public void setAgenda(ArrayList<Cita> agenda) {
        this.agenda = agenda;
    }
    
    public ArrayList<Cita> mostrarAgendaDisponible() {
    	ArrayList<Cita> agendaDisponible = new ArrayList<Cita>();
    	for(int i=1; i<=agenda.size(); i++) {
    		if (agenda.get(i-1).getPaciente() == null) {
    			agendaDisponible.add(agenda.get(i-1));
    		}
    }
    	return agendaDisponible;
  }
    public void actualizarAgenda(Paciente pacienteAsignado, byte numeroCita, ArrayList<Cita> agendaDisponible) {
    	for(int i=1; i<=agenda.size(); i++) {
    		if (agenda.get(i-1).getFecha() == agendaDisponible.get(numeroCita-1).getFecha()) {
    			agenda.get(i-1).setPaciente(pacienteAsignado);
    		}
    	}
    }
}