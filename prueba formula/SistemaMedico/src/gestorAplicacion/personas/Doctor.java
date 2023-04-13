package gestorAplicacion.personas;
import java.util.ArrayList;

import gestorAplicacion.administracion.Cita;


public class Doctor extends Persona{
	private String especialidad;
    private ArrayList<Cita> agenda = new ArrayList<Cita>();
    
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
    
}
