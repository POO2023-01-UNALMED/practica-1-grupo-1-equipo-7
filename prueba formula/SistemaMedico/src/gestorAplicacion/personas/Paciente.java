package gestorAplicacion.personas;

import gestorAplicacion.administracion.HistoriaClinica;

public class Paciente extends Persona{
	private HistoriaClinica historiaClinica;
	
	public Paciente(int cedula, String nombre, HistoriaClinica historiaClinica) {
    	super(cedula, nombre);
    	this.historiaClinica = historiaClinica;
    }

	public HistoriaClinica getHistoriaClinica() {
		return historiaClinica;
	}

	public void setHistoriaClinica(HistoriaClinica historiaClinica) {
		this.historiaClinica = historiaClinica;
	}
	
	
	public String toString() {
		return "Nombre:"+getNombre();
	}
}
