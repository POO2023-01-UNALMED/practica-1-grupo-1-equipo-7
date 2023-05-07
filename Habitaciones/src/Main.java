import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("----- Menú -----");
            System.out.println("1. Asignar habitación a un paciente existente");
            System.out.println("2. Salir");
            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:
                    asignarHabitacion();
                    break;

                case 2:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, intente de nuevo.");
                    break;
            }
            System.out.println();
        } while (opcion != 2);
    }

    public static void asignarHabitacion() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el número de identificación del paciente:");
        int cedula = scanner.nextInt();
        scanner.nextLine();
        Paciente paciente = Hospital.buscarPaciente(cedula);

        if (paciente != null) {
            Habitacion habitacion = null;
            if(paciente.getTipoEps().equals("subsidiado")) {
                int eleccion;
                    System.out.println("Elija el tipo de habitacion que desee, recuerde que segun el tipo va el costo del servicio");
                    System.out.println("1. CAMILLA");
                    System.out.println("2. OBSERVACION");
                    System.out.println("3. UCI");
                    System.out.print("Ingrese una opción: ");
                    eleccion = scanner.nextInt();
                    scanner.nextLine();


                    switch (eleccion) {
                        case 1:
                            paciente.setCategoriaHabitacion(CategoriaHabitacion.CAMILLA);
                            habitacion = Habitacion.BuscarHabitacionDisponible(paciente.getCategoriaHabitacion());
                            break;

                        case 2:
                            paciente.setCategoriaHabitacion(CategoriaHabitacion.OBSERVACION);
                            habitacion = Habitacion.BuscarHabitacionDisponible(paciente.getCategoriaHabitacion());
                            break;
                        case 3:
                            paciente.setCategoriaHabitacion(CategoriaHabitacion.UCI);
                            habitacion = Habitacion.BuscarHabitacionDisponible(paciente.getCategoriaHabitacion());
                            break;
                        default:
                            System.out.println("Opción inválida. Por favor, intente de nuevo.");
                            break;
                    }



            }
            else if (paciente.getTipoEps().equals("contributivo")) {
                int eleccion;

                    System.out.println("Elija el tipo de habitacion que desee, recuerde que segun el tipo va el costo del servicio");
                    System.out.println("1. INDIVIDUAL");
                    System.out.println("2. DOBLE");
                    System.out.println("3. OBSERVACION");
                    System.out.println("4. UCI");
                    System.out.println("5. UCC");
                    System.out.print("Ingrese una opción: ");
                    eleccion = scanner.nextInt();
                    scanner.nextLine();


                    switch (eleccion) {
                        case 1:
                            paciente.setCategoriaHabitacion(CategoriaHabitacion.INDIVIDUAL);
                            habitacion = Habitacion.BuscarHabitacionDisponible(paciente.getCategoriaHabitacion());
                            break;
                        case 2:
                            paciente.setCategoriaHabitacion(CategoriaHabitacion.DOBLE);
                            habitacion = Habitacion.BuscarHabitacionDisponible(paciente.getCategoriaHabitacion());
                            break;
                        case 3:
                            paciente.setCategoriaHabitacion(CategoriaHabitacion.OBSERVACION);
                            habitacion = Habitacion.BuscarHabitacionDisponible(paciente.getCategoriaHabitacion());
                            break;
                        case 4:
                            paciente.setCategoriaHabitacion(CategoriaHabitacion.UCI);
                            habitacion = Habitacion.BuscarHabitacionDisponible(paciente.getCategoriaHabitacion());
                            break;
                        case 5:
                            paciente.setCategoriaHabitacion(CategoriaHabitacion.UCC);
                            habitacion = Habitacion.BuscarHabitacionDisponible(paciente.getCategoriaHabitacion());
                            break;
                        default:
                            System.out.println("Opción inválida. Por favor, intente de nuevo.");
                            break;
                    }


            }

            else {
                int eleccion;

                System.out.println("Elija el tipo de habitacion que desee, recuerde que segun el tipo va el costo del servicio");
                System.out.println("1. CAMILLA");
                System.out.println("2. UCI");
                System.out.print("Ingrese una opción: ");
                eleccion = scanner.nextInt();
                scanner.nextLine();


                switch (eleccion) {
                    case 1:
                        paciente.setCategoriaHabitacion(CategoriaHabitacion.CAMILLA);
                        habitacion = Habitacion.BuscarHabitacionDisponible(paciente.getCategoriaHabitacion());
                        break;

                    case 2:
                        paciente.setCategoriaHabitacion(CategoriaHabitacion.UCI);
                        habitacion = Habitacion.BuscarHabitacionDisponible(paciente.getCategoriaHabitacion());
                        break;

                    default:
                        System.out.println("Opción inválida. Por favor, intente de nuevo.");
                        break;
                }
            }

            if (habitacion != null) {
                    habitacion.setOcupada(true);
                    paciente.setHabitacionAsignada(habitacion);
                    System.out.println("La habitación " + habitacion.getNumero() + " ha sido asignada al paciente " + paciente.getNombre());

            }
            else {
                    System.out.println("No hay habitaciones disponibles de la categoría " + paciente.getCategoriaHabitacion());
                    System.out.println("¿Desea asignar una habitación de otra categoría? (s/n)");
                    Scanner scanner1 = new Scanner(System.in);
                    String respuesta = scanner.nextLine();
                    if (respuesta.equalsIgnoreCase("s")) {
                        CategoriaHabitacion otraCategoria = Habitacion.BuscarOtraCategoria(paciente.getCategoriaHabitacion());
                        Habitacion otraHabitacion = Habitacion.BuscarHabitacionDisponible(otraCategoria);
                        if (otraHabitacion != null) {
                            otraHabitacion.setOcupada(true);
                            paciente.setHabitacionAsignada(otraHabitacion);
                            System.out.println("La habitación " + otraHabitacion.getNumero() + " ha sido asignada al paciente " + paciente.getNombre());
                        }
                        else {
                            System.out.println("No hay habitaciones disponibles de ninguna categoría");

                        }
                    }
            }






        }


    }
    //public void calcularPrecio(){
    //  System.out.println("Ingrese los dias que durara el servicio:");
    //  int dias = scanner.nextInt();
    //  scanner.nextLine();
    //  float precio;
    //  precio = Paciente.calcularPrecio(dias);
    //}
}