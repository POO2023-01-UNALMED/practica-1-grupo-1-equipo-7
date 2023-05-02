package gestorAplicacion.personas;

import gestorAplicacion.administracion.CuentaBancaria;
import gestorAplicacion.administracion.HistoriaClinica;
import gestorAplicacion.administracion.Medicamento;

import java.util.ArrayList;

public class Paciente extends Persona{
	private HistoriaClinica historiaClinica;
	private String tipoEps;
	private ArrayList<String> enfermedades;
	
	public Paciente(int cedula, String nombre, HistoriaClinica historiaClinica, String tipoEps, ArrayList<String> enfermedades) {
    	super(cedula, nombre);
		this.tipoEps = tipoEps;
    	this.historiaClinica = historiaClinica;
		this.enfermedades = enfermedades;
    }

	public ArrayList<Medicamento> medEnfermedad (ArrayList<Medicamento> medicamentos){
		ArrayList<Medicamento> medEnfermedades = new ArrayList<Medicamento>();
		for (Medicamento med : medicamentos){
			if (enfermedades.contains(med.getTipo()))
				medEnfermedades.add(med);
		}
		return medEnfermedades;
	}

	public HistoriaClinica getHistoriaClinica() {
		return historiaClinica;
	}

	public void setHistoriaClinica(HistoriaClinica historiaClinica) {
		this.historiaClinica = historiaClinica;
	}

	public String getTipoEps() {
		return tipoEps;
	}

	public void setTipoEps(String tipoEps) {
		this.tipoEps = tipoEps;
	}

	public ArrayList<String> getEnfermedades() {
		return enfermedades;
	}

	public void setEnfermedades(ArrayList<String> enfermedades) {
		this.enfermedades = enfermedades;
	}
}
