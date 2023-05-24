/* Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
y Santiago Arboleda Acevedo */
package gestorAplicacion.personas;

import gestorAplicacion.servicios.Cita;

import java.util.ArrayList;

//Clase destinada a crear doctores
public class Doctor extends Persona{

    //Atributos
	private String especialidad;
    private ArrayList<Cita> agenda = new ArrayList<Cita>();

    //Constructor
    public Doctor(int cedula, String nombre, String tipoEps, String especialidad) {
    	super(cedula, nombre, tipoEps);
    	this.especialidad = especialidad;
        //Se crea una agenda por defecto a ese doctor
        agenda.add(new Cita(this,"3 de Abril, 8:00 am", null));
        agenda.add(new Cita(this,"4 de Abril, 3:00 pm", null));
        agenda.add(new Cita(this,"5 de Abril, 10:00 am", null));
    	}
    

    //Métodos

    //Muestra la agenda disponible de un doctor (citas que no tienen paciente asignado)
    public ArrayList<Cita> mostrarAgendaDisponible() {
    	ArrayList<Cita> agendaDisponible = new ArrayList<Cita>();
    	for(int i=1; i<=agenda.size(); i++) {
    		if (agenda.get(i-1).getPaciente() == null) {
    			agendaDisponible.add(agenda.get(i-1));
    		}
    }
    	return agendaDisponible;
    }

    //Método que asigna el paciente a una determinada cita de un doctor
    public Cita actualizarAgenda(Paciente pacienteAsignado, byte numeroCita, ArrayList<Cita> agendaDisponible) {
    	Cita citaAsignada = null;
    	for(int i=1; i<=agenda.size(); i++) {
    		if (agenda.get(i-1).getFecha() == agendaDisponible.get(numeroCita-1).getFecha()) {
    			agenda.get(i-1).setPaciente(pacienteAsignado);
    			citaAsignada = agenda.get(i-1);
    		}
    	}
		return citaAsignada;
    }

    //Getters y Setters
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

    @Override
    public String bienvenida() {
        return "Hola doctor, "+getNombre();
    }
    @Override
    public String toString(){
        return "Nombre: "+ getNombre() + "\nCédula: " + getCedula() + "\nTipo de EPS: " + getTipoEps() + "\nEspecialidad: " + getEspecialidad() ;
    }
}
