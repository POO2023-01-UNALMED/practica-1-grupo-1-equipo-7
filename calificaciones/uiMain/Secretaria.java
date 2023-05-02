package uiMain;

import gestorAplicacion.administracion.Cita;
import gestorAplicacion.administracion.HistoriaClinica;
import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.personas.Doctor;
import gestorAplicacion.personas.Paciente;

import java.util.ArrayList;
import java.util.Scanner;

public class Secretaria {

    static Scanner sc = new Scanner(System.in);

    public static void calificacion() {
        System.out.println("Ingrese la cedula del paciente: ");

        Paciente paciente = Hospital.buscarPaciente(sc.nextLong());
        HistoriaClinica historia = paciente.getHistoriaClinica();

        System.out.println("Ingrese la especialidad del doctor: ");
        String especialidad = sc.next();
        ArrayList<Doctor> doctoresPorEspecialidad = Hospital.filtrarDoctoresPorEspecialidad(especialidad);
        for (Doctor doctor: doctoresPorEspecialidad)
            System.out.println(doctor.getCedula() + " " + doctor.getNombre());

        System.out.println("Ingrese la cedula del doctor: ");

        Doctor doctor = Hospital.buscarDoctor(sc.nextLong());
        boolean valido = paciente.validarCita(doctor);

        if (valido){
            System.out.println("Ingrese la calificacion: ");

            doctor.agregarCalificacion(sc.nextFloat());

            System.out.println("Calificacion ingresada");
            System.out.println("Calificacion actualizada de " + doctor.getNombre() + ": " + doctor.getCalificacion());
        }
        else {
            System.out.println("No tiene citas con este doctor");
        }

    }

    public static void main(String[] args) {
        temp();
        System.out.println("Bienvenido:\n" +
                "1. Calificacion");
        switch (sc.nextInt()) {
            case 1 -> calificacion();
        }
    }

    // Base de datos temporal
    public static void temp(){
        Hospital.agregarPaciente(123,"Santiago");
        Hospital.agregarDoctor(456,"Maria","general");
        Paciente p = Hospital.buscarPaciente(123);
        Doctor d = Hospital.buscarDoctor(456);
        p.getHistoriaClinica().actualizarHistoriaClinica(new Cita("Hoy",p,d));
        d.agregarCalificacion(3);
    }
}
