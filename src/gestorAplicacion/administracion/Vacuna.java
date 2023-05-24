/* Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
y Santiago Arboleda Acevedo */
package gestorAplicacion.administracion;

import gestorAplicacion.personas.Paciente;
import gestorAplicacion.servicios.CitaVacuna;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

//Clase destinada a los objetos vacuna
public class Vacuna implements Serializable {

    //Atributos
    private String tipo;
    private String nombre;
    private double precio;
    private ArrayList<String> tipoEps= new ArrayList<String>();
    private ArrayList<CitaVacuna> agenda= new ArrayList<CitaVacuna>();

    //Constructor
    public Vacuna(String tipo, String nombre, ArrayList<String> tipoEps, double precio) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.tipoEps = tipoEps;
        this.precio=precio;
        //Se crea una agenda por defecto para cada vacuna
        agenda.add(new CitaVacuna("3 de Abril, 8:00 am", null, this));
        agenda.add(new CitaVacuna("3 de Abril, 11:00 am", null, this));
        agenda.add(new CitaVacuna("4 de Abril, 3:00 pm", null, this));
        agenda.add(new CitaVacuna("5 de Abril, 10:00 am", null, this));
    }

    //Métodos

    //Método que muestra las citas que no tienen paciente asignado de una vacuna
    public ArrayList<CitaVacuna> mostrarAgendaDisponible(){
        ArrayList<CitaVacuna> agendaDisponible= new ArrayList<CitaVacuna>();
        for (int i=1; i<=agenda.size();i++){
            if (agenda.get(i-1).getPaciente()==null){
                agendaDisponible.add(agenda.get(i-1));
            }
        }
        return agendaDisponible;
    }

    //Este método asigna un paciente a una cita de una vacuna
    public CitaVacuna actualizarAgenda(Paciente pacienteAsignado, byte numeroCita, ArrayList<CitaVacuna> agendaDisponible){
        CitaVacuna citaAsignada= null;
        for (int i=1 ; i<=agenda.size();i++){
            if(Objects.equals(agenda.get(i - 1).getFecha(), agendaDisponible.get(numeroCita - 1).getFecha())){
                agenda.get(i-1).setPaciente(pacienteAsignado);
                citaAsignada= agenda.get(i-1);
            }
        }
        return citaAsignada;
    }

    //Getters y Setters
    public String getTipo() {
        return tipo;
    }

    public ArrayList<String> getTipoEps() {
        return tipoEps;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public ArrayList<CitaVacuna> getAgenda() {
        return agenda;
    }

}

