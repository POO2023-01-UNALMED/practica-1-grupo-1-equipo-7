package gestorAplicacion.administracion;

import java.util.ArrayList;

import gestorAplicacion.personas.Paciente;

public class HistoriaClinica {
	private Paciente paciente;
    private ArrayList<Cita> historialCitas = new ArrayList<Cita>();
    private ArrayList<Formula> historialFormulas = new ArrayList<Formula>();

    
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
}
