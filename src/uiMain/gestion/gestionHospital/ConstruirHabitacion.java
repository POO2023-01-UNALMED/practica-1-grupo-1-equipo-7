/* Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
y Santiago Arboleda Acevedo */
package uiMain.gestion.gestionHospital;

import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.servicios.Habitacion;
import gestorAplicacion.administracion.CategoriaHabitacion;

import java.util.Scanner;

public class ConstruirHabitacion {
    public static void construirHabitacion (Hospital hospital){

        Scanner sc= new Scanner(System.in);

        System.out.println("Por favor introduce la información de la nueva habitación");
        System.out.println("Ingrese el número de la habitación:");
        int numero = sc.nextInt();
        CategoriaHabitacion categoriaHabitacion = null;
        int eleccion;
        do {
            System.out.println("Elija el tipo de habitacion que desea construir");
            System.out.println("1. CAMILLA");
            System.out.println("2. INDIVIDUAL");
            System.out.println("3. DOBLE");
            System.out.println("4. OBSERVACION");
            System.out.println("5. UCI");
            System.out.println("6. UCC");
            System.out.print("Ingrese una opción: ");
            eleccion = sc.nextInt();
            sc.nextLine();


            switch (eleccion) {
                case 1:
                    categoriaHabitacion=CategoriaHabitacion.CAMILLA;
                    break;
                case 2:
                    categoriaHabitacion=CategoriaHabitacion.INDIVIDUAL;
                    break;
                case 3:
                    categoriaHabitacion=CategoriaHabitacion.DOBLE;
                    break;
                case 4:
                    categoriaHabitacion=CategoriaHabitacion.OBSERVACION;
                    break;
                case 5:
                    categoriaHabitacion=CategoriaHabitacion.UCI;
                    break;
                case 6:
                    categoriaHabitacion=CategoriaHabitacion.UCC;
                    break;
            }
        } while (eleccion != 1 && eleccion != 2 && eleccion != 3 && eleccion != 4 && eleccion != 5 && eleccion != 6);
        boolean ocupada=false;
        int dias=0;

        for (Habitacion habitacion : Hospital.habitaciones) {
            if (habitacion.getNumero()==numero && habitacion.getCategoria()==categoriaHabitacion){
                System.out.println("La habitación ya existe");
                return;
            }

        }
        Habitacion habitacion= new Habitacion(numero, categoriaHabitacion, ocupada, null, dias);
        System.out.println("Ya se ha construido la nueva habitación");
        hospital.getHabitaciones().add(habitacion);
        System.out.println("-Número de ID de la habitación: "+ habitacion.getNumero() +" "+ "-Categoria de la habitación: "+ habitacion.getCategoria());
    }

}
