/* Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
y Santiago Arboleda Acevedo */
package uiMain.gestion.gestionHospital;

import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.personas.Doctor;
import gestorAplicacion.personas.Paciente;

import java.util.ArrayList;

public class VerPersonasRegistradas {

    public static void verPersonasRegistradas (Hospital hospital){
        ArrayList<Doctor> listaDoctores = hospital.getListaDoctores();
        ArrayList<Paciente> listaPacientes = hospital.getListaPacientes();

        System.out.println("Doctores: ");
        for (Doctor doc : listaDoctores){
            System.out.println(doc);
        }

        System.out.println("Pacientes:");
        for (Paciente pac : listaPacientes){
            System.out.println(pac);
        }
    }

}
