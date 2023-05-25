/* Esta clase se encarga de registrar nuevas enfermedades a la historiaClinica de algun Paciente
* Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
 * y Santiago Arboleda Acevedo */
package uiMain.gestion.gestionPacientes;

import gestorAplicacion.administracion.HistoriaClinica;
import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.personas.Enfermedad;
import gestorAplicacion.personas.Paciente;

import java.util.ArrayList;
import java.util.Scanner;

public class RegistrarNuevaEnfermedad {
    public static void registrarNuevaEnfermedad(Hospital hospital) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Ingrese la cédula del paciente para registrar nuevas enfermedades: ");
        int cedula = sc.nextInt();
        sc.nextLine(); // nextLine para que no quede guardado el Int y se salte el siguiente scanner
        Paciente paciente = hospital.buscarPaciente(cedula);
        if (paciente == null) { /*Si el paciente es null, quiere decir que no lo encontró, por lo que
        preguntamos si desea registrar este paciente */
            while (true) {
                System.out.println("El paciente no esta registrado.\n¿Desea registrarlo?");
                System.out.println("1. Si\n2. No \nSeleccione una opción");
                byte opcion = sc.nextByte();
                sc.nextLine();
                switch (opcion) {
                    case 1:
                        RegistrarPaciente.registrarPaciente(hospital);
                        return;

                    case 2:
                        System.out.println("Adios");
                        return;
                    default:
                        System.out.println("Opción Inválida");
                }
            }
        }
        HistoriaClinica historiaPaciente = paciente.getHistoriaClinica();
        if (historiaPaciente.getEnfermedades() == null){
            System.out.println("El paciente no tiene enfermedades registradas");
        } else {
            System.out.println("Estas son las enfermedades del paciente");
            for (int i = 0; i < paciente.getHistoriaClinica().getEnfermedades().size(); i++) {
                System.out.println(i + 1 + "." + paciente.getHistoriaClinica().getEnfermedades().get(i));
            }
        }
        boolean agregarOtro = false; //condicion para el while para ir agregando enfermedades
        do {
            ArrayList <Enfermedad> enfermedades = Enfermedad.getEnfermedadesRegistradas();
            while (true){
                System.out.println("Estas son las enfermedades registradas en el sistema, por favor elige una.");
                System.out.println("0.Registrar nueva enfermedad al sistema");
                for (int i = 0; i < enfermedades.size(); i++){
                    System.out.println(i + 1 + "." + enfermedades.get(i));
                }
                byte opcEnf = sc.nextByte();
                sc.nextLine();
                if (opcEnf == 0) { // Se preguntan los datos de la enfermedad que se desea agregar y no esta registrada
                    System.out.println("Ingrese el nombre de la enfermedad");
                    String nombre = sc.nextLine();
                    System.out.println("Ingrese el nombre la tipología de la enfermedad");
                    String tipologia = sc.next();
                    System.out.println("Ingrese la especialidad que trata la enfermedad");
                    String especialidad = sc.next();
                    Enfermedad nuevaEnfermedad = new Enfermedad(especialidad, nombre, tipologia);
                    historiaPaciente.getEnfermedades().add(nuevaEnfermedad);
                    System.out.println("¡La enfermedad ha sido agregada con éxito!");
                    break;
                } else if (opcEnf < 0 || opcEnf > enfermedades.size()){
                    System.out.println("Opción inválida");
                } else {
                    Enfermedad enfermedad = enfermedades.get(opcEnf-1);
                    enfermedad.nuevoEnfermo();
                    historiaPaciente.getEnfermedades().add(enfermedad);
                    System.out.println("¡La enfermedad ha sido agregada con éxito!");
                    break;
                }
            }
            while (true) {
                System.out.println("¿Desea agregar otra enfermedad? (s/n)");
                char agregar = sc.next().charAt(0);
                if (agregar == 's' || agregar == 'n') { //If con el caso para seguir o terminar el bucle
                    agregarOtro = agregar == 's';
                    break;
                } else {
                    System.out.println("Opción inválida");
                }
            }
        } while(agregarOtro);
        System.out.println("¡La enfermedad o enfermedades han sido agregadas con éxito!");
    }
}
