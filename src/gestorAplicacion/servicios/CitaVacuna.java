/* Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
y Santiago Arboleda Acevedo */
package gestorAplicacion.servicios;

import gestorAplicacion.administracion.Vacuna;
import gestorAplicacion.personas.Paciente;
import gestorAplicacion.personas.Persona;

//Clase destinada a las citas que son de vacunas
public class CitaVacuna extends Cita{

    //Atributos
    private Vacuna vacuna;

    //Constructor
    public CitaVacuna(String fecha, Paciente paciente, Vacuna vacuna){
        super(null, fecha, paciente);
        this.vacuna=vacuna;
    }

    //Métodos

    //Método para buscar id del servicio y cambiar estado de pago
    @Override
    public void validarPago(Paciente paciente, long idServicio) {
        for (CitaVacuna citaVacuna :
                paciente.getHistoriaClinica().getHistorialVacunas()) {
            if (citaVacuna.getIdServicio() == idServicio)
                citaVacuna.setEstadoPago(true);
        }
    }

    //Getters y Setters
    public String mensaje(){
        return "del servicio de vacunas!";
    }
    public Vacuna getVacuna() {
        return vacuna;
    }

    public void setVacuna(Vacuna vacuna) {
        this.vacuna = vacuna;
    }



}
