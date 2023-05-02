package uiMain;

import baseDatos.Serializador;
import gestorAplicacion.administracion.Hospital;

import java.util.Scanner;

public class Secretaria {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Hospital hospital = new Hospital();

        byte opcion;

        do {
            System.out.println("MENU PRINCIPAL");
            System.out.println("0. Salir");
            System.out.println("Ingrese una opcion: ");
            opcion = sc.nextByte();

            switch (opcion) {
                case 0 -> {
                    Serializador.serializar(hospital);
                    System.exit(0);
                }
            }
        } while (true);
    }
}
