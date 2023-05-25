/* Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
y Santiago Arboleda Acevedo */
package uiMain.gestion.gestionVacunas;

import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.administracion.Vacuna;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class RegistrarVacuna {

    public static boolean verificarExistenciaVacuna(String nombre,Hospital hospital){
        boolean valor= false;
        
        for(int i=1; i<=hospital.getListaVacunas().size();i++){
            if (nombre.equals(hospital.getListaVacunas().get(i - 1).getNombre())){
                valor=true;
                break;
            }
            else{
                valor=false;
            }
        }
        return valor;
    }
    
    public static void registrarVacuna (Hospital hospital){

        Scanner sc= new Scanner(System.in);

        System.out.println("A continuación ingrese la información de la nueva vacuna: ");
        System.out.println("Ingrese el nombre de la vacuna (Recuerda empezar con mayúscula) : ");
        String nombre= sc.nextLine();

        if(RegistrarVacuna.verificarExistenciaVacuna(nombre,hospital)){
            System.out.println("Esta vacuna ya está registrada");
            return;
        }
        System.out.println("Ingrese el tipo de la vacuna (Obligatoria, No obligatoria): ");
        String tipo = sc.nextLine();

        ArrayList<String> tipoEps= new ArrayList<String>();
        boolean respuesta;

        do {
            System.out.println("Ingrese el tipo de Eps al que va a estar disponible la vacuna (Subsidiado, Contributivo o Particular) : ");
            String eps;
            eps= sc.next();

            tipoEps.add(eps);

            while(true){
                System.out.println("¿Desea agregar otra Eps? [s/n] ");
                String letra =sc.next();
                if (Objects.equals(letra, "s") || Objects.equals(letra, "n")){
                    respuesta= letra.equals("s");
                    break;
                }else{
                    System.out.println("Opción inválida");
                }
            }
        }while(respuesta);

        System.out.println("Ingrese el precio de la vacuna: ");
        double precio = sc.nextDouble();

        Vacuna vacunaNueva= new Vacuna(tipo, nombre, tipoEps, precio );
        System.out.println("¡La vacuna ha sido registrada con éxito!");

        hospital.getListaVacunas().add(vacunaNueva);

        System.out.println("\nInformación general de la nueva vacuna registrada: ");
        System.out.println("Vacuna: "+vacunaNueva.getNombre());
        System.out.println("Tipo: "+vacunaNueva.getTipo());
        System.out.println("Precio: "+ vacunaNueva.getPrecio());

        sc.nextLine();

    }
    
}
