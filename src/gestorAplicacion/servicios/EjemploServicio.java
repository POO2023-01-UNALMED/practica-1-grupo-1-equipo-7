package gestorAplicacion.servicios;

import gestorAplicacion.personas.Paciente;

public class EjemploServicio extends Servicio {

    protected final double precioBase = 10000;

    public EjemploServicio(Paciente paciente, String fecha) {
        super(paciente);
        this.paciente.getHistoriaClinica().getListaEjemploServicios().add(this);
    }

    @Override
    public String toString() {
        return "ID: " + this.idServicio + " - Ejemplo servicio";
    }
}
