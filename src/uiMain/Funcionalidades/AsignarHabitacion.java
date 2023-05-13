package uiMain.Funcionalidades;

import gestorAplicacion.administracion.CategoriaHabitacion;
import gestorAplicacion.administracion.Habitacion;
import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.personas.Paciente;

import java.util.ArrayList;
import java.util.Scanner;

import static uiMain.Funcionalidades.FormulaMedica.sc;

public class AsignarHabitacion {
    Scanner scanner = new Scanner(System.in);
        public static void asignarHabitacion(Hospital hospital) {

            System.out.println("Ingrese el número de identificación del paciente:");
            int cedula = sc.nextInt();
            sc.nextLine();
            Paciente paciente = hospital.buscarPaciente(cedula);

            if (paciente != null) {
                Habitacion habitacion = null;
                Habitacion habitacionSeleccionada=null;
                Habitacion otraHabitacion=null;
                ArrayList<Habitacion> habitacionesDisponibles = new ArrayList<>();
                ArrayList<Habitacion> otraHabitacionDisponibles = new ArrayList<>();
                if(paciente.getTipoEps().equals("subsidiado")) {
                    int eleccion;
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
                            habitacionesDisponibles = Habitacion.BuscarHabitacionDisponible(paciente.getCategoriaHabitacion());
                            if (habitacionesDisponibles!=null) {
                                System.out.println("Habitaciones disponibles de la categoría " + paciente.getCategoriaHabitacion() + ":");
                                for (int i = 0; i < habitacionesDisponibles.size(); i++) {
                                    Habitacion habi = habitacionesDisponibles.get(i);
                                    System.out.println(i + ". " + habi.getNumero());
                                }
                                sc= new Scanner(System.in);
                                int opcion;
                                do {
                                    System.out.println("Seleccione la habitación deseada (ingrese un número del 0 al " +
                                            (habitacionesDisponibles.size() - 1) + "):");
                                    opcion = sc.nextInt();
                                } while (opcion < 0 || opcion >= habitacionesDisponibles.size());

                                // Actualizar la habitación seleccionada en la lista del hospital
                                habitacionSeleccionada = habitacionesDisponibles.get(opcion);
                                habitacionSeleccionada.setOcupada(true);
                                int index = -1;
                                for (int i = 0; i < hospital.habitaciones.size(); i++) {
                                    if (hospital.habitaciones.get(i).equals(habitacionSeleccionada)) {
                                        index = i;
                                        break;
                                    }
                                }
                                if (index != -1) {
                                    hospital.habitaciones.set(index, habitacionSeleccionada);
                                }

                                // Eliminar la habitación seleccionada de la lista de habitaciones disponibles
                                habitacionesDisponibles.remove(opcion);

                                habitacion = habitacionSeleccionada;
                                break;
                            }
                            else{
                                habitacion = null;
                                break;
                            }

                        case 2:
                            paciente.setCategoriaHabitacion(CategoriaHabitacion.OBSERVACION);
                            habitacionesDisponibles = Habitacion.BuscarHabitacionDisponible(paciente.getCategoriaHabitacion());
                            if (habitacionesDisponibles!=null) {
                                System.out.println("Habitaciones disponibles de la categoría " + paciente.getCategoriaHabitacion() + ":");
                                for (int i = 0; i < habitacionesDisponibles.size(); i++) {
                                    Habitacion habi = habitacionesDisponibles.get(i);
                                    System.out.println(i + ". " + habi.getNumero());
                                }
                                sc = new Scanner(System.in);
                                int opcion1;
                                do {
                                    System.out.println("Seleccione la habitación deseada (ingrese un número del 0 al " +
                                            (habitacionesDisponibles.size() - 1) + "):");
                                    opcion1 = sc.nextInt();
                                } while (opcion1 < 0 || opcion1 >= habitacionesDisponibles.size());

                                // Actualizar la habitación seleccionada en la lista del hospital
                                habitacionSeleccionada = habitacionesDisponibles.get(opcion1);
                                habitacionSeleccionada.setOcupada(true);
                                int index = -1;
                                for (int i = 0; i < hospital.habitaciones.size(); i++) {
                                    if (hospital.habitaciones.get(i).equals(habitacionSeleccionada)) {
                                        index = i;
                                        break;
                                    }
                                }
                                if (index != -1) {
                                    hospital.habitaciones.set(index, habitacionSeleccionada);
                                }

                                // Eliminar la habitación seleccionada de la lista de habitaciones disponibles
                                habitacionesDisponibles.remove(opcion1);

                                habitacion = habitacionSeleccionada;
                                break;
                            }
                            else{
                                habitacion = null;
                                break;
                            }
                        case 3:
                            paciente.setCategoriaHabitacion(CategoriaHabitacion.UCI);
                            habitacionesDisponibles = Habitacion.BuscarHabitacionDisponible(paciente.getCategoriaHabitacion());
                            if (habitacionesDisponibles!=null) {
                                System.out.println("Habitaciones disponibles de la categoría " + paciente.getCategoriaHabitacion() + ":");
                                for (int i = 0; i < habitacionesDisponibles.size(); i++) {
                                    Habitacion habi = habitacionesDisponibles.get(i);
                                    System.out.println(i + ". " + habi.getNumero());
                                }
                                sc = new Scanner(System.in);
                                int opcion2;
                                do {
                                    System.out.println("Seleccione la habitación deseada (ingrese un número del 0 al " +
                                            (habitacionesDisponibles.size() - 1) + "):");
                                    opcion2 = sc.nextInt();
                                } while (opcion2 < 0 || opcion2 >= habitacionesDisponibles.size());

                                // Actualizar la habitación seleccionada en la lista del hospital
                                habitacionSeleccionada = habitacionesDisponibles.get(opcion2);
                                habitacionSeleccionada.setOcupada(true);
                                int index = -1;
                                for (int i = 0; i < hospital.habitaciones.size(); i++) {
                                    if (hospital.habitaciones.get(i).equals(habitacionSeleccionada)) {
                                        index = i;
                                        break;
                                    }
                                }
                                if (index != -1) {
                                    hospital.habitaciones.set(index, habitacionSeleccionada);
                                }

                                // Eliminar la habitación seleccionada de la lista de habitaciones disponibles
                                habitacionesDisponibles.remove(opcion2);

                                habitacion = habitacionSeleccionada;
                                break;
                            }
                            else{
                                habitacion = null;
                                break;
                            }
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
                    eleccion = sc.nextInt();
                    sc.nextLine();


                    switch (eleccion) {
                        case 1:
                            paciente.setCategoriaHabitacion(CategoriaHabitacion.INDIVIDUAL);
                            habitacionesDisponibles = Habitacion.BuscarHabitacionDisponible(paciente.getCategoriaHabitacion());
                            if (habitacionesDisponibles!=null) {
                                System.out.println("Habitaciones disponibles de la categoría " + paciente.getCategoriaHabitacion() + ":");
                                for (int i = 0; i < habitacionesDisponibles.size(); i++) {
                                    Habitacion habi = habitacionesDisponibles.get(i);
                                    System.out.println(i + ". " + habi.getNumero());
                                }
                                sc = new Scanner(System.in);
                                int opcion;
                                do {
                                    System.out.println("Seleccione la habitación deseada (ingrese un número del 0 al " +
                                            (habitacionesDisponibles.size() - 1) + "):");
                                    opcion = sc.nextInt();
                                } while (opcion < 0 || opcion >= habitacionesDisponibles.size());

                                // Actualizar la habitación seleccionada en la lista del hospital
                                habitacionSeleccionada = habitacionesDisponibles.get(opcion);
                                habitacionSeleccionada.setOcupada(true);
                                int index = -1;
                                for (int i = 0; i < hospital.habitaciones.size(); i++) {
                                    if (hospital.habitaciones.get(i).equals(habitacionSeleccionada)) {
                                        index = i;
                                        break;
                                    }
                                }
                                if (index != -1) {
                                    hospital.habitaciones.set(index, habitacionSeleccionada);
                                }

                                // Eliminar la habitación seleccionada de la lista de habitaciones disponibles
                                habitacionesDisponibles.remove(opcion);

                                habitacion= habitacionSeleccionada;
                                break;
                            }
                            else{
                                habitacion = null;
                                break;
                            }
                        case 2:
                            paciente.setCategoriaHabitacion(CategoriaHabitacion.DOBLE);
                            habitacionesDisponibles = Habitacion.BuscarHabitacionDisponible(paciente.getCategoriaHabitacion());
                            if (habitacionesDisponibles!=null) {
                                System.out.println("Habitaciones disponibles de la categoría " + paciente.getCategoriaHabitacion() + ":");
                                for (int i = 0; i < habitacionesDisponibles.size(); i++) {
                                    Habitacion habi = habitacionesDisponibles.get(i);
                                    System.out.println(i + ". " + habi.getNumero());
                                }
                                sc = new Scanner(System.in);
                                int opcion1;
                                do {
                                    System.out.println("Seleccione la habitación deseada (ingrese un número del 0 al " +
                                            (habitacionesDisponibles.size() - 1) + "):");
                                    opcion1 = sc.nextInt();
                                } while (opcion1 < 0 || opcion1 >= habitacionesDisponibles.size());

                                // Actualizar la habitación seleccionada en la lista del hospital
                                habitacionSeleccionada = habitacionesDisponibles.get(opcion1);
                                habitacionSeleccionada.setOcupada(true);
                                int index = -1;
                                for (int i = 0; i < hospital.habitaciones.size(); i++) {
                                    if (hospital.habitaciones.get(i).equals(habitacionSeleccionada)) {
                                        index = i;
                                        break;
                                    }
                                }
                                if (index != -1) {
                                    hospital.habitaciones.set(index, habitacionSeleccionada);
                                }

                                // Eliminar la habitación seleccionada de la lista de habitaciones disponibles
                                habitacionesDisponibles.remove(opcion1);

                                habitacion= habitacionSeleccionada;
                                break;
                            }
                            else{
                                habitacion = null;
                                break;
                            }
                        case 3:
                            paciente.setCategoriaHabitacion(CategoriaHabitacion.OBSERVACION);
                            habitacionesDisponibles = Habitacion.BuscarHabitacionDisponible(paciente.getCategoriaHabitacion());
                            if (habitacionesDisponibles!=null) {
                                System.out.println("Habitaciones disponibles de la categoría " + paciente.getCategoriaHabitacion() + ":");
                                for (int i = 0; i < habitacionesDisponibles.size(); i++) {
                                    Habitacion habi = habitacionesDisponibles.get(i);
                                    System.out.println(i + ". " + habi.getNumero());
                                }
                                sc = new Scanner(System.in);
                                int opcion2;
                                do {
                                    System.out.println("Seleccione la habitación deseada (ingrese un número del 0 al " +
                                            (habitacionesDisponibles.size() - 1) + "):");
                                    opcion2 = sc.nextInt();
                                } while (opcion2 < 0 || opcion2 >= habitacionesDisponibles.size());

                                // Actualizar la habitación seleccionada en la lista del hospital
                                habitacionSeleccionada = habitacionesDisponibles.get(opcion2);
                                habitacionSeleccionada.setOcupada(true);
                                int index = -1;
                                for (int i = 0; i < hospital.habitaciones.size(); i++) {
                                    if (hospital.habitaciones.get(i).equals(habitacionSeleccionada)) {
                                        index = i;
                                        break;
                                    }
                                }
                                if (index != -1) {
                                    hospital.habitaciones.set(index, habitacionSeleccionada);
                                }

                                // Eliminar la habitación seleccionada de la lista de habitaciones disponibles
                                habitacionesDisponibles.remove(opcion2);

                                habitacion= habitacionSeleccionada;
                                break;
                            }
                            else{
                                habitacion = null;
                                break;
                            }
                        case 4:
                            paciente.setCategoriaHabitacion(CategoriaHabitacion.UCI);
                            habitacionesDisponibles = Habitacion.BuscarHabitacionDisponible(paciente.getCategoriaHabitacion());
                            if (habitacionesDisponibles!=null) {
                                System.out.println("Habitaciones disponibles de la categoría " + paciente.getCategoriaHabitacion() + ":");
                                for (int i = 0; i < habitacionesDisponibles.size(); i++) {
                                    Habitacion habi = habitacionesDisponibles.get(i);
                                    System.out.println(i + ". " + habi.getNumero());
                                }
                                sc = new Scanner(System.in);
                                int opcion3;
                                do {
                                    System.out.println("Seleccione la habitación deseada (ingrese un número del 0 al " +
                                            (habitacionesDisponibles.size() - 1) + "):");
                                    opcion3 = sc.nextInt();
                                } while (opcion3 < 0 || opcion3 >= habitacionesDisponibles.size());

                                // Actualizar la habitación seleccionada en la lista del hospital
                                habitacionSeleccionada = habitacionesDisponibles.get(opcion3);
                                habitacionSeleccionada.setOcupada(true);
                                int index = -1;
                                for (int i = 0; i < hospital.habitaciones.size(); i++) {
                                    if (hospital.habitaciones.get(i).equals(habitacionSeleccionada)) {
                                        index = i;
                                        break;
                                    }
                                }
                                if (index != -1) {
                                    hospital.habitaciones.set(index, habitacionSeleccionada);
                                }

                                // Eliminar la habitación seleccionada de la lista de habitaciones disponibles
                                habitacionesDisponibles.remove(opcion3);

                                habitacion= habitacionSeleccionada;
                                break;
                            }
                            else{
                                habitacion = null;
                                break;
                            }
                        case 5:
                            paciente.setCategoriaHabitacion(CategoriaHabitacion.UCC);
                            habitacionesDisponibles = Habitacion.BuscarHabitacionDisponible(paciente.getCategoriaHabitacion());
                            if (habitacionesDisponibles!=null) {
                                System.out.println("Habitaciones disponibles de la categoría " + paciente.getCategoriaHabitacion() + ":");
                                for (int i = 0; i < habitacionesDisponibles.size(); i++) {
                                    Habitacion habi = habitacionesDisponibles.get(i);
                                    System.out.println(i + ". " + habi.getNumero());
                                }
                                sc = new Scanner(System.in);
                                int opcion4;
                                do {
                                    System.out.println("Seleccione la habitación deseada (ingrese un número del 0 al " +
                                            (habitacionesDisponibles.size() - 1) + "):");
                                    opcion4 = sc.nextInt();
                                } while (opcion4 < 0 || opcion4 >= habitacionesDisponibles.size());

                                // Actualizar la habitación seleccionada en la lista del hospital
                                habitacionSeleccionada = habitacionesDisponibles.get(opcion4);
                                habitacionSeleccionada.setOcupada(true);
                                int index = -1;
                                for (int i = 0; i < hospital.habitaciones.size(); i++) {
                                    if (hospital.habitaciones.get(i).equals(habitacionSeleccionada)) {
                                        index = i;
                                        break;
                                    }
                                }
                                if (index != -1) {
                                    hospital.habitaciones.set(index, habitacionSeleccionada);
                                }

                                // Eliminar la habitación seleccionada de la lista de habitaciones disponibles
                                habitacionesDisponibles.remove(opcion4);

                                habitacion= habitacionSeleccionada;
                                break;
                            }
                            else{
                                habitacion = null;
                                break;
                            }
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
                    eleccion = sc.nextInt();
                    sc.nextLine();


                    switch (eleccion) {
                        case 1:
                            paciente.setCategoriaHabitacion(CategoriaHabitacion.CAMILLA);
                            habitacionesDisponibles = Habitacion.BuscarHabitacionDisponible(paciente.getCategoriaHabitacion());
                            if (habitacionesDisponibles!=null) {
                                System.out.println("Habitaciones disponibles de la categoría " + paciente.getCategoriaHabitacion() + ":");
                                for (int i = 0; i < habitacionesDisponibles.size(); i++) {
                                    Habitacion habi = habitacionesDisponibles.get(i);
                                    System.out.println(i + ". " + habi.getNumero());
                                }
                                sc = new Scanner(System.in);
                                int opcion;
                                do {
                                    System.out.println("Seleccione la habitación deseada (ingrese un número del 0 al " +
                                            (habitacionesDisponibles.size() - 1) + "):");
                                    opcion = sc.nextInt();
                                } while (opcion < 0 || opcion >= habitacionesDisponibles.size());

                                // Actualizar la habitación seleccionada en la lista del hospital
                                habitacionSeleccionada = habitacionesDisponibles.get(opcion);
                                habitacionSeleccionada.setOcupada(true);
                                int index = -1;
                                for (int i = 0; i < hospital.habitaciones.size(); i++) {
                                    if (hospital.habitaciones.get(i).equals(habitacionSeleccionada)) {
                                        index = i;
                                        break;
                                    }
                                }
                                if (index != -1) {
                                    hospital.habitaciones.set(index, habitacionSeleccionada);
                                }

                                // Eliminar la habitación seleccionada de la lista de habitaciones disponibles
                                habitacionesDisponibles.remove(opcion);

                                habitacion = habitacionSeleccionada;
                                break;
                            }
                            else{
                                habitacion = null;
                                break;
                            }
                        case 2:
                            paciente.setCategoriaHabitacion(CategoriaHabitacion.UCI);
                            habitacionesDisponibles = Habitacion.BuscarHabitacionDisponible(paciente.getCategoriaHabitacion());
                            if (habitacionesDisponibles!=null) {
                                System.out.println("Habitaciones disponibles de la categoría " + paciente.getCategoriaHabitacion() + ":");
                                for (int i = 0; i < habitacionesDisponibles.size(); i++) {
                                    Habitacion habi = habitacionesDisponibles.get(i);
                                    System.out.println(i + ". " + habi.getNumero());
                                }
                                sc = new Scanner(System.in);
                                int opcion1;
                                do {
                                    System.out.println("Seleccione la habitación deseada (ingrese un número del 0 al " +
                                            (habitacionesDisponibles.size() - 1) + "):");
                                    opcion1 = sc.nextInt();
                                } while (opcion1 < 0 || opcion1 >= habitacionesDisponibles.size());

                                // Actualizar la habitación seleccionada en la lista del hospital
                                habitacionSeleccionada = habitacionesDisponibles.get(opcion1);
                                habitacionSeleccionada.setOcupada(true);
                                int index = -1;
                                for (int i = 0; i < hospital.habitaciones.size(); i++) {
                                    if (hospital.habitaciones.get(i).equals(habitacionSeleccionada)) {
                                        index = i;
                                        break;
                                    }
                                }
                                if (index != -1) {
                                    hospital.habitaciones.set(index, habitacionSeleccionada);
                                }

                                // Eliminar la habitación seleccionada de la lista de habitaciones disponibles
                                habitacionesDisponibles.remove(opcion1);

                                habitacion= habitacionSeleccionada;
                                break;
                            }
                            else{
                                habitacion = null;
                                break;
                            }

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
                    System.out.println("¿Desea asignar una habitación de otra categoría anterior? (s/n)");
                    Scanner scanner1 = new Scanner(System.in);
                    String respuesta = sc.nextLine();
                    if (respuesta.equalsIgnoreCase("s")) {
                        CategoriaHabitacion otraCategoria = Habitacion.BuscarOtraCategoria(paciente.getCategoriaHabitacion());
                        otraHabitacionDisponibles = Habitacion.BuscarHabitacionDisponible(otraCategoria);
                        if (otraHabitacionDisponibles!=null) {
                            System.out.println("Habitaciones disponibles de la categoría anterior:");
                            for (int i = 0; i < otraHabitacionDisponibles.size(); i++) {
                                Habitacion habi = otraHabitacionDisponibles.get(i);
                                System.out.println(i + ". " + habi.getNumero());
                            }
                            sc = new Scanner(System.in);
                            int opcion;
                            do {
                                System.out.println("Seleccione la habitación deseada (ingrese un número del 0 al " +
                                        (otraHabitacionDisponibles.size() - 1) + "):");
                                opcion = sc.nextInt();
                            } while (opcion < 0 || opcion >= otraHabitacionDisponibles.size());
                            // Actualizar la habitación seleccionada en la lista del hospital
                            habitacionSeleccionada = otraHabitacionDisponibles.get(opcion);
                            habitacionSeleccionada.setOcupada(true);
                            int index = -1;
                            for (int i = 0; i < hospital.habitaciones.size(); i++) {
                                if (hospital.habitaciones.get(i).equals(habitacionSeleccionada)) {
                                    index = i;
                                    break;
                                }
                            }
                            if (index != -1) {
                                hospital.habitaciones.set(index, habitacionSeleccionada);
                            }

                            // Eliminar la habitación seleccionada de la lista de habitaciones disponibles
                            otraHabitacionDisponibles.remove(opcion);

                            otraHabitacion= habitacionSeleccionada;
                        }
                        else{
                            otraHabitacion = null;
                        }
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

    }


