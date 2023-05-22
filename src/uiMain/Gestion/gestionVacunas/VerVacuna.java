package uiMain.Gestion.gestionVacunas;

import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.administracion.Vacuna;

import java.util.Objects;
import java.util.Scanner;

public class VerVacuna {

    static Scanner sc= new Scanner(System.in);

    public static void verVacuna(Hospital hospital){

        System.out.println("Por favor ingrese el nombre de la vacuna a la que quiere ver su información: ");

        String nombre= sc.nextLine();

        if(!RegistrarVacuna.verificarExistenciaVacuna(nombre,hospital)){
            System.out.println("Esta vacuna no existe en el inventario del hospital");
        }else{
            for (Vacuna vacuna: hospital.getListaVacunas()){
                if(Objects.equals(vacuna.getNombre(), nombre)){
                    System.out.println("\nNombre: "+vacuna.getNombre());
                    System.out.println("Tipo: "+vacuna.getTipo());
                    System.out.println("Eps a la que está disponible: ");
                    for (int i=1; i<=vacuna.getTipoEps().size(); i++){
                        System.out.println(i+". "+vacuna.getTipoEps().get(i-1));
                    }
                    System.out.println("Precio: "+vacuna.getPrecio());
                }
            }
        }

    }

}
