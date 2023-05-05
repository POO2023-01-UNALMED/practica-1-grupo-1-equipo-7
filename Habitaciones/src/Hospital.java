import java.util.ArrayList;
import java.util.Scanner;

public class Hospital {
    private Paciente paciente;
    private Habitacion habitacion;
    static ArrayList<Habitacion> habitaciones= new ArrayList<>();
    static
    {
        habitaciones.add(new Habitacion(14, CategoriaHabitacion.CAMILLA,false));
        habitaciones.add(new Habitacion(15, CategoriaHabitacion.CAMILLA,true));
        habitaciones.add(new Habitacion(16, CategoriaHabitacion.CAMILLA,true));
        habitaciones.add(new Habitacion(17, CategoriaHabitacion.CAMILLA,false));
        habitaciones.add(new Habitacion(18, CategoriaHabitacion.CAMILLA,true));

        habitaciones.add(new Habitacion(1, CategoriaHabitacion.INDIVIDUAL,false));
        habitaciones.add(new Habitacion(2, CategoriaHabitacion.INDIVIDUAL,true));
        habitaciones.add(new Habitacion(3, CategoriaHabitacion.INDIVIDUAL,true));
        habitaciones.add(new Habitacion(4, CategoriaHabitacion.INDIVIDUAL,false));
        habitaciones.add(new Habitacion(5, CategoriaHabitacion.INDIVIDUAL,true));

        habitaciones.add(new Habitacion(10, CategoriaHabitacion.DOBLE,false));
        habitaciones.add(new Habitacion(11, CategoriaHabitacion.DOBLE,false));
        habitaciones.add(new Habitacion(12, CategoriaHabitacion.DOBLE,true));
        habitaciones.add(new Habitacion(13, CategoriaHabitacion.DOBLE,false));

        habitaciones.add(new Habitacion(101, CategoriaHabitacion.OBSERVACION,true));
        habitaciones.add(new Habitacion(102, CategoriaHabitacion.OBSERVACION,true));
        habitaciones.add(new Habitacion(103, CategoriaHabitacion.OBSERVACION,false));

        habitaciones.add(new Habitacion(201, CategoriaHabitacion.UCI,true));
        habitaciones.add(new Habitacion(202, CategoriaHabitacion.UCI,true));
        habitaciones.add(new Habitacion(203, CategoriaHabitacion.UCI,false));

        habitaciones.add(new Habitacion(210, CategoriaHabitacion.UCC,false));
        habitaciones.add(new Habitacion(211, CategoriaHabitacion.UCC,false));
        habitaciones.add(new Habitacion(212, CategoriaHabitacion.UCC,true));

    }
    protected static ArrayList<Paciente> listaPacientes= new ArrayList<>();
    protected static ArrayList<Paciente> pacientesNoRegistrados= new ArrayList<>();


    static {
        listaPacientes.add(new Paciente(111, "Juan","subsidiado",null,null,null,null));
        listaPacientes.add(new Paciente(222, "Jose","contributivo",null,null,null,null));
        listaPacientes.add(new Paciente(333, "Felipe","subsidiado",null,null,null,null));
        listaPacientes.add(new Paciente(444, "Maria","contributivo",null,null,null,null));
        listaPacientes.add(new Paciente(555, "Pedro","subsidiado",null,null,null,null));

    }

    protected static Paciente buscarPaciente(int cedula)
    {
        Scanner scanner = new Scanner(System.in);
        for (Paciente paciente : listaPacientes)
        {
            if (paciente.getCedula() == cedula)
            {
                return paciente;
            }
        }
        System.out.println("Ingrese el nombre del paciente:");
        String nombre = scanner.nextLine();
        Paciente paciente = new Paciente(cedula, nombre,  "particular",null,null, null,null);
        Hospital.pacientesNoRegistrados.add(paciente);
        return paciente;
    }


}






