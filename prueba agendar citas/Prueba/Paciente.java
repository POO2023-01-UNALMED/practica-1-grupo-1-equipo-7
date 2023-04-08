package Prueba;

public class Paciente extends Persona{
    private HistoriaClinica historiaClinica;
    
    public Paciente(int cedula, String nombre, HistoriaClinica historiaClinica) {
    	super(cedula, nombre);
    	this.historiaClinica = historiaClinica;
    }
    
    
}