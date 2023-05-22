package uiMain.Gestion.gestionHospital;

import baseDatos.Serializador;
import gestorAplicacion.administracion.Hospital;

import java.util.Scanner;

public class GestionHospital {
    private static final Scanner sc = new Scanner(System.in);

    public static void menuGetionHospital(Hospital hospital) {
        byte opcion;
        do {
            System.out.println("---Menu Gestion Hospital---");
            System.out.println("1. Cosntruir Habitación");
            System.out.println("2. Ver lista de Habitaciones");
            System.out.println("3. Destruir Habitación");
            System.out.println("4. Regresar al menu anterior");
            System.out.println("5. Salir");
            System.out.println("Ingrese una opcion: ");
            opcion = sc.nextByte();
            switch (opcion) {
                case 1 -> ConstruirHabitacion.construirHabitacion(hospital);
                case 2 -> VerHabitacion.verHabitacion(hospital);
                case 3 -> DestruirHabitacion.destruirHabitacion(hospital);
                case 4 -> {
                    return;
                }
                case 5 -> {
                    Serializador.serializar(hospital);
                    System.exit(0);
                }
            }
        } while (true);
    }
}

