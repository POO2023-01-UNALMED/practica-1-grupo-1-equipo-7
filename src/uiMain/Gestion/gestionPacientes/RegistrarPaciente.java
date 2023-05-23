package uiMain.Gestion.gestionPacientes;

import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.personas.Paciente;

import java.util.Scanner;

public class RegistrarPaciente {

    public static void registrarPaciente (Hospital hospital){

        Scanner sc= new Scanner(System.in);

        System.out.println("Por favor introduce la información del paciente para su registro");
        System.out.println("Ingrese el nombre del paciente:");
        String nombre = sc.nextLine();
        System.out.println("Ingrese el número de cédula: ");
        int id = sc.nextInt();
        if (hospital.buscarPaciente(id) != null) {
            System.out.println("Este paciente ya esta registrado");
            return;
        }
        System.out.println("Ingrese su tipo de EPS 'subsidiado','contributivo' o 'particular': ");
        String eps = sc.next();
        Paciente paciente = new Paciente(id, nombre, eps);
        System.out.println("¡El paciente ha sido registrado con éxito!");
        System.out.println("Recuerde que actualmente el paciente su historia clínica está totalmente vacía");
        hospital.getListaPacientes().add(paciente);
        System.out.println(paciente);
    }
}
