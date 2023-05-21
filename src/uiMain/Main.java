package uiMain;

import baseDatos.Serializador;
import gestorAplicacion.administracion.Hospital;
import uiMain.Funcionalidades.MenuFuncionalidades;
import uiMain.Gestion.MenuGestion;

import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Hospital hospital = new Hospital();
        menuInicial(hospital);
    }

    public static void menuInicial(Hospital hospital) {

        byte opcion;

        do {
            System.out.println("\nMENU INICIAL");
            System.out.println("1. Servicios para pacientes");
            System.out.println("2. Gestionar registros");
            System.out.println("3. Salir");
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
