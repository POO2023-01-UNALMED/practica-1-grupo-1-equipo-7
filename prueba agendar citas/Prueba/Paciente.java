package Prueba;

import java.util.ArrayList;

public class Paciente extends Persona{
    private HistoriaClinica historiaClinica;
    
    public Paciente(int cedula, String nombre, String tipoEps, HistoriaClinica historiaClinica) {
    	super(cedula, nombre, tipoEps);
    	this.historiaClinica = historiaClinica;
    }
    
    public ArrayList<Doctor> buscarDoctorEps(String especialidad){
    	ArrayList<Doctor> doctoresPorEspecialidad = Hospital.buscarTipoDoctor(especialidad);
    	ArrayList<Doctor> doctoresDisponibles = new ArrayList<Doctor>();
    	
    	for(int i=1; i<=doctoresPorEspecialidad.size(); i++) {
    		if (doctoresPorEspecialidad.get(i-1).getTipoEps() == getTipoEps() ) {
    			doctoresDisponibles.add(doctoresPorEspecialidad.get(i-1));
    		}
    }
		return doctoresDisponibles;
  }
    public void actualizarHistorialCitas(Cita citaAsignada) {
    	historiaClinica.getHistorialCitas().add(citaAsignada);
    }
    
    public void mostrarHistorial() {
    	for(int i=1; i<=historiaClinica.getHistorialCitas().size(); i++) {
    		System.out.println("Fecha: " + historiaClinica.getHistorialCitas().get(i-1).getFecha());
    		System.out.println("Paciente: " + historiaClinica.getHistorialCitas().get(i-1).getPaciente().getNombre());
    		System.out.println("Doctor: " + historiaClinica.getHistorialCitas().get(i-1).getDoctor().getNombre());
    	}
    }
    
    public double calcularPrecio(Cita citaAsignada) {
    	
    	final double IVA = 0.19;
    	double precioTotal = 0;
    	
    	if (citaAsignada.getDoctor().getEspecialidad() == "General") {
    		precioTotal += 5000;
    	}
    	
    	if (citaAsignada.getDoctor().getEspecialidad() == "Oftalmologia") {
    		precioTotal += 7000;
    	}
    	
    	if (citaAsignada.getDoctor().getEspecialidad() == "Odontologia") {
    		precioTotal += 10000;
    	}
    	
    	if (citaAsignada.getPaciente().getTipoEps() == "Contributivo") {
    		precioTotal += 2000;
    	}
    	
    	if (citaAsignada.getPaciente().getTipoEps() == "Subsidiado") {
    		precioTotal += 500;
    	}
    	
    	if (citaAsignada.getPaciente().getTipoEps() == "Particular") {
    		precioTotal += 10000;
    	}
    	
    	precioTotal = precioTotal*(1+IVA);
    	return precioTotal;
    }
}