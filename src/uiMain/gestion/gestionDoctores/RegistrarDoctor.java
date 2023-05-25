/* Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
y Santiago Arboleda Acevedo */
package uiMain.gestion.gestionDoctores;

import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.personas.Doctor;

import java.util.Scanner;

public class RegistrarDoctor {

    public static void registrarDoctor (Hospital hospital){

        Scanner sc= new Scanner(System.in);

        System.out.println("Por favor introduce la información del doctor para su registro");
        System.out.println("Ingrese el nombre del doctor:");
        String nombre = sc.next();
        System.out.println("Ingrese el número de cédula: ");
        int id = sc.nextInt();

        if (hospital.buscarDoctor(id) != null) {
            System.out.println("Este doctor ya esta registrado");
            return;
        }

        System.out.println("Ingrese su tipo de EPS 'Subsidiado','Contributivo' o 'Particular':");
        String eps = sc.next();
        System.out.println("Ingrese su especialidad 'General', 'Odontologia' o 'Oftalmologia': ");
        String especialidad = sc.next();

        Doctor doctor = new Doctor(id, nombre, eps, especialidad);
        System.out.println("¡El doctor ha sido registrado con éxito!");
        hospital.getListaDoctores().add(doctor);
        System.out.println(doctor);
    }
}
