/* Esta clase se encarga de administrar el menu de la gestion de las clases Paciente y Enfermedad
* Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
* y Santiago Arboleda Acevedo */
package uiMain.gestion.gestionPacientes;

import baseDatos.Serializador;
import gestorAplicacion.administracion.Hospital;

import java.util.Scanner;

public class GestionPaciente {
    private static final Scanner sc = new Scanner(System.in);

    public static void menuGestionPaciente(Hospital hospital) {
        byte opcion;
        do {
            // Menu para gestionar la clase Paciente
            System.out.println("\nMENU Gestion Paciente");
            System.out.println("1. Registrar paciente");
            System.out.println("2. Registrar enfermedad");
            System.out.println("3. Eliminar Paciente");
            System.out.println("4. Ver Paciente");
            System.out.println("5. --Regresar al menu anterior--");
            System.out.println("6. --Salir--");
            System.out.println("Ingrese una opcion: ");
            opcion = sc.nextByte();
            switch (opcion) {
                case 1 -> RegistrarPaciente.registrarPaciente(hospital);
                case 2 -> RegistrarNuevaEnfermedad.registrarNuevaEnfermedad(hospital);
                case 3 -> EliminarPaciente.eliminarPaciente(hospital);
                case 4 -> VerPaciente.verPaciente(hospital);
                case 5 -> {
                    return;
                }
                case 6 -> {
                    Serializador.serializar(hospital);
                    System.exit(0);
                }
            }
        } while (true);
    }
}
