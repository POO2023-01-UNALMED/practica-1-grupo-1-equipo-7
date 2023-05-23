package uiMain.Gestion;

import baseDatos.Serializador;
import gestorAplicacion.administracion.Hospital;
import uiMain.Gestion.gestionDoctores.GestionDoctor;
import uiMain.Gestion.gestionHospital.GestionHospital;
import uiMain.Gestion.gestionPacientes.GestionPaciente;
import uiMain.Gestion.gestionVacunas.GestionVacunas;

import java.util.Scanner;

public class MenuGestion {

    private static final Scanner sc = new Scanner(System.in);
    public static void menuGestion(Hospital hospital) {
        byte opcion;

        do {
            System.out.println("\nMENU Gestion");
            System.out.println("1. Gestionar Pacientes");
            System.out.println("2. Gestionar apartado de vacunas");
            System.out.println("3. Gestionar Doctores");
            System.out.println("4. Gestionar Hospital");
            System.out.println("6. Regresar al menu inicial");
            System.out.println("7. Salir");
            System.out.println("Ingrese una opcion: ");
            opcion = sc.nextByte();
            switch (opcion) {
                case 1 -> GestionPaciente.menuGestionPaciente(hospital);
                case 2 -> GestionVacunas.menuGestionVacunas(hospital);
                case 3 -> GestionDoctor.menuGestionDoctor(hospital);
                case 4 -> GestionHospital.menuGetionHospital(hospital);
                case 6 -> {
                    return;
                }
                case 7 -> {
                    Serializador.serializar(hospital);
                    System.exit(0);
                }
            }
        } while (true);
    }
}
