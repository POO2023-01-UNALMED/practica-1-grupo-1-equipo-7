package uiMain.Gestion.gestionPacientes;

import gestorAplicacion.administracion.HistoriaClinica;
import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.personas.Enfermedad;
import gestorAplicacion.personas.Paciente;

import java.util.Scanner;

public class RegistrarNuevaEnfermedad {
    public static void registrarNuevaEnfermedad(Hospital hospital) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese la cédula del paciente para registrar nuevas enfermedades: ");
        int cedula = sc.nextInt();
        Paciente paciente = hospital.buscarPaciente(cedula);
        if (paciente == null) { /*Si el paciente es null, quiere decir que no lo encontró, por lo que
        preguntamos si desea registrar este paciente */
            while (true) {
                System.out.println("El paciente no esta registrado.\n¿Desea registrarlo?");
                System.out.println("1. Si\n2. No \nSeleccione una opción");
                byte opcion = sc.nextByte();
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
            System.out.println("Ingresa el nombre de la enfermedad: ");
            String nombre = sc.next();
            System.out.println("Ingresa la tipologia de la enfermedad: ");
            String tipologia = sc.next();
            Enfermedad enfermedad = Enfermedad.registroEnfermedad(nombre, tipologia);
            if (enfermedad == null) {
                System.out.println("Esta enfermedad no está registrada\nIngrese la especialidad que la trata para registrarla");
                String especialidad = sc.next();
                Enfermedad nuevaEnfermedad = new Enfermedad(nombre, tipologia, especialidad);
                historiaPaciente.getEnfermedades().add(nuevaEnfermedad);
                System.out.println("¡La enfermedad ha sido agregada con éxito!");
            } else {
                enfermedad.nuevoEnfermo();
                historiaPaciente.getEnfermedades().add(enfermedad);
                System.out.println("¡La enfermedad ha sido agregada con éxito!");
            }
            while (true) {
                System.out.println("¿Desea agregar otra enfermedad? (s/n)");
                char agregar = sc.next().charAt(0);
                if (agregar == 's' || agregar == 'n') {
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
