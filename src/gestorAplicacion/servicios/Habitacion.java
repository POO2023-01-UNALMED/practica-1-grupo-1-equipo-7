/* Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
y Santiago Arboleda Acevedo */
package gestorAplicacion.servicios;


import gestorAplicacion.administracion.CategoriaHabitacion;
import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.personas.Paciente;

import java.util.ArrayList;


public class Habitacion  extends Servicio{
    private int numero;
    private CategoriaHabitacion categoria;
    private boolean ocupada;
    private int dias;

    public Habitacion(int numero, CategoriaHabitacion categoria, boolean ocupada,Paciente paciente,int dias)
    {
        super(paciente);
        this.numero = numero;
        this.categoria = categoria;
        this.ocupada = ocupada;
        this.dias=dias;


    }


    public static ArrayList<Habitacion> BuscarHabitacionDisponible(CategoriaHabitacion categoria)
    {
        ArrayList<Habitacion>habitacionesDisponibles=new ArrayList<>();

        for (Habitacion habitacion : Hospital.habitaciones)
        {
            if (!habitacion.isOcupada() && habitacion.getCategoria() == categoria)
            {
                habitacionesDisponibles.add(habitacion);
            }
        }

        if (habitacionesDisponibles.isEmpty()) {
            return null;
        }
        return habitacionesDisponibles;
    }


    public static CategoriaHabitacion BuscarOtraCategoria(CategoriaHabitacion categoria)
    {
        switch (categoria)
        {
            case UCC:
                return CategoriaHabitacion.UCI;
            case UCI:
                return CategoriaHabitacion.OBSERVACION;
            case OBSERVACION:
                return CategoriaHabitacion.DOBLE;
            case DOBLE:
                return CategoriaHabitacion.INDIVIDUAL;
            case INDIVIDUAL:
                return CategoriaHabitacion.CAMILLA;
            default:
                return null;
        }
    }

    public Paciente getPaciente()
    {
        return paciente;
    }

    public void setPaciente(Paciente paciente)
    {
        this.paciente = paciente;
    }


    public int getNumero()
    {
        return numero;
    }

    public void setNumero(int numero)
    {
        this.numero = numero;
    }

    public CategoriaHabitacion getCategoria()
    {
        return categoria;
    }

    public void setCategoria(CategoriaHabitacion categoria)
    {
        this.categoria = categoria;
    }

    public boolean isOcupada()
    {
        return ocupada;
    }

    public void setOcupada(boolean ocupada)
    {
        this.ocupada = ocupada;
    }
    public int getDias()
    {
        return dias;
    }

    public void setDias(int dias)
    {
        this.dias = dias;
    }

    @Override
    public void validarPago(Paciente paciente, long idServicio) {
        if (paciente.getHabitacionAsignada().getIdServicio() == idServicio)
            paciente.getHabitacionAsignada().setEstadoPago(true);
    }

    // Metodo que imprime una descripcion del servicio
    @Override
    public String descripcionServicio() {
        return idServicio + " - Habitacion " + numero + " ocupada " + dias + " dias";
    }

}
