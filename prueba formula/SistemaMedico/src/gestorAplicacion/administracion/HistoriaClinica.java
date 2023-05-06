package gestorAplicacion.administracion;

import java.util.ArrayList;

import com.sun.source.doctree.DocTree;
import gestorAplicacion.personas.Doctor;
import gestorAplicacion.personas.Enfermedad;
import gestorAplicacion.personas.Paciente;

import javax.print.Doc;

public class HistoriaClinica {
	private Paciente paciente;
    private ArrayList<Cita> historialCitas = new ArrayList<Cita>();
    private ArrayList<Formula> historialFormulas = new ArrayList<Formula>();
	private ArrayList<Enfermedad> enfermedades = new ArrayList<Enfermedad>();

	public ArrayList<Doctor> buscarCitaDoc(String especialidad){
		ArrayList<Doctor> doctoresDisp = Hospital.buscarTipoDoctor(especialidad);
		ArrayList<Doctor> docCita = new ArrayList<>();
		for (Doctor doc : doctoresDisp){
			for (Cita cita : historialCitas){
				if (doc == cita.getDoctor()){
					docCita.add(doc);
				}
			}
		}
		return docCita;
	}
	public void actualizarHistoriaClinica(Cita citaAsignada) {
    	historialCitas.add(citaAsignada);
    }
    
    public void agregarFormula(Formula formula) {
    	historialFormulas.add(formula);
    }
    

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public ArrayList<Cita> getHistorialCitas() {
		return historialCitas;
	}

	public void setHistorialCitas(ArrayList<Cita> historialCitas) {
		this.historialCitas = historialCitas;
	}

	public ArrayList<Formula> getHistorialFormulas() {
		return historialFormulas;
	}

	public void setHistorialFormulas(ArrayList<Formula> historialFormulas) {
		this.historialFormulas = historialFormulas;
	}

	public ArrayList<Enfermedad> getEnfermedades() {
		return enfermedades;
	}

	public void setEnfermedades(ArrayList<Enfermedad> enfermedades) {
		this.enfermedades = enfermedades;
	}
}
