/* Esta clase se encarga iniciar el programa.
 * Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
 * y Santiago Arboleda Acevedo */
package uiMain;

import baseDatos.Serializador;
import gestorAplicacion.administracion.Hospital;
import uiMain.funcionalidades.MenuFuncionalidades;
import uiMain.gestion.MenuGestion;

import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Hospital hospital = new Hospital(); // Inicio de instancia de hospital para serialización
        menuInicial(hospital);
    }

    public static void menuInicial(Hospital hospital) {

        byte opcion; // Se inicia la variable para elegir la opción que se selecciona

        do {
            // Inicio del programa, solo se selecciona a que menú se desea acceder
            System.out.println("\nMENU INICIAL");
            System.out.println("1. Servicios para pacientes");
            System.out.println("2. Gestionar registros");
            System.out.println("3. --Salir--");
            System.out.println("Ingrese una opcion: ");
            opcion = sc.nextByte();

            switch (opcion) {
                case 1 -> MenuFuncionalidades.menuFuncionalidades(hospital);
                case 2 -> MenuGestion.menuGestion(hospital);
                case 3 -> {
                    Serializador.serializar(hospital);
                    System.exit(0);
                }
            }
        } while (true);
    }
}
