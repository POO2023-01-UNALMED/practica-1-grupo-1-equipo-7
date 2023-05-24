package uiMain.Funcionalidades;

import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.personas.Paciente;
import gestorAplicacion.servicios.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Facturacion {

    private static final Scanner sc = new Scanner(System.in);

    public static void facturacion(Hospital hospital) {
        // Buscar paciente
        Paciente pacienteSeleccionado;

        do {
            System.out.println("Ingrese la cedula del paciente:");
            pacienteSeleccionado = hospital.buscarPaciente(Integer.parseInt(sc.nextLine()));
            if (pacienteSeleccionado == null) {
                System.out.println("No existe un paciente registrado con esta cedula. Desea intentar de nuevo? (S/N)");
                if (sc.nextLine().equalsIgnoreCase("n")) {
                    return;
                }
            }
        } while (pacienteSeleccionado == null);

        // Buscar servicios pendientes de pago
        System.out.println("Servicios pendientes de pago:");
        ArrayList<Servicio> serviciosSinPagar = Servicio.obtenerServiciosSinPagar(pacienteSeleccionado);
        if (serviciosSinPagar.size()==0){
            System.out.println("Usted no tiene ningún servicio por pagar pendiente");
            return;
        }
        for (Servicio servicio :
                serviciosSinPagar) {
            if (servicio instanceof Formula)
                System.out.println(servicio.getIdServicio() + " - Formula");
            else if (servicio instanceof CitaVacuna)
                System.out.println(servicio.getIdServicio() + " - Vacuna");
            else if (servicio instanceof Habitacion)
                System.out.println(servicio.getIdServicio() + " - Habitacion");
            else if (servicio instanceof Cita) 
                System.out.println(servicio.getIdServicio() + " - Cita medica");

        }

        // Seleccionar servicio a pagar
        Servicio servicioSeleccionado = null;
        System.out.println("Ingrese la ID del servicio que va a pagar:");
        do {
            long idSeleccionada = Long.parseLong(sc.nextLine());
            for (Servicio servicio :
                    serviciosSinPagar) {
                if (servicio.getIdServicio() == idSeleccionada)
                    servicioSeleccionado = servicio;
            }
            if (servicioSeleccionado == null) {
                System.out.println("No existe el servicio seleccionado. Intente de nuevo.");
            }
        } while (servicioSeleccionado == null);

        // Calcular precio
        double precioServicioSeleccionado = 0;
        if (servicioSeleccionado instanceof Formula)
            precioServicioSeleccionado = pacienteSeleccionado.calcularPrecio((Formula) servicioSeleccionado);
        else if (servicioSeleccionado instanceof CitaVacuna)
            precioServicioSeleccionado = pacienteSeleccionado.calcularPrecio((CitaVacuna) servicioSeleccionado);
        else if (servicioSeleccionado instanceof Habitacion)
            precioServicioSeleccionado = pacienteSeleccionado.calcularPrecio((Habitacion) servicioSeleccionado);
        else if (servicioSeleccionado instanceof Cita)
            precioServicioSeleccionado = pacienteSeleccionado.calcularPrecio((Cita) servicioSeleccionado);

        // Realizar pago
        System.out.println("Total a pagar: $" + precioServicioSeleccionado);
        System.out.println("Realizar pago? (S/N)");

        if (sc.nextLine().equalsIgnoreCase("s")) {
            servicioSeleccionado.validarPago(pacienteSeleccionado, servicioSeleccionado.getIdServicio());
            System.out.println("Pago realizado");
        }
        else {
            System.out.println("Pago cancelado");
        }

        // Limpiar la lista de servicios sin pagar
        serviciosSinPagar.clear();
    }
}
