/* Esta clase se usa para eliminar pacientes del programa
* Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
 * y Santiago Arboleda Acevedo */
package uiMain.gestion.gestionPacientes;

import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.personas.Paciente;

import java.util.Scanner;

public class EliminarPaciente {
    public static void eliminarPaciente(Hospital hospital) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Ingrese la cédula del paciente que se eliminará: ");
        int cedula = sc.nextInt();
        Paciente paciente = hospital.buscarPaciente(cedula);
        if (paciente == null) { /*Si el paciente es null, quiere decir que no lo encontró */
            System.out.println("¡El paciente no existe o ya fue eliminado!");
        }
        hospital.getListaPacientes().remove(paciente);
        System.out.println("¡Paciente eliminado!");
    }
}
