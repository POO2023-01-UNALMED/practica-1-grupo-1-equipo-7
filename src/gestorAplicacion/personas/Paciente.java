package gestorAplicacion.personas;

import gestorAplicacion.administracion.HistoriaClinica;

public class Paciente extends Persona{

    private final HistoriaClinica historiaClinica;

    public Paciente(int cedula, String nombre) {
        super(cedula,nombre);
        this.historiaClinica = new HistoriaClinica(this);
    }

    public double calcularPrecio() {
        return 0;
    }

    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }
}
