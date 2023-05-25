/* Esta clase se encarga de mostrar la información de algun Paciente, incluida la información de su historiaClinica
 * Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
 * y Santiago Arboleda Acevedo */
package uiMain.gestion.gestionPacientes;

import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.personas.Enfermedad;
import gestorAplicacion.personas.Paciente;
import gestorAplicacion.servicios.Cita;
import gestorAplicacion.servicios.CitaVacuna;
import gestorAplicacion.servicios.Formula;

import java.util.Scanner;

public class VerPaciente {
    public static void verPaciente(Hospital hospital) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese la cédula del paciente: ");
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
        System.out.println("Aquí está la información del paciente: ");
        System.out.println(paciente);
        System.out.println("Historia Clinica: ");
        System.out.println("Enfermedades: ");
        for (Enfermedad enfermedades : paciente.getHistoriaClinica().getEnfermedades()){
            System.out.println(enfermedades);
        }
        System.out.println("Formulas: ");
        for (Formula formulas : paciente.getHistoriaClinica().getListaFormulas()){
            System.out.println(formulas);
        }
        System.out.println("Citas: ");
        for (Cita citas : paciente.getHistoriaClinica().getHistorialCitas()){
            System.out.println("Fecha: "+citas.getFecha());
            System.out.println("Doctor: "+citas.getDoctor().getNombre());
        }
        System.out.println("Historial de Vacunas: ");
        for (CitaVacuna vacunas : paciente.getHistoriaClinica().getHistorialVacunas()){
            System.out.println(vacunas.getVacuna().getNombre());
        }
    }
}
