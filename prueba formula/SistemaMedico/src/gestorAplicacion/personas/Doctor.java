package gestorAplicacion.personas;
import java.util.ArrayList;

import gestorAplicacion.administracion.Cita;


public class Doctor extends Persona{
	private String especialidad;
    private ArrayList<Cita> agenda = new ArrayList<Cita>();
	private ArrayList<String> tipoEps;
	private ArrayList<String> enfermedadesAtendidas;
    
    public Doctor(int cedula, String nombre, String especialidad, ArrayList<String> tipoEps) {
    	super(cedula, nombre);
    	this.especialidad = especialidad;
		this.tipoEps = tipoEps;
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

	public ArrayList<String> getTipoEps() {
		return tipoEps;
	}

	public void setTipoEps(ArrayList<String> tipoEps) {
		this.tipoEps = tipoEps;
	}
}
