package baseDatos;

import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.administracion.Medicamento;
import gestorAplicacion.personas.Doctor;
import gestorAplicacion.personas.Paciente;

import java.io.*;
import java.util.ArrayList;

public class Deserializador {

    public static void deserializar(Hospital hospital) {
        deserializarDoctores(hospital,new File("src\\baseDatos\\temp\\registroDoctores.dat"));
        deserializarPacientes(hospital,new File("src\\baseDatos\\temp\\registroPacientes.dat"));
        deserializarMedicamentos(hospital,new File("src\\baseDatos\\temp\\registroMedicamentos.dat"));
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
}