public class Habitacion {
    private Paciente paciente;
    private int numero;
    private CategoriaHabitacion categoria;
    private boolean ocupada;




    public Habitacion(int numero, CategoriaHabitacion categoria, boolean ocupada)
    {
        this.numero = numero;
        this.categoria = categoria;
        this.ocupada = ocupada;

    }


    protected static Habitacion BuscarHabitacionDisponible(CategoriaHabitacion categoria)
    {
        for (Habitacion habitacion : Hospital.habitaciones)
        {
            if (!habitacion.isOcupada() && habitacion.getCategoria() == categoria)
            {
                return habitacion;
            }
        }
        return null;
    }


    protected static CategoriaHabitacion BuscarOtraCategoria(CategoriaHabitacion categoria)
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
}
