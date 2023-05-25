/* Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
y Santiago Arboleda Acevedo */
package uiMain.funcionalidades;

import gestorAplicacion.administracion.CategoriaHabitacion;
import gestorAplicacion.servicios.Habitacion;
import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.personas.Paciente;

import java.util.ArrayList;
import java.util.Scanner;



public class AsignarHabitacion {
    static Scanner sc = new Scanner(System.in);

    public static void asignarHabitacion(Hospital hospital) {

        //Se pide la cedula del paciente y devuelve el paciente encontrado
        System.out.println("Ingrese el número de identificación del paciente:");
        int cedula = sc.nextInt();
        sc.nextLine();
        Paciente paciente = hospital.buscarPaciente(cedula);

        if (paciente != null) { //se comprueba que el paciente sea diferete de null para poder continuar
            if (paciente.getHabitacionAsignada() == null) { //se comprueba que el atributo sea null para poder seleccionar una habitacion
                //se declaran unos atributos necesarios para la funcionalidad
                Habitacion habitacion = null;
                Habitacion habitacionSeleccionada = null;
                Habitacion otraHabitacion = null;
                ArrayList<Habitacion> habitacionesDisponibles = new ArrayList<>();
                ArrayList<Habitacion> otraHabitacionDisponibles = new ArrayList<>();
                //del atributo tipoEps se comprueba si es tipo sucidiado para mostrar la categoria de habitaciones disponibles que la eps le permite
                if (paciente.getTipoEps().equals("Subsidiado")) {
                    int eleccion;
                    do { // se emplea un swicth para mostrar las categorias disponibles
                        System.out.println("Elija el tipo de habitacion que desee, recuerde que segun el tipo va el costo del servicio");
                        System.out.println("1. CAMILLA");
                        System.out.println("2. OBSERVACION");
                        System.out.println("3. UCI");
                        System.out.print("Ingrese una opción: ");
                        eleccion = sc.nextInt();
                        sc.nextLine();


                        switch (eleccion) {
                            case 1:
                                paciente.setCategoriaHabitacion(CategoriaHabitacion.CAMILLA);
                                //se envia la categoria que selecciono al atributo correspondiente que este caso es categoriaHabitacion
                                habitacionesDisponibles = Habitacion.BuscarHabitacionDisponible(paciente.getCategoriaHabitacion());
                                //con el atributo anterior se llama el metodo BuscarHabitacionDisponible y se pasa como paramatro el atributo anterior
                                if (habitacionesDisponibles != null) {
                                    //se comprueba si lo que nos retorno el metodo anterior es diferente a null
                                    System.out.println("Habitaciones disponibles de la categoría " + paciente.getCategoriaHabitacion() + ":");
                                    // se recorre la lista que nos retorno y muestran en la consola una lista numerada de las habitaciones disponibles, donde cada habitación se identifica por su índice y número
                                    for (int i = 0; i < habitacionesDisponibles.size(); i++) {
                                        Habitacion habi = habitacionesDisponibles.get(i);
                                        System.out.println(i + ". " + "Número ID: "+ habi.getNumero());
                                    }
                                    sc = new Scanner(System.in);
                                    int opcion;
                                    //se pide que seleccione la habitación que mas le guste y que cumple el rango establecido
                                    do {
                                        System.out.println("Seleccione la habitación deseada (ingrese un número del 0 al " +
                                                (habitacionesDisponibles.size() - 1) + "):");
                                        opcion = sc.nextInt();
                                    } while (opcion < 0 || opcion >= habitacionesDisponibles.size());


                                    habitacionSeleccionada = habitacionesDisponibles.get(opcion);


                                    // Eliminar la habitación seleccionada de la lista de habitaciones disponibles
                                    habitacionesDisponibles.remove(opcion);

                                    habitacion = habitacionSeleccionada;

                                    break;
                                } else {
                                    habitacion = null;
                                    break;
                                }

                            case 2:
                                paciente.setCategoriaHabitacion(CategoriaHabitacion.OBSERVACION);
                                //se envia la categoria que selecciono al atributo correspondiente que este caso es categoriaHabitacion
                                habitacionesDisponibles = Habitacion.BuscarHabitacionDisponible(paciente.getCategoriaHabitacion());
                                //con el atributo anterior se llama el metodo BuscarHabitacionDisponible y se pasa como paramatro el atributo anterior
                                if (habitacionesDisponibles != null) {
                                    //se comprueba si lo que nos retorno el metodo anterior es diferente a null
                                    System.out.println("Habitaciones disponibles de la categoría " + paciente.getCategoriaHabitacion() + ":");
                                    // se recorre la lista que nos retorno y muestran en la consola una lista numerada de las habitaciones disponibles, donde cada habitación se identifica por su índice y número
                                    for (int i = 0; i < habitacionesDisponibles.size(); i++) {
                                        Habitacion habi = habitacionesDisponibles.get(i);
                                        System.out.println(i + ". " + "Número ID: "+ habi.getNumero());
                                    }
                                    sc = new Scanner(System.in);
                                    int opcion1;
                                    //se pide que seleccione la habitación que mas le guste y que cumple el rango establecido
                                    do {
                                        System.out.println("Seleccione la habitación deseada (ingrese un número del 0 al " +
                                                (habitacionesDisponibles.size() - 1) + "):");
                                        opcion1 = sc.nextInt();
                                    } while (opcion1 < 0 || opcion1 >= habitacionesDisponibles.size());


                                    habitacionSeleccionada = habitacionesDisponibles.get(opcion1);


                                    // Eliminar la habitación seleccionada de la lista de habitaciones disponibles
                                    habitacionesDisponibles.remove(opcion1);

                                    habitacion = habitacionSeleccionada;
                                    break;
                                } else {
                                    habitacion = null;
                                    break;
                                }
                            case 3:
                                paciente.setCategoriaHabitacion(CategoriaHabitacion.UCI);
                                habitacionesDisponibles = Habitacion.BuscarHabitacionDisponible(paciente.getCategoriaHabitacion());
                                if (habitacionesDisponibles != null) {
                                    System.out.println("Habitaciones disponibles de la categoría " + paciente.getCategoriaHabitacion() + ":");
                                    for (int i = 0; i < habitacionesDisponibles.size(); i++) {
                                        Habitacion habi = habitacionesDisponibles.get(i);
                                        System.out.println(i + ". " + "Número ID: "+ habi.getNumero());
                                    }
                                    sc = new Scanner(System.in);
                                    int opcion2;
                                    do {
                                        System.out.println("Seleccione la habitación deseada (ingrese un número del 0 al " +
                                                (habitacionesDisponibles.size() - 1) + "):");
                                        opcion2 = sc.nextInt();
                                    } while (opcion2 < 0 || opcion2 >= habitacionesDisponibles.size());


                                    habitacionSeleccionada = habitacionesDisponibles.get(opcion2);


                                    // Eliminar la habitación seleccionada de la lista de habitaciones disponibles
                                    habitacionesDisponibles.remove(opcion2);

                                    habitacion = habitacionSeleccionada;
                                    break;
                                } else {
                                    habitacion = null;
                                    break;
                                }
                            default:
                                System.out.println("Opción inválida. Por favor, intente de nuevo.");
                                break;
                        }

                    } while (eleccion != 1 && eleccion != 2 && eleccion != 3);

                }//del atributo tipoEps se comprueba si es tipo contributivo para mostrar la categoria de habitaciones disponibles que la eps le permite y se hace lo mismo con las categorias que en sucidiado
                //cabe aclarar que contributivo tiene mas categorias disponibles
                else if (paciente.getTipoEps().equals("Contributivo")) {
                    int eleccion;
                    do {
                        System.out.println("Elija el tipo de habitacion que desee, recuerde que segun el tipo va el costo del servicio");
                        System.out.println("1. INDIVIDUAL");
                        System.out.println("2. DOBLE");
                        System.out.println("3. OBSERVACION");
                        System.out.println("4. UCI");
                        System.out.println("5. UCC");
                        System.out.print("Ingrese una opción: ");
                        eleccion = sc.nextInt();
                        sc.nextLine();


                        switch (eleccion) {
                            case 1:
                                paciente.setCategoriaHabitacion(CategoriaHabitacion.INDIVIDUAL);
                                habitacionesDisponibles = Habitacion.BuscarHabitacionDisponible(paciente.getCategoriaHabitacion());
                                if (habitacionesDisponibles != null) {
                                    System.out.println("Habitaciones disponibles de la categoría " + paciente.getCategoriaHabitacion() + ":");
                                    for (int i = 0; i < habitacionesDisponibles.size(); i++) {
                                        Habitacion habi = habitacionesDisponibles.get(i);
                                        System.out.println(i + ". " + "Número ID: "+ habi.getNumero());
                                    }
                                    sc = new Scanner(System.in);
                                    int opcion;
                                    do {
                                        System.out.println("Seleccione la habitación deseada (ingrese un número del 0 al " +
                                                (habitacionesDisponibles.size() - 1) + "):");
                                        opcion = sc.nextInt();
                                    } while (opcion < 0 || opcion >= habitacionesDisponibles.size());


                                    habitacionSeleccionada = habitacionesDisponibles.get(opcion);


                                    // Eliminar la habitación seleccionada de la lista de habitaciones disponibles
                                    habitacionesDisponibles.remove(opcion);

                                    habitacion = habitacionSeleccionada;
                                    break;
                                } else {
                                    habitacion = null;
                                    break;
                                }
                            case 2:
                                paciente.setCategoriaHabitacion(CategoriaHabitacion.DOBLE);
                                habitacionesDisponibles = Habitacion.BuscarHabitacionDisponible(paciente.getCategoriaHabitacion());
                                if (habitacionesDisponibles != null) {
                                    System.out.println("Habitaciones disponibles de la categoría " + paciente.getCategoriaHabitacion() + ":");
                                    for (int i = 0; i < habitacionesDisponibles.size(); i++) {
                                        Habitacion habi = habitacionesDisponibles.get(i);
                                        System.out.println(i + ". " + "Número ID: "+ habi.getNumero());
                                    }
                                    sc = new Scanner(System.in);
                                    int opcion1;
                                    do {
                                        System.out.println("Seleccione la habitación deseada (ingrese un número del 0 al " +
                                                (habitacionesDisponibles.size() - 1) + "):");
                                        opcion1 = sc.nextInt();
                                    } while (opcion1 < 0 || opcion1 >= habitacionesDisponibles.size());


                                    habitacionSeleccionada = habitacionesDisponibles.get(opcion1);


                                    // Eliminar la habitación seleccionada de la lista de habitaciones disponibles
                                    habitacionesDisponibles.remove(opcion1);

                                    habitacion = habitacionSeleccionada;
                                    break;
                                } else {
                                    habitacion = null;
                                    break;
                                }
                            case 3:
                                paciente.setCategoriaHabitacion(CategoriaHabitacion.OBSERVACION);
                                habitacionesDisponibles = Habitacion.BuscarHabitacionDisponible(paciente.getCategoriaHabitacion());
                                if (habitacionesDisponibles != null) {
                                    System.out.println("Habitaciones disponibles de la categoría " + paciente.getCategoriaHabitacion() + ":");
                                    for (int i = 0; i < habitacionesDisponibles.size(); i++) {
                                        Habitacion habi = habitacionesDisponibles.get(i);
                                        System.out.println(i + ". " + "Número ID: "+ habi.getNumero());
                                    }
                                    sc = new Scanner(System.in);
                                    int opcion2;
                                    do {
                                        System.out.println("Seleccione la habitación deseada (ingrese un número del 0 al " +
                                                (habitacionesDisponibles.size() - 1) + "):");
                                        opcion2 = sc.nextInt();
                                    } while (opcion2 < 0 || opcion2 >= habitacionesDisponibles.size());


                                    habitacionSeleccionada = habitacionesDisponibles.get(opcion2);


                                    // Eliminar la habitación seleccionada de la lista de habitaciones disponibles
                                    habitacionesDisponibles.remove(opcion2);

                                    habitacion = habitacionSeleccionada;
                                    break;
                                } else {
                                    habitacion = null;
                                    break;
                                }
                            case 4:
                                paciente.setCategoriaHabitacion(CategoriaHabitacion.UCI);
                                habitacionesDisponibles = Habitacion.BuscarHabitacionDisponible(paciente.getCategoriaHabitacion());
                                if (habitacionesDisponibles != null) {
                                    System.out.println("Habitaciones disponibles de la categoría " + paciente.getCategoriaHabitacion() + ":");
                                    for (int i = 0; i < habitacionesDisponibles.size(); i++) {
                                        Habitacion habi = habitacionesDisponibles.get(i);
                                        System.out.println(i + ". " + "Número ID: "+ habi.getNumero());
                                    }
                                    sc = new Scanner(System.in);
                                    int opcion3;
                                    do {
                                        System.out.println("Seleccione la habitación deseada (ingrese un número del 0 al " +
                                                (habitacionesDisponibles.size() - 1) + "):");
                                        opcion3 = sc.nextInt();
                                    } while (opcion3 < 0 || opcion3 >= habitacionesDisponibles.size());


                                    habitacionSeleccionada = habitacionesDisponibles.get(opcion3);


                                    // Eliminar la habitación seleccionada de la lista de habitaciones disponibles
                                    habitacionesDisponibles.remove(opcion3);

                                    habitacion = habitacionSeleccionada;
                                    break;
                                } else {
                                    habitacion = null;
                                    break;
                                }
                            case 5:
                                paciente.setCategoriaHabitacion(CategoriaHabitacion.UCC);
                                habitacionesDisponibles = Habitacion.BuscarHabitacionDisponible(paciente.getCategoriaHabitacion());
                                if (habitacionesDisponibles != null) {
                                    System.out.println("Habitaciones disponibles de la categoría " + paciente.getCategoriaHabitacion() + ":");
                                    for (int i = 0; i < habitacionesDisponibles.size(); i++) {
                                        Habitacion habi = habitacionesDisponibles.get(i);
                                        System.out.println(i + ". " + "Número ID: "+ habi.getNumero());
                                    }
                                    sc = new Scanner(System.in);
                                    int opcion4;
                                    do {
                                        System.out.println("Seleccione la habitación deseada (ingrese un número del 0 al " +
                                                (habitacionesDisponibles.size() - 1) + "):");
                                        opcion4 = sc.nextInt();
                                    } while (opcion4 < 0 || opcion4 >= habitacionesDisponibles.size());


                                    habitacionSeleccionada = habitacionesDisponibles.get(opcion4);


                                    // Eliminar la habitación seleccionada de la lista de habitaciones disponibles
                                    habitacionesDisponibles.remove(opcion4);

                                    habitacion = habitacionSeleccionada;
                                    break;
                                } else {
                                    habitacion = null;
                                    break;
                                }
                            default:
                                System.out.println("Opción inválida. Por favor, intente de nuevo.");
                                break;
                        }
                    } while (eleccion != 1 && eleccion != 2 && eleccion != 3 && eleccion != 4 && eleccion != 5);

                } else { //si no cumple ninguno de los parametros anteriores se hace lo mismo pero con menos categorias disponibles
                    int eleccion;
                    do {

                        System.out.println("Elija el tipo de habitacion que desee, recuerde que segun el tipo va el costo del servicio");
                        System.out.println("1. CAMILLA");
                        System.out.println("2. UCI");
                        System.out.print("Ingrese una opción: ");
                        eleccion = sc.nextInt();
                        sc.nextLine();


                        switch (eleccion) {
                            case 1:
                                paciente.setCategoriaHabitacion(CategoriaHabitacion.CAMILLA);
                                habitacionesDisponibles = Habitacion.BuscarHabitacionDisponible(paciente.getCategoriaHabitacion());
                                if (habitacionesDisponibles != null) {
                                    System.out.println("Habitaciones disponibles de la categoría " + paciente.getCategoriaHabitacion() + ":");
                                    for (int i = 0; i < habitacionesDisponibles.size(); i++) {
                                        Habitacion habi = habitacionesDisponibles.get(i);
                                        System.out.println(i + ". " + "Número ID: "+ habi.getNumero());
                                    }
                                    sc = new Scanner(System.in);
                                    int opcion;
                                    do {
                                        System.out.println("Seleccione la habitación deseada (ingrese un número del 0 al " +
                                                (habitacionesDisponibles.size() - 1) + "):");
                                        opcion = sc.nextInt();
                                    } while (opcion < 0 || opcion >= habitacionesDisponibles.size());


                                    habitacionSeleccionada = habitacionesDisponibles.get(opcion);

                                    // Eliminar la habitación seleccionada de la lista de habitaciones disponibles
                                    habitacionesDisponibles.remove(opcion);

                                    habitacion = habitacionSeleccionada;
                                    break;
                                } else {
                                    habitacion = null;
                                    break;
                                }
                            case 2:
                                paciente.setCategoriaHabitacion(CategoriaHabitacion.UCI);
                                habitacionesDisponibles = Habitacion.BuscarHabitacionDisponible(paciente.getCategoriaHabitacion());
                                if (habitacionesDisponibles != null) {
                                    System.out.println("Habitaciones disponibles de la categoría " + paciente.getCategoriaHabitacion() + ":");
                                    for (int i = 0; i < habitacionesDisponibles.size(); i++) {
                                        Habitacion habi = habitacionesDisponibles.get(i);
                                        System.out.println(i + ". " + "Número ID: "+ habi.getNumero());
                                    }
                                    sc = new Scanner(System.in);
                                    int opcion1;
                                    do {
                                        System.out.println("Seleccione la habitación deseada (ingrese un número del 0 al " +
                                                (habitacionesDisponibles.size() - 1) + "):");
                                        opcion1 = sc.nextInt();
                                    } while (opcion1 < 0 || opcion1 >= habitacionesDisponibles.size());


                                    habitacionSeleccionada = habitacionesDisponibles.get(opcion1);

                                    // Eliminar la habitación seleccionada de la lista de habitaciones disponibles
                                    habitacionesDisponibles.remove(opcion1);

                                    habitacion = habitacionSeleccionada;
                                    break;
                                } else {
                                    habitacion = null;
                                    break;
                                }

                            default:
                                System.out.println("Opción inválida. Por favor, intente de nuevo.");
                                break;
                        }
                    } while (eleccion != 1 && eleccion != 2);
                }


                if (habitacion != null) { //se comprueba que habitacion sea diferente a null
                    habitacion.setOcupada(true);
                    habitacion.setPaciente(paciente);
                    int index = -1;
                    for (int i = 0; i < hospital.habitaciones.size(); i++) {
                        if (hospital.habitaciones.get(i).equals(habitacion)) {
                            index = i;
                            break;
                        }
                    }
                    if (index != -1) {
                        hospital.habitaciones.set(index, habitacion);
                    }
                    paciente.setHabitacionAsignada(habitacion);
                    //se asigna la habitacion al paciente
                    sc = new Scanner(System.in);
                    System.out.println("Ingrese el número estimado de dias para la estadia en la habitacion:");
                    //se pide los dias estimados de la estadia en la habitacion
                    int dias = sc.nextInt();
                    sc.nextLine();
                    paciente.getHabitacionAsignada().setDias(dias);
                    System.out.println("\nDatos de la habitacion del Paciente: ");
                    System.out.println("Número de ID de la habitación: " + habitacion.getNumero());
                    System.out.println("Categoria de la habitación: " + habitacion.getCategoria());
                    System.out.println("Cedula del Paciente: " + paciente.getCedula());
                    System.out.println("Nombre del Paciente: "+ paciente.getNombre());

                } else { // si la habitacion es null se imprime una ocion al usuario de escoger si desea cambiar de categoria a una inferior
                    System.out.println("No hay habitaciones disponibles de la categoría " + paciente.getCategoriaHabitacion());
                    System.out.println("¿Desea asignar una habitación de otra categoría inferior? (s/n)");
                    sc = new Scanner(System.in);
                    String respuesta = sc.nextLine();
                    if (respuesta.equalsIgnoreCase("s")) {
                        //se ejecuta un metodo para cambiar de caterioria y se buscan las habitaciones disponibles de esa categoria
                        CategoriaHabitacion otraCategoria = Habitacion.BuscarOtraCategoria(paciente.getCategoriaHabitacion());
                        otraHabitacionDisponibles = Habitacion.BuscarHabitacionDisponible(otraCategoria);
                        if (otraHabitacionDisponibles != null) {//se comprueba que la nueva categoria tenga habitaciones y se hace lo mismo al momento de elejir la habitacion q
                            System.out.println("Habitaciones disponibles de la categoría inferor:");
                            for (int i = 0; i < otraHabitacionDisponibles.size(); i++) {
                                Habitacion habi = otraHabitacionDisponibles.get(i);
                                System.out.println(i + ". " + "Número ID: "+ habi.getNumero());
                            }
                            sc = new Scanner(System.in);
                            int opcion;
                            do {
                                System.out.println("Seleccione la habitación deseada (ingrese un número del 0 al " +
                                        (otraHabitacionDisponibles.size() - 1) + "):");
                                opcion = sc.nextInt();
                            } while (opcion < 0 || opcion >= otraHabitacionDisponibles.size());
                            habitacionSeleccionada = otraHabitacionDisponibles.get(opcion);

                            // Eliminar la habitación seleccionada de la lista de habitaciones disponibles
                            otraHabitacionDisponibles.remove(opcion);

                            otraHabitacion = habitacionSeleccionada;
                        } else {
                            otraHabitacion = null;
                        }
                        if (otraHabitacion != null) {//se vuelve a comprobar que la otraHabitacion sea diferente a null
                            otraHabitacion.setOcupada(true);
                            int index = -1;
                            for (int i = 0; i < hospital.habitaciones.size(); i++) {
                                if (hospital.habitaciones.get(i).equals(otraHabitacion)) {
                                    index = i;
                                    break;
                                }
                            }
                            if (index != -1) {
                                hospital.habitaciones.set(index, otraHabitacion);
                            }
                            otraHabitacion.setPaciente(paciente);
                            paciente.setHabitacionAsignada(otraHabitacion);
                            sc = new Scanner(System.in);
                            System.out.println("\"Ingrese el número estimado de dias para la estadia en la habitacion:");
                            int dias = sc.nextInt();
                            sc.nextLine();
                            paciente.getHabitacionAsignada().setDias(dias);
                            System.out.println("\nDatos de la habitacion del Paciente: ");
                            System.out.println("Número de ID de la habitación: " + otraHabitacion.getNumero());
                            System.out.println("Categoria de la habitación: " + otraHabitacion.getCategoria());
                            System.out.println("Cedula del Paciente: " + paciente.getCedula());
                            System.out.println("Nombre del Paciente: "+ paciente.getNombre());
                            //se asigna la habitacion al paciente
                        } else
                        {//si no se cumple se imprime que no hay habitaciones disponibles
                            System.out.println("No hay habitaciones disponibles de ninguna categoría");
                        }
                    }
                }


            } else
            {//si no se cumple que la habitacionAsignada es null se imprime que ya tiene registrada una habitacion
                System.out.println("El paciente ya tiene una habitacion registrada");
            }




        } else
        {//si al momento de comprobar si la cedula del paciente se encuentra en la lista de pacientes registardos y no lo esta imprime que se devuelva y se registre
            System.out.println("El paciente no se encuentra registrado. Por favor, regístrese en el hospital para poder atenderlo.");

        }

    }


}



