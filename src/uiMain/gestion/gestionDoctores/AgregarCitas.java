/* Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
y Santiago Arboleda Acevedo */
package uiMain.gestion.gestionDoctores;

import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.personas.Doctor;
import gestorAplicacion.servicios.Cita;

import java.util.Scanner;

public class AgregarCitas {
    public static void agregarCitas(Hospital hospital) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese la cédula del doctor: ");
        int cedula = sc.nextInt();
        sc.nextLine();
        Doctor doctor = hospital.buscarDoctor(cedula);
        if (doctor == null) { /*Si el doctor es null, quiere decir que no lo encontró, por lo que
        preguntamos si desea registrar este doctor */
            while (true) {
                System.out.println("El doctor no esta registrado.\n¿Desea registrarlo?");
                System.out.println("1. Si\n2. No \nSeleccione una opción");
                byte opcion = sc.nextByte();
                sc.nextLine();
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
        System.out.println("Ingrese la fecha de la cita: ");
        String fecha = sc.nextLine();
        doctor.getAgenda().add(new Cita(doctor, fecha, null));
        System.out.println("La cita ha sido agregada a la agenda del doctor correctamente");
        System.out.println(doctor);
        System.out.println("Agenda: ");
        for(int i=1; i<=doctor.getAgenda().size(); i++) {
            System.out.println(i + ". " + doctor.getAgenda().get(i-1).getFecha());
        }
    }
}
