/* Esta clase muestra el menu de gestion de las clases de la aplicación
 * Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
 * y Santiago Arboleda Acevedo */
package uiMain.gestion;

import baseDatos.Serializador;
import gestorAplicacion.administracion.Hospital;
import uiMain.gestion.gestionDoctores.GestionDoctor;
import uiMain.gestion.gestionHospital.GestionHospital;
import uiMain.gestion.gestionPacientes.GestionPaciente;
import uiMain.gestion.gestionVacunas.GestionVacunas;

import java.util.Scanner;

public class MenuGestion {

    private static final Scanner sc = new Scanner(System.in);
    public static void menuGestion(Hospital hospital) {
        byte opcion;
        // Menú para gestionar las clases del programa
        do {
            System.out.println("\nMENU Gestion");
            System.out.println("1. Gestionar Pacientes");
            System.out.println("2. Gestionar apartado de vacunas");
            System.out.println("3. Gestionar Doctores");
            System.out.println("4. Gestionar Hospital");
            System.out.println("5. --Regresar al menu inicial--");
            System.out.println("6. --Salir--");
            System.out.println("Ingrese una opcion: ");
            opcion = sc.nextByte();
            switch (opcion) {
                case 1 -> GestionPaciente.menuGestionPaciente(hospital);
                case 2 -> GestionVacunas.menuGestionVacunas(hospital);
                case 3 -> GestionDoctor.menuGestionDoctor(hospital);
                case 4 -> GestionHospital.menuGetionHospital(hospital);
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
