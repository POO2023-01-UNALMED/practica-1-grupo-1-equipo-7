package uiMain.Gestion.gestionPacientes;

import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.personas.Paciente;

import java.util.Scanner;

public class EliminarPaciente {
    public static void eliminarPaciente(Hospital hospital) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Ingrese la cédula del paciente que se eliminará: ");
        int cedula = sc.nextInt();
        Paciente paciente = hospital.buscarPaciente(cedula);
        if (paciente == null) { /*Si el paciente es null, quiere decir que no lo encontró, por lo que
        preguntamos si desea registrar este paciente */
            while (true) {
                System.out.println("El paciente no esta registrado.\n¿Desea registrarlo?");
                System.out.println("1. Si\n2. No \nSeleccione una opción");
                byte opcion = sc.nextByte();
                switch (opcion) {
                    case 1:
                        RegistrarPaciente.registrarPaciente(hospital);
                        return;

                    case 2:
                        System.out.println("Adios");
                        return;
                    default:
                        System.out.println("Opción Inválida");
                }
            }
        }
        hospital.getListaPacientes().remove(paciente);
        System.out.println("¡Paciente eliminado!");
    }
}
