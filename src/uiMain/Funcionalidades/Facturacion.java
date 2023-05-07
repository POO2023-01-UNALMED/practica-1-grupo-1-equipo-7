package uiMain.Funcionalidades;

import gestorAplicacion.administracion.Factura;
import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.personas.Paciente;
import gestorAplicacion.servicios.Servicio;

import java.util.ArrayList;
import java.util.Scanner;

public class Facturacion {

    private static final Scanner sc = new Scanner(System.in);

    public static void facturacion(Hospital hospital) {
        // Buscar paciente
        Paciente pacienteSeleccionado;
        System.out.println("Ingrese la cedula del paciente:");
        do {
            pacienteSeleccionado = hospital.buscarPaciente(sc.nextInt());
            if (pacienteSeleccionado == null) {
                System.out.println("No existe un paciente registrado con esta cedula. Intente de nuevo.");
            }
        } while (pacienteSeleccionado == null);

        // Buscar servicios pendientes de pago
        System.out.println("Servicios pendientes de pago:");
        ArrayList<Servicio> serviciosSinPagar = pacienteSeleccionado.getHistoriaClinica().obtenerServiciosSinPagar();
        for (Servicio servicio :
                serviciosSinPagar) {
            System.out.println(servicio);
        }

        // Seleccionar servicio a pagar
        Servicio servicioSeleccionado = null;
        System.out.println("Ingrese la ID del servicio que va a pagar:");
        do {
            long idSeleccionada = sc.nextLong();
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
        double precioServicioSeleccionado = pacienteSeleccionado.calcularPrecio(servicioSeleccionado);
        System.out.println("Total a pagar: $" + precioServicioSeleccionado);

        // Realizar pago
        Factura facturaServicioSeleccionado = new Factura(servicioSeleccionado.getIdServicio(), pacienteSeleccionado, precioServicioSeleccionado);

        System.out.println("Realizar pago? (S/N)");

        if (sc.nextLine().equalsIgnoreCase("s")) {
            facturaServicioSeleccionado.realizarPago();
            System.out.println("Pago registrado\n");
        }
        else {
            System.out.println("Pago cancelado\n");
        }

        // Limpiar la lista de servicios sin pagar
        serviciosSinPagar.clear();
    }
}
