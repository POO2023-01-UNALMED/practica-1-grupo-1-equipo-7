package uiMain.Gestion.gestionHospital;

import gestorAplicacion.administracion.CategoriaHabitacion;
import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.servicios.Habitacion;

import java.util.Scanner;

public class DestruirHabitacion {
    static Scanner sc = new Scanner(System.in);
    public static void destruirHabitacion (Hospital hospital){
        System.out.println("Por favor introduce la información de la habitación que se va ha destruir");
        System.out.println("Ingrese el número de la habitación:");
        int numero = sc.nextInt();
        System.out.println("Ingrese el tipo de habitacion que se desea Destruir: ");
        System.out.println("'CAMILLA', 'INDIVIDUAL', 'DOBLE', 'OBSERVACION', 'UCI', 'UCC'");
        String categoriaSeleccionada = sc.next();
        CategoriaHabitacion habitacionSeleccionada = CategoriaHabitacion.valueOf(categoriaSeleccionada);
        for (Habitacion habitacion: hospital.habitaciones){
            if ((habitacion.getNumero() == numero) && (habitacion.getCategoria()==habitacionSeleccionada)){
                if (!habitacion.isOcupada()){
                    hospital.getHabitaciones().remove(habitacion);
                    System.out.println("La habitación ha sido demolida con exito");
                    return;
                } else {
                    System.out.println("La habitación esta ocupada no puede ser demolida");
                    return;
                }
            }
            
        }
        System.out.println("Esta habitación no existe");
    }
}
