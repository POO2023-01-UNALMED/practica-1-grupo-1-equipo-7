package gestorAplicacion.personas;

import gestorAplicacion.administracion.*;
import gestorAplicacion.servicios.Formula;
import gestorAplicacion.servicios.Servicio;

import java.util.ArrayList;

public class Paciente extends Persona{

    private final HistoriaClinica historiaClinica;
    private String tipoEps;
    private CategoriaHabitacion categoriaHabitacion;
    private Habitacion habitacionAsignada;

    public Paciente(int cedula, String nombre, String tipoEps,CategoriaHabitacion categoriaHabitacion) {
        super(cedula,nombre);
        this.tipoEps = tipoEps;
        this.categoriaHabitacion=categoriaHabitacion;
        this.historiaClinica = new HistoriaClinica(this);
    }

    public double calcularPrecio(Servicio servicio) {
        return 0;
    }
    public double calcularPrecio(Formula formula){
        double precio = 0;
        for (Medicamento med : formula.getListaMedicamentos()){
            precio += med.getPrecio();
        }
        formula.setPrecio(precio);
        return precio;
    }
    public ArrayList<Medicamento> medEnfermedad(Enfermedad enfermedad, Hospital hospital) {
        ArrayList<Medicamento> medicamentos = hospital.medicamentosDisponibles();
        ArrayList<Medicamento> medEnfermedades = new ArrayList<Medicamento>();
        for (Medicamento med : medicamentos){
            if (enfermedad == med.getEnfermedad()){
                medEnfermedades.add(med);
            }

        }
        return medEnfermedades;
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

    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }

    public String getTipoEps() {
        return tipoEps;
    }

    public void setTipoEps(String tipoEps) {
        this.tipoEps = tipoEps;
    }

    public CategoriaHabitacion getCategoriaHabitacion()
    {
        return categoriaHabitacion;
    }

    public void setCategoriaHabitacion(CategoriaHabitacion categoriaHabitacion) {
        this.categoriaHabitacion = categoriaHabitacion;
    }

    public Habitacion getHabitacionAsignada() {
        return habitacionAsignada;
    }

    public void setHabitacionAsignada(Habitacion habitacionAsignada) {
        this.habitacionAsignada = habitacionAsignada;
    }
}
