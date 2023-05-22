package uiMain.Gestion.gestionDoctores;

import baseDatos.Serializador;
import gestorAplicacion.administracion.Hospital;

import java.util.Scanner;

public class GestionDoctor {
    private static final Scanner sc = new Scanner(System.in);

    public static void menuGestionDoctor(Hospital hospital) {
        byte opcion;
        do {
            System.out.println("\nMENU Gestion Doctor");
            System.out.println("1. Registrar doctor");
            System.out.println("2. Eliminar doctor");
            System.out.println("3. Ver doctor");
            System.out.println("4. Añadir citas");
            System.out.println("5. Eliminar citas");
            System.out.println("6. Regresar al menu anterior");
            System.out.println("7. Salir");
            System.out.println("Ingrese una opcion: ");
            opcion = sc.nextByte();
            switch (opcion) {
                case 1 -> RegistrarDoctor.registrarDoctor(hospital);
                case 2 -> EliminarDoctor.eliminarDoctor(hospital);
                case 3 -> VerDoctor.verDoctor(hospital);
                case 4 -> AñadirCitas.añadirCitas(hospital);
                case 5 -> EliminarCitas.eliminarCitas(hospital);
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
