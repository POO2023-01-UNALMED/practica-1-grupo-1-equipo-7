package Prueba;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	//funcionalidad agendar cita	
	static public void agendarCita(){
		
		//menu para elegir el tipo de cita
		byte tipoCita;
		
		System.out.println("\nSeleccione el tipo de cita que requiere");
		System.out.println("1. General");
		System.out.println("2. Odontologia");
		System.out.println("3. Oftalmologia");
		System.out.print("Ingrese una opcion: ");
		
		tipoCita = sc.nextByte();
		
		//muestra los doctores disponibles segun el tipo de cita
		System.out.println("\nDoctores disponibles:");
		
		ArrayList<Doctor> doctoresDisponibles = new ArrayList<Doctor>();
		
		switch (tipoCita) {
		case 1:
			doctoresDisponibles = Hospital.buscarTipoDoctor("General");
			for(int i=1; i<=doctoresDisponibles.size(); i++) {
	    		System.out.println(i + ". " + doctoresDisponibles.get(i-1).getNombre());
			}
			break;
			
		case 2:
			doctoresDisponibles = Hospital.buscarTipoDoctor("Odontologia");
			for(int i=1; i<=doctoresDisponibles.size(); i++) {
	    		System.out.println(i + ". " + doctoresDisponibles.get(i-1).getNombre());
			}
			break;
			
		case 3:
			doctoresDisponibles = Hospital.buscarTipoDoctor("Oftalmologia");
			for(int i=1; i<=doctoresDisponibles.size(); i++) {
	    		System.out.println(i + ". " + doctoresDisponibles.get(i-1).getNombre());
			}
			break;
		}
		
		//elige un doctor de la lista anterior y muestra su agenda disponible
		System.out.print("\nSeleccione el doctor con el que quiere la cita: ");
		
		byte numeroDoctor;
		numeroDoctor = sc.nextByte();
		
		ArrayList<Cita> agendaDisponible = new ArrayList<Cita>();
		
		System.out.println("\nCitas disponibles: ");
		
		Doctor doctorAsignado = doctoresDisponibles.get(numeroDoctor-1);
		agendaDisponible = doctorAsignado.mostrarAgendaDisponible();
		
		for(int i=1; i<=agendaDisponible.size(); i++) {
    		System.out.println(i + ". " + agendaDisponible.get(i-1).getFecha());
    }
		
		//selecciona la cita, luego busca el paciente y lo asigna en la cita
		System.out.print("\nSeleccione la cita de su preferencia: ");
		
		byte numeroCita;
		numeroCita = sc.nextByte();
		
		System.out.print("Ingrese su numero de cedula: ");
		
		int numeroCedula;
		numeroCedula = sc.nextInt();
		
		Paciente pacienteAsignado = Hospital.buscarPaciente(numeroCedula);
		
		//actualiza la agenda del doctor con el paciente
		doctorAsignado.actualizarAgenda(pacienteAsignado, numeroCita, agendaDisponible);
		
		System.out.println("\nSu cita ha sido asignada con exito");
		
		//crea la cita con el parametro doctor, y la guarda en historia clinica
		Cita citaAsignada = new Cita(agendaDisponible.get(numeroCita-1).getFecha(), pacienteAsignado, doctorAsignado);
		HistoriaClinica.actualizarHistoriaClinica(citaAsignada);
		
		//resumen de la cita
		System.out.println("\nResumen de su cita: ");
		System.out.println("Fecha: " + citaAsignada.getFecha());
		System.out.println("Paciente: " + citaAsignada.getPaciente().getNombre());
		System.out.println("Doctor: " + citaAsignada.getDoctor().getNombre());
		
		//limpia los arrays de doctores y agenda disponibles al haberse asignado un paciente a una cita
		agendaDisponible.clear();
		doctoresDisponibles.clear();
	}

	public static void main(String[] args) {
		
		//menu principal
		byte opcion;
		
		System.out.println("MENU PRINCIPAL");
		System.out.println("1. Agendar cita");
		System.out.println("5. Salir");
		System.out.print("Ingrese una opcion: ");
			
		opcion = sc.nextByte();
			
		switch (opcion) {
		case 1:
			agendarCita();
			break;
		    
		case 5:
			System.out.println("saliendo");
			break;
		}		
	}
}
