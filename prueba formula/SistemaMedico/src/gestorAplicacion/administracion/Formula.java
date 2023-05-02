package gestorAplicacion.administracion;
import java.util.ArrayList;

import gestorAplicacion.personas.Paciente;

public class Formula {
	private ArrayList<Medicamento> listaMedicamentos = new ArrayList<Medicamento>();
	private Paciente paciente;
	private float precio;
	
	public Formula(ArrayList<Medicamento> listaMedicamentos, Paciente paciente, float precio) {
		this.listaMedicamentos = listaMedicamentos;
		this.paciente = paciente;
		this.precio = precio;
	}
	public Formula(Paciente paciente){
		this(null, paciente, 0);
	}

	public float calcularPrecio (ArrayList<Medicamento> medicamentos){
		float precio = 0;
		if (paciente.getTipoEps()=="Particular"){
			for (Medicamento med : medicamentos){
				precio += med.getPrecio();
			}
		}
		return precio;
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

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Hola "+paciente.getNombre()+"\n"+"Estos son tus medicamentos:"+listaMedicamentos+"\nPrecio de la formula: " + precio;
	}
	
}
