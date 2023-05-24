/* Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
y Santiago Arboleda Acevedo */
package uiMain.Gestion.gestionDoctores;

import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.personas.Doctor;

import java.util.Scanner;

public class VerDoctor {
    public static void verDoctor(Hospital hospital) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese la cédula del doctor: ");
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
        System.out.println("Aquí está la información del doctor: ");
        System.out.println(doctor);
        System.out.println("Agenda: ");
        for(int i=1; i<=doctor.getAgenda().size(); i++) {
            System.out.println(i + ". " + doctor.getAgenda().get(i-1).getFecha());
        }
    }
}
