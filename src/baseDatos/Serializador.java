/* Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
y Santiago Arboleda Acevedo */
package baseDatos;

import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.personas.Enfermedad;

import java.io.*;

public class Serializador {

    public static void serializar(Hospital hospital) {
        serializarDoctores(hospital,new File("src\\baseDatos\\temp\\registroDoctores.dat"));
        serializarPacientes(hospital,new File("src\\baseDatos\\temp\\registroPacientes.dat"));
        serializarMedicamentos(hospital,new File("src\\baseDatos\\temp\\registroMedicamentos.dat"));
        serializarVacunas(hospital,new File("src\\baseDatos\\temp\\registroVacunas.dat"));
        serializarHabitaciones(hospital,new File("src\\baseDatos\\temp\\registroHabitaciones.dat"));
        serializarEnfermedades(hospital,new File("src\\baseDatos\\temp\\registroEnfermedades.dat"));
    }

    public static void serializarDoctores(Hospital hospital, File ruta) {
        try {
            PrintWriter pw = new PrintWriter(ruta);
            FileOutputStream file = new FileOutputStream(ruta);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(hospital.getListaDoctores());
            out.close();
            file.close();

        } catch (IOException e) {
            System.out.println("Error en la serializacion" + e);
        }
    }

    public static void serializarPacientes(Hospital hospital, File ruta) {
        try {
            PrintWriter pw = new PrintWriter(ruta);
            FileOutputStream file = new FileOutputStream(ruta);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(hospital.getListaPacientes());
            out.close();
            file.close();

        } catch (IOException e) {
            System.out.println("Error en la serializacion" + e);
        }
    }

    public static void serializarMedicamentos(Hospital hospital, File ruta) {
        try {
            PrintWriter pw = new PrintWriter(ruta);
            FileOutputStream file = new FileOutputStream(ruta);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(hospital.getListaMedicamentos());
            out.close();
            file.close();

        } catch (IOException e) {
            System.out.println("Error en la serializacion" + e);
        }
    }

    public static void serializarVacunas(Hospital hospital, File ruta) {
        try {
            PrintWriter pw = new PrintWriter(ruta);
            FileOutputStream file = new FileOutputStream(ruta);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(hospital.getListaVacunas());
            out.close();
            file.close();

        } catch (IOException e) {
            System.out.println("Error en la serializacion" + e);
        }
    }

    public static void serializarHabitaciones(Hospital hospital, File ruta) {
        try {
            PrintWriter pw = new PrintWriter(ruta);
            FileOutputStream file = new FileOutputStream(ruta);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(Hospital.getHabitaciones());
            out.close();
            file.close();

        } catch (IOException e) {
            System.out.println("Error en la serializacion" + e);
        }
    }

    public static void serializarEnfermedades(Hospital hospital, File ruta) {
        try {
            PrintWriter pw = new PrintWriter(ruta);
            FileOutputStream file = new FileOutputStream(ruta);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(Enfermedad.getEnfermedadesRegistradas());
            out.close();
            file.close();

        } catch (IOException e) {
            System.out.println("Error en la serializacion" + e);
        }
    }
}
