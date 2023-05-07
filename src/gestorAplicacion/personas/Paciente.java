package gestorAplicacion.personas;

import gestorAplicacion.administracion.HistoriaClinica;
import gestorAplicacion.servicios.Servicio;

public class Paciente extends Persona{

    private final HistoriaClinica historiaClinica;

    public Paciente(int cedula, String nombre) {
        super(cedula,nombre);
        this.historiaClinica = new HistoriaClinica(this);
    }

    public double calcularPrecio(Servicio servicio) {
        return 0;
    }

    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }
}
