/* Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
y Santiago Arboleda Acevedo */
package gestorAplicacion.personas;

import gestorAplicacion.administracion.*;
import gestorAplicacion.servicios.*;

import java.util.ArrayList;
import java.util.Objects;

//Clase destinada a crear pacientes
public class Paciente extends Persona implements Pago{

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

    //Sobre carga de métodos con calcular precio de los distintos servicios
    @Override
    public double calcularPrecio(Formula formula){
        double precio = 0;
        for (Medicamento med : formula.getListaMedicamentos()){
            if (formula.getPaciente().getTipoEps().equals("Contributivo")){
                precio += med.getPrecio()*0.8;
            }
            if (formula.getPaciente().getTipoEps().equals("Subsidiado")){
                precio += med.getPrecio()*0.7;
            }
            if (formula.getPaciente().getTipoEps().equals("Particular")){
                precio += med.getPrecio();
            }
        }
        return precio*(1+IVA);
    }

    @Override
    public double calcularPrecio(Cita citaAsignada) {

    	double precioTotal = 0;

    	if (Objects.equals(citaAsignada.getDoctor().getEspecialidad(),"General")) {
    		precioTotal += 5000;
    	}

    	if (Objects.equals(citaAsignada.getDoctor().getEspecialidad(),"Oftalmologia")) {
    		precioTotal += 7000;
    	}

    	if (Objects.equals(citaAsignada.getDoctor().getEspecialidad(),"Odontologia")) {
    		precioTotal += 10000;
    	}

    	if (Objects.equals(citaAsignada.getPaciente().getTipoEps(),"Contributivo")) {
    		precioTotal += 2000;
    	}

    	if (Objects.equals(citaAsignada.getPaciente().getTipoEps(),"Subsidiado")) {
    		precioTotal += 500;
    	}

    	if (Objects.equals(citaAsignada.getPaciente().getTipoEps(),"Particular")) {
    		precioTotal += 10000;
    	}

    	precioTotal = precioTotal*(1+IVA);
    	return precioTotal;
    }

    @Override
    public double calcularPrecio(CitaVacuna citaAsignada){
        double precioTotal=citaAsignada.getVacuna().getPrecio();

        if(Objects.equals(citaAsignada.getVacuna().getTipo(), "Obligatoria")){
            precioTotal += 1000;
        }
        if(Objects.equals(citaAsignada.getVacuna().getTipo(), "No obligatoria")){
            precioTotal += 3000;
        }
        if (Objects.equals(citaAsignada.getPaciente().getTipoEps(), "Contributivo")){
            precioTotal += 2000;
        }
        if (Objects.equals(citaAsignada.getPaciente().getTipoEps(), "Subsidiado")){
            precioTotal += 500;
        }
        if (Objects.equals(citaAsignada.getPaciente().getTipoEps(), "Particular")){
            precioTotal += 10000;
        }
        precioTotal=precioTotal*(1+IVA);
        return precioTotal;
    }

    @Override
    public  double calcularPrecio(Habitacion habitacionAsignada)
    {
        double precio=0;
        if (Objects.equals(getTipoEps(), "Subsidiado"))
        {
            precio=habitacionAsignada.getCategoria().getValor()*0;
            return precio*(1+IVA);
        }
        else if (Objects.equals(getTipoEps(), "Contributivo"))
        {
            precio=(habitacionAsignada.getCategoria().getValor()/2)*habitacionAsignada.getDias();
            return precio*(1+IVA);
        }
        else
        {
            precio=habitacionAsignada.getCategoria().getValor()*habitacionAsignada.getDias();
            return precio*(1+IVA);
        }
    }

    /* Método que busca las vacunas por tipo ingresada en el parámetro, invocando el método
     de hospital, y con el for filtrandolas por eps del paciente */
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
    public String mensajeDoctor(Persona doctor){
        //Hay ligadura dinámica
        return doctor.bienvenida()+ "\nPor favor selecciona los medicamentos que vas a formularle a: "+getNombre();
    }

    //Getter y setter

    //Agrega una cita de vacuna al historial de vacunas del paciente
    public void actualizarHistorialVacunas(CitaVacuna citaAsignada){
        historiaClinica.getHistorialVacunas().add(citaAsignada);
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

    public String despedida(Cita citaAsignada ){
        /*Hay ligadura dinámica ya que la referencia
        citaAsignada puede apuntar a una cita o a una cita vacuna
        y dependiendo de eso ejecutará el método más específico
        de mensaje().
         */
        return "¡Adiós "+getNombre()+" "+citaAsignada.mensaje();
    }
}
