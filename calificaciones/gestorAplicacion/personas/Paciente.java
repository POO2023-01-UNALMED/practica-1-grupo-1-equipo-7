package gestorAplicacion.personas;

import gestorAplicacion.administracion.Cita;
import gestorAplicacion.administracion.HistoriaClinica;
import gestorAplicacion.administracion.Hospital;

import java.util.ArrayList;

public class Paciente extends Persona {

    private HistoriaClinica historiaClinica;

    public Paciente(long cedula, String nombre) {
        super(cedula, nombre);
        this.historiaClinica = new HistoriaClinica(this);
    }

    public boolean validarCita (Doctor doctor) {
        boolean validacion = false;

        for (Cita cita: this.historiaClinica.getHistorialCitas())
            if (doctor == cita.getDoctor()) {
                validacion = true;
                break;
            }

        return validacion;
    }

    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }
}
