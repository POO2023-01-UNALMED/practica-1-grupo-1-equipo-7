import java.util.ArrayList;

public class Paciente {
    private int cedula;
    private String nombre;
    private String TipoEps;
    private HistoriaClinica historiaClinica;
    private CuentaBancaria cuentaBancaria;
    private CategoriaHabitacion categoriaHabitacion;
    private ArrayList<Factura> facturas=new ArrayList<Factura>();
    private Habitacion habitacionAsignada;


    public Paciente(int cedula, String nombre, String tipoEps, HistoriaClinica historiaClinica, CuentaBancaria cuentaBancaria, CategoriaHabitacion categoriaHabitacion, ArrayList<Factura> facturas)
    {
        this.cedula = cedula;
        this.nombre = nombre;
        TipoEps = tipoEps;
        this.historiaClinica = historiaClinica;
        this.cuentaBancaria = cuentaBancaria;
        this.categoriaHabitacion = categoriaHabitacion;
        this.facturas = facturas;
    }
    public  float calcularPrecio(int dias)
    {
        float precio=0;
        if (getTipoEps()=="subsidiado")
        {
            precio=getCategoriaHabitacion().getValor()*0;
                return precio;
            }
        else if (getTipoEps()=="contributivo")
        {
            precio=(getCategoriaHabitacion().getValor()/2)*dias;
            return precio;
        }
        else
        {
            precio=getCategoriaHabitacion().getValor()*dias;
            return precio;
        }
    }




    public int getCedula()
    {
        return cedula;
    }

    public void setCedula(int cedula)
    {
        this.cedula = cedula;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }


    public  String getTipoEps()
    {
        return TipoEps;
    }

    public void setTipoEps(String tipoEps)
    {
        TipoEps = tipoEps;
    }

    public CategoriaHabitacion getCategoriaHabitacion()
    {
        return categoriaHabitacion;
    }

    public void setCategoriaHabitacion(CategoriaHabitacion categoriaHabitacion)
    {
        this.categoriaHabitacion = categoriaHabitacion;
    }

    public Habitacion getHabitacionAsignada()
    {
        return habitacionAsignada;
    }

    public void setHabitacionAsignada(Habitacion habitacionAsignada)
    {
        this.habitacionAsignada = habitacionAsignada;
    }

}

