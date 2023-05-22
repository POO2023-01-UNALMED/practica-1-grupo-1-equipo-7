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
            System.out.println("1. Construir Habitación");
            System.out.println("2. Ver lista de Habitaciones");
            System.out.println("3. Destruir Habitación");
            System.out.println("4. Agregar Medicamentos");
            System.out.println("5. Ver Inventario de medicamentos");
            System.out.println("6. Ver personas registradas en el hospital");
            System.out.println("7. Regresar al menu anterior");
            System.out.println("8. Salir");
            System.out.println("Ingrese una opcion: ");
            opcion = sc.nextByte();
            switch (opcion) {
                case 1 -> ConstruirHabitacion.construirHabitacion(hospital);
                case 2 -> VerHabitacion.verHabitacion(hospital);
                case 3 -> DestruirHabitacion.destruirHabitacion(hospital);
                case 4 -> AgregarMedicamentos.agregarMedicamentos(hospital);
                case 5 -> VerMedicamentos.verMedicamentos(hospital);
                case 6 -> VerPersonasRegistradas.verPersonasRegistradas(hospital);
                case 7 -> {
                    return;
                }
                case 8 -> {
                    Serializador.serializar(hospital);
                    System.exit(0);
                }
            }
        } while (true);
    }
}

