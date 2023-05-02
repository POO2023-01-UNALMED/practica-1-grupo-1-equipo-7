package gestorAplicacion.administracion;

import gestorAplicacion.personas.Doctor;
import gestorAplicacion.personas.Paciente;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Hospital {

    private static ArrayList<Paciente> listaPacientes = new ArrayList<>();
    private static ArrayList<Doctor> listaDoctores = new ArrayList<>();

    public static void agregarPaciente (int cedula, String nombre) {
        listaPacientes.add(new Paciente(cedula,nombre));
    }

    public static void agregarDoctor (int cedula, String nombre, String especialidad){
        listaDoctores.add(new Doctor(cedula,nombre,especialidad));
    }

    public static Paciente buscarPaciente (long cedula) {
        Paciente p = null;

        for (Paciente paciente: listaPacientes)
            if (paciente.getCedula() == cedula) {
                p = paciente;
                break;
            }

        return p;
    }

    public static Doctor buscarDoctor (long cedula) {
        Doctor d = null;

        for (Doctor doctor: listaDoctores)
            if (doctor.getCedula() == cedula) {
                d = doctor;
                break;
            }

        return d;
    }

    public static ArrayList<Doctor> filtrarDoctoresPorEspecialidad (String especialidad){
        ArrayList<Doctor> doctoresPorEspecialidad = new ArrayList<>();

        for (Doctor doctor: listaDoctores)
            if (Objects.equals(doctor.getEspecialidad(), especialidad))
                doctoresPorEspecialidad.add(doctor);

        return doctoresPorEspecialidad;
    }

}
