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
            System.out.println("3. Ver información de una vacuna");
            System.out.println("4. Agregar cita a una vacuna");
            System.out.println("5. Eliminar cita a una vacuna");
            System.out.println("6. Regresar al menu anterior");
            System.out.println("7. Salir");
            System.out.println("Ingrese una opcion: ");
            opcion = sc.nextByte();
            switch (opcion) {
                case 1 -> RegistrarVacuna.registrarVacuna(hospital);
                case 2 -> EliminarVacuna.eliminarVacuna(hospital);
                case 3 -> VerVacuna.verVacuna(hospital);
                case 4 -> AgregarCitaVacuna.agregarCitaVacuna(hospital);
                case 5 -> EliminarCitaVacuna.eliminarCitaVacuna(hospital);
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
