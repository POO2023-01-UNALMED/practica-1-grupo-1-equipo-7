package uiMain;

import baseDatos.Serializador;
import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.personas.Paciente;

import java.util.Scanner;

public class Secretaria {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Hospital hospital = new Hospital();

        byte opcion;

        do {
            System.out.println("MENU PRINCIPAL");
            System.out.println("8. Registrar paciente");
            System.out.println("9. Salir");
            System.out.println("Ingrese una opcion: ");
            opcion = sc.nextByte();

            switch (opcion) {
                case 8 -> registrarPaciente(hospital);
                case 9 -> {
                    Serializador.serializar(hospital);
                    System.exit(0);
                }
            }
        } while (true);
    }

    public static void registrarPaciente(Hospital hospital) {
        System.out.println("Ingrese la cedula: ");
        int cedula = sc.nextInt();
        System.out.println("Ingrese el nombre: ");
        String nombre = sc.nextLine();
        hospital.registrarPaciente(new Paciente(cedula,nombre));
    }
}
