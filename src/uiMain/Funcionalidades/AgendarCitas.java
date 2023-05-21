package uiMain.Funcionalidades;

import java.util.ArrayList;
import java.util.Scanner;

import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.servicios.Cita;
import gestorAplicacion.personas.Doctor;
import gestorAplicacion.personas.Paciente;

public class AgendarCitas {
	
static Scanner sc = new Scanner(System.in);
	
	public static void AgendarCitas(Hospital hospital) {
		
		//pide la cedula y busca el paciente
        System.out.print("Ingrese su numero de cedula: ");
		
		int numeroCedula;
		numeroCedula = sc.nextInt();
		
		Paciente pacienteAsignado = hospital.buscarPaciente(numeroCedula);
		
		ArrayList<Doctor> doctoresDisponibles = new ArrayList<Doctor>();
		
		//ciclo while para cuando no hay doctores con citas disponibles de la especialidad seleccionada
		while (doctoresDisponibles.size()==0) {
			
		//menu para elegir el tipo de cita
		byte tipoCita;
		
		System.out.println("\nSeleccione el tipo de cita que requiere");
		System.out.println("1. General");
		System.out.println("2. Odontologia");
		System.out.println("3. Oftalmologia");
		System.out.print("Ingrese una opcion: ");
		
		tipoCita = sc.nextByte();
		
		//ciclo while para casos de error
		while ((tipoCita<1) ||(tipoCita>3) ){
            System.out.print("Opción fuera de rango, por favor ingrese otro número: ");
            tipoCita = sc.nextByte();
		}
            
		//muestra los doctores disponibles segun el tipo de cita y eps del paciente
		switch (tipoCita) {
		case 1:
			doctoresDisponibles = pacienteAsignado.buscarDoctorEps("General", hospital);
			
			if (doctoresDisponibles.size()>0) {
				System.out.println("\nDoctores disponibles:");
			for(int i=1; i<=doctoresDisponibles.size(); i++) {
	    		System.out.println(i + ". " + doctoresDisponibles.get(i-1).getNombre());
			}
		}
			else {
				System.out.println("\nNo hay doctores con citas disponibles para esta categoria, por favor seleccione otra");
			}
			break;
			
		case 2:
			doctoresDisponibles = pacienteAsignado.buscarDoctorEps("Odontologia", hospital);
			if (doctoresDisponibles.size()>0) {
				System.out.println("\nDoctores disponibles:");
				for(int i=1; i<=doctoresDisponibles.size(); i++) {
		    		System.out.println(i + ". " + doctoresDisponibles.get(i-1).getNombre());
				}
			}
				else {
					System.out.println("\nNo hay doctores con citas disponibles para esta categoria, por favor seleccione otra");
				}
			break;
			
		case 3:
			doctoresDisponibles = pacienteAsignado.buscarDoctorEps("Oftalmologia", hospital);
			if (doctoresDisponibles.size()>0) {
				System.out.println("\nDoctores disponibles:");
				for(int i=1; i<=doctoresDisponibles.size(); i++) {
		    		System.out.println(i + ". " + doctoresDisponibles.get(i-1).getNombre());
				}
			}
				else {
					System.out.println("\nNo hay doctores con citas disponibles para esta categoria, por favor seleccione otra");
				}
			break;
		}
	}
		
		//elige un doctor de la lista anterior
		System.out.print("\nSeleccione el doctor con el que quiere la cita: ");
		
		byte numeroDoctor;
		numeroDoctor = sc.nextByte();
		
		//ciclo while para casos de error
		while ((numeroDoctor<1) || numeroDoctor>doctoresDisponibles.size()){
            System.out.print("Opción fuera de rango, por favor ingrese otro número: ");
            numeroDoctor = sc.nextByte();
		}
		
		ArrayList<Cita> agendaDisponible = new ArrayList<Cita>();
		
		//muestra la agenda disponible del doctor seleccionado
		System.out.println("\nCitas disponibles: ");
		
		Doctor doctorAsignado = doctoresDisponibles.get(numeroDoctor-1);
		agendaDisponible = doctorAsignado.mostrarAgendaDisponible();
		
		for(int i=1; i<=agendaDisponible.size(); i++) {
    		System.out.println(i + ". " + agendaDisponible.get(i-1).getFecha());
    }
	
		//selecciona la cita de la agenda disponible del doctor
		System.out.print("\nSeleccione la cita de su preferencia: ");
		
		byte numeroCita;
		numeroCita = sc.nextByte();
		
		//ciclo while para casos de error
		while ((numeroCita<1) || numeroCita>agendaDisponible.size()){
            System.out.print("Opción fuera de rango, por favor ingrese otro número: ");
            numeroCita = sc.nextByte();
		}
		
		//actualiza la agenda del doctor con el paciente
		Cita citaAsignada = doctorAsignado.actualizarAgenda(pacienteAsignado, numeroCita, agendaDisponible);
		
		System.out.println("\nSu cita ha sido asignada con exito");		
		
		//actualiza el historial de citas del paciente
		pacienteAsignado.actualizarHistorialCitas(citaAsignada);
		
		
		//resumen de la cita
		System.out.println("\nResumen de su cita: ");
		System.out.println("Fecha: " + citaAsignada.getFecha());
		System.out.println("Paciente: " + citaAsignada.getPaciente().getNombre());
		System.out.println("Doctor: " + citaAsignada.getDoctor().getNombre());
		System.out.println("Precio de la cita: " + pacienteAsignado.calcularPrecio(citaAsignada));
		
		
		//limpia los arrays de doctores y agenda disponibles al haberse asignado un paciente a una cita
		agendaDisponible.clear();
		doctoresDisponibles.clear();
		
		System.out.println("\nhistorial citas de historia clinica del paciente: ");
		pacienteAsignado.mostrarHistorial();

		//Despedida
		System.out.println("\n"+pacienteAsignado.despedida(citaAsignada));
	}
}
