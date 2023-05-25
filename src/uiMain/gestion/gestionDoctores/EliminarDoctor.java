/* Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
y Santiago Arboleda Acevedo */
package uiMain.gestion.gestionDoctores;

import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.personas.Doctor;

import java.util.Scanner;

public class EliminarDoctor {

    public static void eliminarDoctor(Hospital hospital) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Ingrese la cédula del doctor que se eliminará: ");
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
        hospital.getListaDoctores().remove(doctor);
        System.out.println("¡Doctor eliminado!");
    }
}
