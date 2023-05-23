package uiMain.Gestion.gestionHospital;

import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.administracion.Vacuna;
import gestorAplicacion.servicios.CitaVacuna;

public class VerVacunas {

    public static void verVacunas (Hospital hospital){

        for (Vacuna vacuna : hospital.getListaVacunas()){
            System.out.println("\nVacuna: "+vacuna.getNombre());
            System.out.println("Tipo: "+vacuna.getTipo());
            System.out.println("Precio: "+vacuna.getPrecio());
            System.out.println("Eps a la que está disponible: ");
            for (String fecha: vacuna.getTipoEps()){
                System.out.println(fecha);
            }
            System.out.println("Agenda de la vacuna: ");
            for (CitaVacuna agenda: vacuna.getAgenda()){
                System.out.println(agenda.getFecha());
            }

        }

    }
}