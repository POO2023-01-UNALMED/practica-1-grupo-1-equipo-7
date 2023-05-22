package uiMain.Gestion.gestionDoctores;

import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.personas.Doctor;
import gestorAplicacion.servicios.Cita;

import java.util.ArrayList;
import java.util.Scanner;

public class EliminarCitas {
    static Scanner sc = new Scanner(System.in);
    public static void eliminarCitas(Hospital hospital) {
        System.out.println("Ingrese la cédula del doctor al que se le eliminará una cita: ");
        int cedula = sc.nextInt();
        Doctor doctor = hospital.buscarDoctor(cedula);
        if (doctor == null) { /*Si el doctor es null, quiere decir que no lo encontró, por lo que
        preguntamos si desea registrar este doctor */
            while (true) {
                System.out.println("El doctor no esta registrado.\n¿Desea registrarlo?");
                System.out.println("1. Si\n2. No \nSeleccione una opción");
                byte opcion = sc.nextByte();
                switch (opcion) {
                    case 1:
                        RegistrarDoctor.registrarDoctor(hospital);
                        return;

                    case 2:
                        System.out.println("Adios");
                        return;
                    default:
                        System.out.println("Opción Inválida");
                }
            }
        }
        System.out.println("Seleccione la cita que desea eliminar, (solo podra eliminar citas que " +
                "no tengan un paciente asigando: ");

        ArrayList<Cita> agendaDisponible = doctor.mostrarAgendaDisponible();
        for(int i=1; i<=agendaDisponible.size(); i++) {
            System.out.println(i + ". " + agendaDisponible.get(i-1).getFecha());
        }

        System.out.println("Seleccione la cita que desea eliminar: ");
        byte numeroCita = sc.nextByte();
        for(int i=1; i<=doctor.getAgenda().size(); i++) {
            if (doctor.getAgenda().get(i-1).getFecha() == agendaDisponible.get(numeroCita-1).getFecha()) {
                doctor.getAgenda().remove(doctor.getAgenda().get(i-1));
            }
        }
        System.out.println("¡Cita eliminada con exito!");
        System.out.println(doctor);
        System.out.println("Agenda: ");
        for(int i=1; i<=doctor.getAgenda().size(); i++) {
            System.out.println(i + ". " + doctor.getAgenda().get(i-1).getFecha());
        }
    }
}
