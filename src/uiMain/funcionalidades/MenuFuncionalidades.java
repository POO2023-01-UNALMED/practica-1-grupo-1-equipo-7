/* Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
y Santiago Arboleda Acevedo */
package uiMain.funcionalidades;

import baseDatos.Serializador;
import gestorAplicacion.administracion.Hospital;

import java.util.Scanner;

public class MenuFuncionalidades {

    private static final Scanner sc = new Scanner(System.in);
    public static void menuFuncionalidades(Hospital hospital) {
        byte opcion;

        do {
            System.out.println("\nMENU FUNCIONALIDADES");
            System.out.println("1. Agendar una cita medica");
            System.out.println("2. Generar fórmula médica");
            System.out.println("3. Asignar habitación a un paciente");
            System.out.println("4. Aplicarse una vacuna");
            System.out.println("5. Facturacion");
            System.out.println("6. --Regresar al menu inicial--");
            System.out.println("7. --Salir--");
            System.out.println("Ingrese una opcion: ");
            opcion = sc.nextByte();

            switch (opcion) {
                case 1 -> AgendarCitas.agendarCitas(hospital);
                case 2 -> FormulaMedica.formulaMedica(hospital);
                case 3 -> AsignarHabitacion.asignarHabitacion(hospital);
                case 4 -> Vacunacion.vacunacion(hospital);
                case 5 -> Facturacion.facturacion(hospital);
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