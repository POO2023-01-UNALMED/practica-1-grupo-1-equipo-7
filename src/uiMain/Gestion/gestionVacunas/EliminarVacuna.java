package uiMain.Gestion.gestionVacunas;

import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.administracion.Vacuna;

import java.util.Objects;
import java.util.Scanner;

public class EliminarVacuna {

    static Scanner sc= new Scanner(System.in);
    public static void eliminarVacuna(Hospital hospital){

        System.out.println("Por favor ingresa el nombre de la vacuna que quiere eliminar: ");

        String nombre= sc.nextLine();

        if(!RegistrarVacuna.verificarExistenciaVacuna(nombre, hospital)){
            System.out.println("Esta vacuna no existe en el inventario del hospital");
        }else{
            for(int i=1; i<=hospital.getListaVacunas().size(); i++){
                if(Objects.equals(hospital.getListaVacunas().get(i - 1).getNombre(), nombre)){
                    System.out.println(hospital.getListaVacunas().get(i-1).getNombre()+" acaba de ser eliminada");
                    hospital.getListaVacunas().remove(hospital.getListaVacunas().get(i-1));
                }
            }
            /*
            for (Vacuna vacuna: hospital.getListaVacunas()){
                if (Objects.equals(vacuna.getNombre(), nombre)){
                    System.out.println(vacuna.getNombre()+" acaba de ser eliminada");
                    hospital.getListaVacunas().remove(vacuna);
                }
            }
             */
        }

    }
}
