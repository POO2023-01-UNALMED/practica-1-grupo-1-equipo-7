package uiMain.Gestion.gestionVacunas;

import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.administracion.Vacuna;
import gestorAplicacion.servicios.CitaVacuna;

import java.util.Scanner;

public class AgregarCitaVacuna {

    static Scanner sc= new Scanner(System.in);

    public static void agregarCitaVacuna(Hospital hospital){

        System.out.println("Ingrese el nombre de la vacuna: ");
        String nombre= sc.nextLine();

        if(!RegistrarVacuna.verificarExistenciaVacuna(nombre, hospital)){
            System.out.println("Esta vacuna no existe en el inventario del hospital");
        }else{
            //busca la vacuna
            Vacuna vacuna= hospital.buscarVacuna(nombre);

            System.out.println("Ingrese la fecha de la cita nueva (Ejemplo de estructura: 3 de Abril, 8:00 am): ");
            String fecha= sc.nextLine();

            vacuna.getAgenda().add(new CitaVacuna(fecha,null,null));

            System.out.println("¡La cita ha sido añadida a la agenda de la vacuna correctamente!");

            System.out.println("\nVacuna: "+vacuna.getNombre());
            System.out.println("Agenda: ");
            for (int i=1; i<=vacuna.getAgenda().size(); i++){
                System.out.println(i+ ". "+ vacuna.getAgenda().get(i-1).getFecha());
            }

        }
    }
}
