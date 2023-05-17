package gestorAplicacion.administracion;

import gestorAplicacion.personas.Paciente;
import gestorAplicacion.servicios.CitaVacuna;

import java.util.ArrayList;


public class Vacuna {
    private String tipo;
    private String nombre;
    private double precio;
    private ArrayList<String> tipoEps= new ArrayList<String>();
    private ArrayList<CitaVacuna> agenda= new ArrayList<CitaVacuna>();

    public Vacuna(String tipo, String nombre, ArrayList<String> tipoEps, double precio) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.tipoEps = tipoEps;
        this.precio=precio;
        agenda.add(new CitaVacuna("3 de Abril, 8:00 am", null, this));
        agenda.add(new CitaVacuna("3 de Abril, 11:00 am", null, this));
        agenda.add(new CitaVacuna("4 de Abril, 3:00 pm", null, this));
        agenda.add(new CitaVacuna("5 de Abril, 10:00 am", null, this));
    }

    public String getTipo() {
        return tipo;
    }

    public ArrayList<String> getTipoEps() {
        return tipoEps;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<CitaVacuna> mostrarAgendaDisponible(){
        ArrayList<CitaVacuna> agendaDisponible= new ArrayList<CitaVacuna>();
        for (int i=1; i<=agenda.size();i++){
            if (agenda.get(i-1).getPaciente()==null){
                agendaDisponible.add(agenda.get(i-1));
            }
        }
        return agendaDisponible;
    }

    public CitaVacuna actualizarAgenda(Paciente pacienteAsignado, byte numeroCita, ArrayList<CitaVacuna> agendaDisponible){
        CitaVacuna citaAsignada= null;
        for (int i=1 ; i<=agenda.size();i++){
            if(agenda.get(i-1).getFecha()==agendaDisponible.get(numeroCita-1).getFecha()){
                agenda.get(i-1).setPaciente(pacienteAsignado);
                citaAsignada= agenda.get(i-1);
            }
        }
        return citaAsignada;
    }

    public double getPrecio() {
        return precio;
    }
}

