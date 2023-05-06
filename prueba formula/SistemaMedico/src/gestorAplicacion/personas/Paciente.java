package gestorAplicacion.personas;

import gestorAplicacion.administracion.CuentaBancaria;
import gestorAplicacion.administracion.HistoriaClinica;
import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.administracion.Medicamento;
import gestorAplicacion.personas.Doctor;

import java.util.ArrayList;

public class Paciente extends Persona{
	private HistoriaClinica historiaClinica;
	private String tipoEps;
	public Paciente(int cedula, String nombre, HistoriaClinica historiaClinica, String tipoEps) {
    	super(cedula, nombre);
		this.tipoEps = tipoEps;
    	this.historiaClinica = historiaClinica;
    }
	public Paciente(int cedula, String nombre, String tipoEps) {
		this(cedula, nombre, new HistoriaClinica(), tipoEps);
	}
	public ArrayList<Medicamento> medEnfermedad (Enfermedad enfermedad){
		ArrayList<Medicamento> medicamentos = Hospital.medicamentosDisponibles();
		ArrayList<Medicamento> medEnfermedades = new ArrayList<Medicamento>();
		for (Medicamento med : medicamentos){
			if (enfermedad == med.getEnfermedad()){
				medEnfermedades.add(med);
			}

		}
		return medEnfermedades;
	}
	public float calcularPrecio (ArrayList<Medicamento> medicamentos){
		float precio = 0;
		for (Medicamento med : medicamentos){
			precio += med.getPrecio();
		}
		return precio;
	}	public ArrayList<Doctor> buscarDoctorEps(String Especialidad){
		ArrayList<Doctor> doctorEsp = Hospital.buscarTipoDoctor(Especialidad);
		ArrayList<Doctor> doctorEps = new ArrayList<Doctor>();
		for (Doctor doc : doctorEsp){
			if (doc.getTipoEps().contains(tipoEps));{
				doctorEps.add(doc);
			}
		}
		return doctorEps;
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
}
