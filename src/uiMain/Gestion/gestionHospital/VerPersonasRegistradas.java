package uiMain.Gestion.gestionHospital;

import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.personas.Doctor;
import gestorAplicacion.personas.Paciente;

import java.util.ArrayList;
import java.util.Scanner;

public class VerPersonasRegistradas {
    static Scanner sc = new Scanner(System.in);

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
