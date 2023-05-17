package gestorAplicacion.personas;

import gestorAplicacion.administracion.*;
import gestorAplicacion.servicios.CitaVacuna;
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
    public double calcularPrecio(Formula formula){
        double precio = 0;
        for (Medicamento med : formula.getListaMedicamentos()){
            precio += med.getPrecio();
        }
        formula.setPrecio(precio);
        return precio;
    }

    public double calcularPrecio(CitaVacuna citaAsignada){
        final double IVA= 0.19;
        double precioTotal=citaAsignada.getVacuna().getPrecio();

        if(citaAsignada.getVacuna().getTipo()=="Obligatoria"){
            precioTotal += 1000;
        }
        if(citaAsignada.getVacuna().getTipo()=="No obligatoria"){
            precioTotal += 3000;
        }
        if (citaAsignada.getPaciente().getTipoEps()=="Contributivo"){
            precioTotal += 2000;
        }
        if (citaAsignada.getPaciente().getTipoEps()=="Subsidiado"){
            precioTotal += 500;
        }
        if (citaAsignada.getPaciente().getTipoEps()=="Particular"){
            precioTotal += 10000;
        }

        precioTotal=precioTotal*(1+IVA);
        return precioTotal;
    }

    /*
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
    }*/

    public ArrayList<Vacuna> buscarVacunaPorEps(String tipo, Hospital hospital){
        ArrayList<Vacuna> vacunasPorTipo= hospital.buscarTipoVacuna(tipo);
        ArrayList<Vacuna> vacunasDisponibles= new ArrayList<Vacuna>();

        for (int i=1; i<=vacunasPorTipo.size();i++){
            ArrayList<String> tipoEpsVacuna= vacunasPorTipo.get(i-1).getTipoEps();
            for (int j=1;j<=tipoEpsVacuna.size();j++){
                if (tipoEpsVacuna.get(j-1)==getTipoEps()){
                    vacunasDisponibles.add(vacunasPorTipo.get(i-1));
                }
            }
        }
        return vacunasDisponibles;
    }

    public void actualizarHistorialVacunas(CitaVacuna citaAsignada){
        historiaClinica.getHistorialVacunas().add(citaAsignada.getVacuna());
    }

    public void mostrarHistorialVacunas() {
        for (int i=1; i<=historiaClinica.getHistorialVacunas().size();i++){
            System.out.println(i + ". Vacuna: "+historiaClinica.getHistorialVacunas().get(i-1).getNombre());
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
