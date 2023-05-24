/* Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
y Santiago Arboleda Acevedo */
package baseDatos;

import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.administracion.Medicamento;
import gestorAplicacion.administracion.Vacuna;
import gestorAplicacion.personas.Doctor;
import gestorAplicacion.personas.Enfermedad;
import gestorAplicacion.personas.Paciente;
import gestorAplicacion.servicios.Habitacion;

import java.io.*;
import java.util.ArrayList;

public class Deserializador {

    public static void deserializar(Hospital hospital) {
        deserializarDoctores(hospital,new File("src\\baseDatos\\temp\\registroDoctores.dat"));
        deserializarPacientes(hospital,new File("src\\baseDatos\\temp\\registroPacientes.dat"));
        deserializarMedicamentos(hospital,new File("src\\baseDatos\\temp\\registroMedicamentos.dat"));
        deserializarVacunas(hospital,new File("src\\baseDatos\\temp\\registroVacunas.dat"));
        deserializarHabitaciones(hospital,new File("src\\baseDatos\\temp\\registroHabitaciones.dat"));
        deserializarEnfermedades(hospital,new File("src\\baseDatos\\temp\\registroEnfermedades.dat"));
    }

    public static void deserializarDoctores(Hospital hospital, File ruta) {
        try {
            FileInputStream file = new FileInputStream(ruta);
            ObjectInputStream in = new ObjectInputStream(file);
            hospital.setListaDoctores((ArrayList<Doctor>) in.readObject());

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error en la serializacion" + e);
        }
    }

    public static void deserializarPacientes(Hospital hospital, File ruta) {
        try {
            FileInputStream file = new FileInputStream(ruta);
            ObjectInputStream in = new ObjectInputStream(file);
            hospital.setListaPacientes((ArrayList<Paciente>) in.readObject());

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error en la serializacion" + e);
        }
    }

    public static void deserializarMedicamentos(Hospital hospital, File ruta) {
        try {
            FileInputStream file = new FileInputStream(ruta);
            ObjectInputStream in = new ObjectInputStream(file);
            hospital.setListaMedicamentos((ArrayList<Medicamento>) in.readObject());

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error en la serializacion" + e);
        }
    }

    public static void deserializarVacunas(Hospital hospital, File ruta) {
        try {
            FileInputStream file = new FileInputStream(ruta);
            ObjectInputStream in = new ObjectInputStream(file);
            hospital.setListaVacunas((ArrayList<Vacuna>) in.readObject());

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error en la serializacion" + e);
        }
    }

    public static void deserializarHabitaciones(Hospital hospital, File ruta) {
        try {
            FileInputStream file = new FileInputStream(ruta);
            ObjectInputStream in = new ObjectInputStream(file);
            Hospital.setHabitaciones((ArrayList<Habitacion>) in.readObject());

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error en la serializacion" + e);
        }
    }

    public static void deserializarEnfermedades(Hospital hospital, File ruta) {
        try {
            FileInputStream file = new FileInputStream(ruta);
            ObjectInputStream in = new ObjectInputStream(file);
            Enfermedad.setEnfermedadesRegistradas((ArrayList<Enfermedad>) in.readObject());

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error en la serializacion" + e);
        }
    }
}
