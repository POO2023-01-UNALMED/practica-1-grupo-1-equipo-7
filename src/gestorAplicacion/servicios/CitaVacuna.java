package gestorAplicacion.servicios;

import gestorAplicacion.administracion.Vacuna;
import gestorAplicacion.personas.Paciente;

public class CitaVacuna extends Cita{
    private Vacuna vacuna;

    public CitaVacuna(String fecha, Paciente paciente, Vacuna vacuna){
        super(null, fecha, paciente);
        this.vacuna=vacuna;
    }

    public Vacuna getVacuna() {
        return vacuna;
    }

    public void setVacuna(Vacuna vacuna) {
        this.vacuna = vacuna;
    }
}
