/* Clase que usa las funcionalidades del programa.
 Hereda de la clase Persona */
package gestorAplicacion.personas;

import gestorAplicacion.administracion.*;
import gestorAplicacion.servicios.*;

import java.util.ArrayList;
import java.util.Objects;

public class Paciente extends Persona{

    // Atributos
	private final HistoriaClinica historiaClinica;
    private CategoriaHabitacion categoriaHabitacion;
    private Habitacion habitacionAsignada;

    // Constructores
    public Paciente(int cedula, String nombre, String tipoEps,CategoriaHabitacion categoriaHabitacion) {
        super(cedula,nombre,tipoEps);
        this.categoriaHabitacion=categoriaHabitacion;
        this.historiaClinica = new HistoriaClinica(this);
    }
    // Sobrecarga de constructor
    public Paciente(int cedula, String nombre, String tipoEps){
        this(cedula, nombre, tipoEps, null);
    }

    // Métodos

    /* Método que se encarga de buscar los medicamentos disponibles y posteriormente busca los medicamentos
    que traten la enfermedad ingresada como parámetro */
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

    /* Método que busca los doctores por especialidad y por el tipo de eps del paciente */
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

  /* Método que agrega una cita a la lista de citas de la historia clínica del paciente */
    public void actualizarHistorialCitas(Cita citaAsignada) {
    	historiaClinica.getHistorialCitas().add(citaAsignada);
    }

    /* Método que muestra la historia clínica del paciente */
    public void mostrarHistorial() {
    	for(int i=1; i<=historiaClinica.getHistorialCitas().size(); i++) {
    		System.out.println("Fecha: " + historiaClinica.getHistorialCitas().get(i-1).getFecha());
    		System.out.println("Paciente: " + historiaClinica.getHistorialCitas().get(i-1).getPaciente().getNombre());
    		System.out.println("Doctor: " + historiaClinica.getHistorialCitas().get(i-1).getDoctor().getNombre());
    	}
    }

    //Sobre carga de métodos con calcular precio de los distintos servicios
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

    /* Método que busca las vacunas por tipo ingresada en el parámetro, invocando el método
     de hospital, y con el for filtrandolas por eps */
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
    //Método para bienvenida de doctor
    public String mensajeDoctor(Doctor doctor){
        Persona persona=doctor;
        //Hay ligadura dinámica
        return persona.mensaje()+ "\nPor favor selecciona los medicamentos que vas a formularle a: "+getNombre();
    }

    //Getter y setter
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
        return "¡Adiós "+getNombre()+" "+servicio.mensaje();
    }
}
