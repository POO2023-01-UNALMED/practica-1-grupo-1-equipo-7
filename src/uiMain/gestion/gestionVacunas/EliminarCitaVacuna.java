/* Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
y Santiago Arboleda Acevedo */
package uiMain.gestion.gestionVacunas;

import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.administracion.Vacuna;
import gestorAplicacion.servicios.CitaVacuna;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class EliminarCitaVacuna {

    public static void eliminarCitaVacuna(Hospital hospital){

        Scanner sc= new Scanner(System.in);

        System.out.println("Ingrese el nombre de la vacuna a la que se le eliminará la cita");
        String nombre= sc.nextLine();

        if (!RegistrarVacuna.verificarExistenciaVacuna(nombre,hospital)){
            System.out.println("Esta vacuna no existe en el inventario del hospital");
        }else{

            Vacuna vacuna=hospital.buscarVacuna(nombre);

            System.out.println("A continuación se muestran las citas de esta vacuna que no tiene paciente asignado: ");

            ArrayList<CitaVacuna> agendaDisponible= vacuna.mostrarAgendaDisponible();

            if(agendaDisponible.size()==0){
                System.out.println("La vacuna no tiene agenda disponible");
                return;
            }
            //Se muestran las citas
            for(int i=1; i<= agendaDisponible.size();i++){
                System.out.println(i+ ". "+agendaDisponible.get(i-1).getFecha());
            }

            System.out.println("Seleccione la cita que desea eliminar: ");
            byte numeroCita= sc.nextByte();

            //Se valida la opción
            while (numeroCita<1 || numeroCita>agendaDisponible.size()){
                System.out.println("Opción inválida, por favor ingrese otra opción: ");
                numeroCita= sc.nextByte();
            }

            for (int i=1; i<=vacuna.getAgenda().size(); i++){
                if(Objects.equals(vacuna.getAgenda().get(i - 1).getFecha(), agendaDisponible.get(numeroCita - 1).getFecha())){
                    vacuna.getAgenda().remove(vacuna.getAgenda().get(i-1));
                }
            }

            System.out.println("¡Cita eliminada con éxito!");
            System.out.println("\nVacuna: "+vacuna.getNombre());
            System.out.println("Agenda: ");
            for (CitaVacuna agenda : vacuna.getAgenda()){
                System.out.println(agenda.getFecha());
            }
        }
    }

}
