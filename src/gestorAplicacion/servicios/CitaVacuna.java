package gestorAplicacion.servicios;

import gestorAplicacion.administracion.Vacuna;
import gestorAplicacion.personas.Paciente;
import gestorAplicacion.personas.Persona;

public class CitaVacuna extends Cita{
    private Vacuna vacuna;

    public CitaVacuna(String fecha, Paciente paciente, Vacuna vacuna){
        super(null, fecha, paciente);
        this.vacuna=vacuna;
    }

    public String mensaje(){
        return "del servicio de vacunas!";
    }
    public Vacuna getVacuna() {
        return vacuna;
    }

    public void setVacuna(Vacuna vacuna) {
        this.vacuna = vacuna;
    }

    @Override
    public void validarPago(Paciente paciente, long idServicio) {
        for (CitaVacuna citaVacuna :
                paciente.getHistoriaClinica().getHistorialVacunas()) {
            if (citaVacuna.getIdServicio() == idServicio)
                citaVacuna.setEstadoPago(true);
        }
    }
}
