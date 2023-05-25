/* Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
y Santiago Arboleda Acevedo */
package uiMain.gestion.gestionDoctores;

import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.personas.Doctor;
import gestorAplicacion.servicios.Cita;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class EliminarCitas {

    public static void eliminarCitas(Hospital hospital) {
        Scanner sc= new Scanner(System.in);
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
        System.out.println("Seleccione la cita que desea eliminar, (solo se muestran las citas que " +
                "no tienen un paciente asigando: ");

        ArrayList<Cita> agendaDisponible = doctor.mostrarAgendaDisponible();
        for(int i=1; i<=agendaDisponible.size(); i++) {
            System.out.println(i + ". " + agendaDisponible.get(i-1).getFecha());
        }

        System.out.println("Seleccione la cita que desea eliminar: ");
        byte numeroCita = sc.nextByte();

        //Se valida la opción
        while (numeroCita<1 || numeroCita>agendaDisponible.size()){
            System.out.println("Opción inválida, por favor ingrese otra opción: ");
            numeroCita= sc.nextByte();
        }

        for(int i=1; i<=doctor.getAgenda().size(); i++) {
            if (Objects.equals(doctor.getAgenda().get(i - 1).getFecha(), agendaDisponible.get(numeroCita - 1).getFecha())) {
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
