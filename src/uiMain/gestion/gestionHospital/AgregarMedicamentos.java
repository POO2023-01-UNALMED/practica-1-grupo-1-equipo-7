/* Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
y Santiago Arboleda Acevedo */
package uiMain.gestion.gestionHospital;

import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.administracion.Medicamento;
import gestorAplicacion.personas.Enfermedad;

import java.util.ArrayList;
import java.util.Scanner;

public class AgregarMedicamentos {


    public static void agregarMedicamentos(Hospital hospital) {
        Scanner sc= new Scanner(System.in);
        boolean agregarOtro = false; //condicion para el while para ir agregando medicamentos
        do {
            System.out.println("Bienvenido! ¿Deseas aumentar el stock de algún medicamentos existente o agregar nuevos?");
            System.out.println("1. Aumentar stock de medicamentos existente.");
            System.out.println("2. Registrar nuevos medicamentos. ");
            ArrayList<Medicamento> listaMedicamentos = hospital.getListaMedicamentos();
            int opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    while (true) {
                        VerMedicamentos.verMedicamentos(hospital);
                        byte opcMed = sc.nextByte();
                        if (opcMed <= 0 || opcMed > listaMedicamentos.size()) {
                            System.out.println("Opción inválida");
                        } else {
                            Medicamento medicamentoEscogido = listaMedicamentos.get(opcMed - 1);
                            System.out.println("Ingrese el nuevo stock del medicamento");
                            int opcStock = sc.nextInt();
                            medicamentoEscogido.setCantidad(opcStock);
                            System.out.println("Medicamento actualizado: ");
                            System.out.println(medicamentoEscogido);
                            break;
                        }
                    }
                    break;

                case 2:
                    System.out.println("Ingrese el nombre del medicamento: ");
                    String nombre = sc.nextLine();
                    Enfermedad enfermedad = null;
                    ArrayList<Enfermedad> enfermedades = Enfermedad.getEnfermedadesRegistradas();
                    while (true) {
                        System.out.println("Elige la enfermedad que trata el medicamento: ");
                        System.out.println("0.Registrar nueva enfermedad al sistema");
                        for (int i = 0; i < enfermedades.size(); i++) {
                            System.out.println(i + 1 + "." + enfermedades.get(i));
                        }
                        byte opcEnf = sc.nextByte();
                        sc.nextLine();
                        if (opcEnf == 0) {
                            System.out.println("Ingrese el nombre de la enfermedad");
                            String nombreEnf = sc.nextLine();
                            System.out.println("Ingrese el nombre la tipología de la enfermedad");
                            String tipologia = sc.next();
                            System.out.println("Ingrese la especialidad que trata la enfermedad");
                            String especialidad = sc.next();
                            Enfermedad nuevaEnfermedad = new Enfermedad(especialidad, nombreEnf, tipologia);
                            System.out.println("¡La enfermedad ha sido registrada con éxito!");
                        } else if (opcEnf < 0 || opcEnf > enfermedades.size()) {
                            System.out.println("Opción inválida");
                        } else {
                            enfermedad = enfermedades.get(opcEnf - 1);
                            break;
                        }
                    }
                    System.out.println("Ingresa una descripción del medicamento");
                    String descripcion = sc.nextLine();
                    System.out.println("Ingresa la cantidad de medicamentos: ");
                    int cantidad = sc.nextInt();
                    System.out.println("Ingresa el precio: ");
                    float precio = sc.nextFloat();
                    Medicamento medicamento = new Medicamento(nombre, enfermedad, descripcion, cantidad, precio);
                    hospital.getListaMedicamentos().add(medicamento);
                    break;
                default:
                    System.out.println("Opción Inválida");
            }
            while (true) {
                System.out.println("¿Desea agregar otro medicamento? (s/n)");
                char agregar = sc.next().charAt(0);
                if (agregar == 's' || agregar == 'n') {
                    agregarOtro = agregar == 's';
                    break;
                } else {
                    System.out.println("Opción inválida");
                }
            }
        } while(agregarOtro);
        System.out.println("¡Los medicamentos han sido actualizados/creados con éxito!");
    }
}
