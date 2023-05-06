package gestorAplicacion.administracion;
import java.util.ArrayList;

import gestorAplicacion.personas.Paciente;

public class Formula {
	private ArrayList<Medicamento> listaMedicamentos = new ArrayList<Medicamento>();
	private Paciente paciente;
	private String tipoEnfermedad;

	public Formula(ArrayList<Medicamento> listaMedicamentos, Paciente paciente) {
		this.listaMedicamentos = listaMedicamentos;
		this.paciente = paciente;
	}
	public Formula(Paciente paciente){
		this(null, paciente);
	}

	public ArrayList<Medicamento> getListaMedicamentos() {
		return listaMedicamentos;
	}
	public void setListaMedicamentos(ArrayList<Medicamento> listaMedicamentos) {
		this.listaMedicamentos = listaMedicamentos;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	@Override
	public String toString() {
		return "Hola "+paciente.getNombre()+"\nEstos son tus medicamentos formulados:\n"+listaMedicamentos;
	}

}
