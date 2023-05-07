package uiMain.Gestion;

import baseDatos.Serializador;
import gestorAplicacion.administracion.Hospital;

import java.util.Scanner;

public class MenuGestion {

    private static final Scanner sc = new Scanner(System.in);
    public static void menuGestion(Hospital hospital) {
        byte opcion;

        do {
            System.out.println("MENU Gestion");
            System.out.println("1. Registrar paciente");
            System.out.println("6. Regresar al menu inicial");
            System.out.println("7. Salir");
            System.out.println("Ingrese una opcion: ");
            opcion = sc.nextByte();

            switch (opcion) {
                case 1 -> System.out.println("1");
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
