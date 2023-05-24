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
// Se define el constructor con sus respectivos atributos
    public Habitacion(int numero, CategoriaHabitacion categoria, boolean ocupada,Paciente paciente,int dias)
    {
        super(paciente);
        this.numero = numero;
        this.categoria = categoria;
        this.ocupada = ocupada;
        this.dias=dias;


    }

//Se crea el metodo estatico, el cual se encarga de filtrar y retornar un ArrayList de las habitaciones vacias de la categoria que se selecciono
    public static ArrayList<Habitacion> BuscarHabitacionDisponible(CategoriaHabitacion categoria)
    {
        //se instacia una nueva variable tipo ArrayList de clase habitacion
        ArrayList<Habitacion>habitacionesDisponibles=new ArrayList<>();
//se recorre la lista estatica que se encuentra en hospital
        for (Habitacion habitacion : Hospital.habitaciones)
        {
            //se comprueba si la habitacion esta no ocupada y si la categoria que se ingreso es la misma que la del objeto habitacion de la lista
            if (!habitacion.isOcupada() && habitacion.getCategoria() == categoria)
            {
                // se agrega el objeto habitacion que cumpla y se retorna
                habitacionesDisponibles.add(habitacion);
            }
        }

        //se comprueba si la lista esta vacia y si lo esta retorna null
        if (habitacionesDisponibles.isEmpty()) {
            return null;
        }
        //retorna la nueva lista
        return habitacionesDisponibles;
    }


    //Este metodo se encarga de cambiar la categoria a una inferior 
    public static CategoriaHabitacion BuscarOtraCategoria(CategoriaHabitacion categoria)
    {
        //contiene switch que comprueba la categoria que ingresa y retorna la categoria inferior 
        switch (categoria)
        {
            //si la categoria que ingresa es UCC retorna UCI
            case UCC:
                return CategoriaHabitacion.UCI;
            //si la categoria que ingresa es UCI retorna OBSERVACION    
            case UCI:
                return CategoriaHabitacion.OBSERVACION;
            //si la categoria que ingresa es OBSERVACION retorna DOBLE    
            case OBSERVACION:
                return CategoriaHabitacion.DOBLE;
            //si la categoria que ingresa es DOBLE retorna INDIVIDUAL    
            case DOBLE:
                return CategoriaHabitacion.INDIVIDUAL;
            //si la categoria que ingresa es INDIVIDUAL retorna CAMILLA    
            case INDIVIDUAL:
                return CategoriaHabitacion.CAMILLA;
            //Aqui lo que hace es que para CAMILLA no hay una categoria inferior por lo tanto nos retorna null y termina el proceso    
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

    //Se implementa para cambiar el estado de pago a los servicios 
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
