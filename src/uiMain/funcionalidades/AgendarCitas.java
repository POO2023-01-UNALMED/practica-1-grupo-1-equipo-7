/* Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
y Santiago Arboleda Acevedo */
package uiMain.funcionalidades;

import java.util.ArrayList;
import java.util.Scanner;

import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.servicios.Cita;
import gestorAplicacion.personas.Doctor;
import gestorAplicacion.personas.Paciente;
import uiMain.gestion.gestionPacientes.RegistrarPaciente;
//Clase destinada para la funcionalidad de agendar citas medicas.
public class AgendarCitas {
	
static Scanner sc = new Scanner(System.in);
	
	public static void agendarCitas(Hospital hospital) {

		//pide la cedula y busca el paciente
        System.out.print("Ingrese su numero de cedula: ");

		int numeroCedula;
		numeroCedula = sc.nextInt();

		Paciente pacienteAsignado = hospital.buscarPaciente(numeroCedula);

		//Verificación que el paciente se encuentre en la base de datos del hospital

        if (pacienteAsignado == null){
            while(true){
                //Se da la posibilidad de registrar ese paciente
                System.out.println("El paciente no está registrado.\n¿Desea registrarlo?");
                System.out.println("1. Si\n2.No \nSeleccione una opción");
                byte opcion= sc.nextByte();
                switch (opcion){
                    case 1:
                        RegistrarPaciente.registrarPaciente(hospital);
                        return;

                    case 2:
                        System.out.println("Adiós");
                        return;

                    default:
                        System.out.println("Opción Inválida");
                }
            }
        }

		//Mensaje de bienvenida
		System.out.println(pacienteAsignado.bienvenida());

		//se crea un arraylit de doctores que se usara mas adelante
		ArrayList<Doctor> doctoresDisponibles = new ArrayList<Doctor>();

		//ciclo while para cuando no hay doctores del tipo de eps del paciente en la especialidad seleccionada
		while (doctoresDisponibles.size()==0) {

		//menu para elegir el tipo de cita
		byte tipoCita;

		System.out.println("\nSeleccione el tipo de cita que requiere");
		System.out.println("1. General");
		System.out.println("2. Odontologia");
		System.out.println("3. Oftalmologia");
		System.out.println("4. --Regresar al menu--");
		System.out.print("Ingrese una opcion: ");

		tipoCita = sc.nextByte();

		//ciclo while para casos de error
		while ((tipoCita<1) ||(tipoCita>4) ){
            System.out.print("Opción fuera de rango, por favor ingrese otro número: ");
            tipoCita = sc.nextByte();
		}

		//muestra los doctores disponibles segun el tipo de cita y eps del paciente
		switch (tipoCita) {
		case 1:
			doctoresDisponibles = pacienteAsignado.buscarDoctorEps("General", hospital);

			if (doctoresDisponibles.size()>0) {
				System.out.println("\nLista de doctores Generales para el tipo de eps "
						+pacienteAsignado.getTipoEps()+":");
			for(int i=1; i<=doctoresDisponibles.size(); i++) {
	    		System.out.println(i + ". " + doctoresDisponibles.get(i-1).getNombre());
			}
		}
			else {
				System.out.println("\nNo hay doctores que atiendan al tipo de eps "
						+pacienteAsignado.getTipoEps()+" para esta categoria, por favor seleccione otra");
			}
			break;

		case 2:
			doctoresDisponibles = pacienteAsignado.buscarDoctorEps("Odontologia", hospital);
			if (doctoresDisponibles.size()>0) {
				System.out.println("\nLista de odontologos para el tipo de eps "
						+pacienteAsignado.getTipoEps()+":");
				for(int i=1; i<=doctoresDisponibles.size(); i++) {
		    		System.out.println(i + ". " + doctoresDisponibles.get(i-1).getNombre());
				}
			}
				else {
					System.out.println("\nNo hay doctores que atiendan al tipo de eps "
						+pacienteAsignado.getTipoEps()+" para esta categoria, por favor seleccione otra");
				}
			break;

		case 3:
			doctoresDisponibles = pacienteAsignado.buscarDoctorEps("Oftalmologia", hospital);
			if (doctoresDisponibles.size()>0) {
				System.out.println("\nLista de oftalmologos para el tipo de eps "
						+pacienteAsignado.getTipoEps()+":");
				for(int i=1; i<=doctoresDisponibles.size(); i++) {
		    		System.out.println(i + ". " + doctoresDisponibles.get(i-1).getNombre());
				}
			}
				else {
					System.out.println("\nNo hay doctores que atiendan al tipo de eps "
						+pacienteAsignado.getTipoEps()+" para esta categoria, por favor seleccione otra");
				}
			break;

			case 4:
				return;
		}
	}
		//se crea un arraylit de citas del doctor seleccionado
		ArrayList<Cita> agendaDisponible = new ArrayList<Cita>();

		//ciclo while para cuando el doctor seleccionado no tiene citas disponibles
		while(agendaDisponible.size()==0){
			//elige un doctor de la lista anterior
			System.out.print("\nSeleccione el doctor con el que quiere la cita: ");

			byte numeroDoctor;
			numeroDoctor = sc.nextByte();

			//ciclo while para casos de error
			while ((numeroDoctor<1) || numeroDoctor>doctoresDisponibles.size()+1){
				System.out.print("Opción fuera de rango, por favor ingrese otro número: ");
				numeroDoctor = sc.nextByte();
			}

			if(numeroDoctor==doctoresDisponibles.size()+1){return;}

			Doctor doctorAsignado = doctoresDisponibles.get(numeroDoctor-1);
			agendaDisponible = doctorAsignado.mostrarAgendaDisponible();

			if(agendaDisponible.size()>0) {
				//muestra la agenda disponible del doctor seleccionado
				System.out.println("\nCitas disponibles: ");
				for (int i = 1; i <= agendaDisponible.size(); i++) {
					System.out.println(i + ". " + agendaDisponible.get(i - 1).getFecha());
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


				//limpia los arrays de doctores y agenda disponibles al haberse asignado un paciente a una cita
				agendaDisponible.clear();
				doctoresDisponibles.clear();

				//muestra el historial de las citas que ha tenido el paciente
				System.out.println("\nhistorial citas de historia clinica del paciente: ");
				for(int i=1; i<=pacienteAsignado.getHistoriaClinica().getHistorialCitas().size(); i++) {
					System.out.println("");
					System.out.println("Fecha: " + pacienteAsignado.getHistoriaClinica().getHistorialCitas().get(i-1).getFecha());
					System.out.println("Paciente: " + pacienteAsignado.getHistoriaClinica().getHistorialCitas().get(i-1).getPaciente().getNombre());
					System.out.println("Doctor: " + pacienteAsignado.getHistoriaClinica().getHistorialCitas().get(i-1).getDoctor().getNombre());
				}

				//Despedida
				System.out.println("\n"+pacienteAsignado.despedida(citaAsignada));
				break;
			}
			else{
				System.out.println("\nEste doctor no tiene citas disponibles, por favor seleccione otro");
			}
		}
	}
}
