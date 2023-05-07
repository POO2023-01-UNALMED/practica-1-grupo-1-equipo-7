package gestorAplicacion.servicios;

import gestorAplicacion.personas.Paciente;

import java.io.Serializable;

public abstract class Servicio implements Serializable {

    protected static long generadorID = 1;
    protected final long idServicio;
    protected final Paciente paciente;
    private boolean estadoPago;

    public Servicio(Paciente paciente) {
        this.idServicio = generadorID++;
        this.paciente = paciente;
        this.estadoPago = false;
    }

    public boolean isEstadoPago() {
        return estadoPago;
    }

    public long getIdServicio() {
        return idServicio;
    }

    public void setEstadoPago(boolean estadoPago) {
        this.estadoPago = estadoPago;
    }
}
