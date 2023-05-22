package gestorAplicacion.personas;

import gestorAplicacion.administracion.*;
import gestorAplicacion.servicios.*;

import java.util.ArrayList;
import java.util.Objects;

public class Paciente extends Persona{

	private final HistoriaClinica historiaClinica;
    private CategoriaHabitacion categoriaHabitacion;
    private Habitacion habitacionAsignada;

    public Paciente(int cedula, String nombre, String tipoEps,CategoriaHabitacion categoriaHabitacion) {
        super(cedula,nombre,tipoEps);
        this.categoriaHabitacion=categoriaHabitacion;
        this.historiaClinica = new HistoriaClinica(this);
    }
    public Paciente(int cedula, String nombre, String tipoEps){
        this(cedula, nombre, tipoEps, null);
    }

    public ArrayList<Medicamento> medEnfermedad(Enfermedad enfermedad, Hospital hospital) {
        ArrayList<Medicamento> medicamentos = hospital.medicamentosDisponibles();
        ArrayList<Medicamento> medEnfermedades = new ArrayList<Medicamento>();
        for (Medicamento med : medicamentos){
            if (enfermedad.getNombre().equals(med.getEnfermedad().getNombre()) && enfermedad.getTipologia().equals(med.getEnfermedad().getTipologia())){
                medEnfermedades.add(med);
            }

        }
        return medEnfermedades;
    }
    
    public ArrayList<Doctor> buscarDoctorEps(String especialidad, Hospital hospital){
    	ArrayList<Doctor> doctoresPorEspecialidad = hospital.buscarTipoDoctor(especialidad);
    	ArrayList<Doctor> doctoresDisponibles = new ArrayList<Doctor>();
    	
    	for(int i=1; i<=doctoresPorEspecialidad.size(); i++) {
    		if (Objects.equals(doctoresPorEspecialidad.get(i-1).getTipoEps(), getTipoEps())) {
    			doctoresDisponibles.add(doctoresPorEspecialidad.get(i-1));
    		}
    }
		return doctoresDisponibles;
  }
    
    public void actualizarHistorialCitas(Cita citaAsignada) {
    	historiaClinica.getHistorialCitas().add(citaAsignada);
    }
    
    public void mostrarHistorial() {
    	for(int i=1; i<=historiaClinica.getHistorialCitas().size(); i++) {
    		System.out.println("Fecha: " + historiaClinica.getHistorialCitas().get(i-1).getFecha());
    		System.out.println("Paciente: " + historiaClinica.getHistorialCitas().get(i-1).getPaciente().getNombre());
    		System.out.println("Doctor: " + historiaClinica.getHistorialCitas().get(i-1).getDoctor().getNombre());
    	}
    }

    public double calcularPrecio(Formula formula){
        double precio = 0;
        for (Medicamento med : formula.getListaMedicamentos()){
            precio += med.getPrecio();
        }
        return precio;
    }
    
    public double calcularPrecio(Cita citaAsignada) {
    	
    	final double IVA = 0.19;
    	double precioTotal = 0;
    	
    	if (citaAsignada.getDoctor().getEspecialidad() == "General") {
    		precioTotal += 5000;
    	}
    	
    	if (citaAsignada.getDoctor().getEspecialidad() == "Oftalmologia") {
    		precioTotal += 7000;
    	}
    	
    	if (citaAsignada.getDoctor().getEspecialidad() == "Odontologia") {
    		precioTotal += 10000;
    	}
    	
    	if (citaAsignada.getPaciente().getTipoEps() == "Contributivo") {
    		precioTotal += 2000;
    	}
    	
    	if (citaAsignada.getPaciente().getTipoEps() == "Subsidiado") {
    		precioTotal += 500;
    	}
    	
    	if (citaAsignada.getPaciente().getTipoEps() == "Particular") {
    		precioTotal += 10000;
    	}
    	
    	precioTotal = precioTotal*(1+IVA);
    	return precioTotal;
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

    public  double calcularPrecio(Habitacion habitacionAsignada)
    {
        double precio=0;
        if (getTipoEps()=="subsidiado")
        {
            precio=habitacionAsignada.getCategoria().getValor()*0;
            return precio;
        }
        else if (getTipoEps()=="contributivo")
        {
            precio=(habitacionAsignada.getCategoria().getValor()/2)*habitacionAsignada.getDias();
            return precio;
        }
        else
        {
            precio=habitacionAsignada.getCategoria().getValor()*habitacionAsignada.getDias();
            return precio;
        }
    }

    public ArrayList<Vacuna> buscarVacunaPorEps(String tipo, Hospital hospital){
        ArrayList<Vacuna> vacunasPorTipo= hospital.buscarTipoVacuna(tipo);
        ArrayList<Vacuna> vacunasDisponibles= new ArrayList<Vacuna>();

        for (int i=1; i<=vacunasPorTipo.size();i++){
            ArrayList<String> tipoEpsVacuna = vacunasPorTipo.get(i-1).getTipoEps();
            for (int j=1;j<=tipoEpsVacuna.size();j++){
                if (Objects.equals(tipoEpsVacuna.get(j - 1), getTipoEps())){
                    vacunasDisponibles.add(vacunasPorTipo.get(i-1));
                }
            }
        }
        return vacunasDisponibles;
    }

    public void actualizarHistorialVacunas(CitaVacuna citaAsignada){
        historiaClinica.getHistorialVacunas().add(citaAsignada);
    }

    public void mostrarHistorialVacunas() {
        for (int i=1; i<=historiaClinica.getHistorialVacunas().size();i++){
            System.out.println(i + ". Vacuna: "+historiaClinica.getHistorialVacunas().get(i-1).getVacuna().getNombre());
        }
    }

    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
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

    @Override

    public String toString(){
        return "---------------------------\nNombre: "+ getNombre() + "\nCédula: " + getCedula() + "\nTipo de EPS: " + getTipoEps() + "\n---------------------------";
    }

    //Despedida para funcionalidad Vacunas
    public String despedida(CitaVacuna citaAsignada ){
        Cita servicio=citaAsignada;
        //Hay ligadura dinámica
        return "¡Adiós "+getNombre()+" "+servicio.mensaje();
    }
    //Despedida para funcionalidad Cita médica
    public String despedida(Cita citaAsignada ){
        Cita servicio=citaAsignada;
        //Hay ligadura dinámica
        return "¡Adiós "+getNombre()+" "+servicio.mensaje();
    }
}
