package uiMain.Gestion.gestionVacunas;

import baseDatos.Serializador;
import gestorAplicacion.administracion.Hospital;


import java.util.Scanner;

public class GestionVacunas {
    static Scanner sc= new Scanner(System.in);
    public static void menuGestionVacunas(Hospital hospital){
        byte opcion;
        do {
            System.out.println("\nMENU Gestion Vacunas");
            System.out.println("1. Registrar vacuna");
            System.out.println("2. Eliminar vacuna");
            System.out.println("4. Regresar al menu anterior");
            System.out.println("5. Salir");
            System.out.println("Ingrese una opcion: ");
            opcion = sc.nextByte();
            switch (opcion) {
                case 1 -> RegistrarVacuna.registrarVacuna(hospital);
                case 2 -> EliminarVacuna.eliminarVacuna(hospital);
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
