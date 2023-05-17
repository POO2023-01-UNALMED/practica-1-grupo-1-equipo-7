package gestorAplicacion.servicios;

import gestorAplicacion.administracion.HistoriaClinica;
import gestorAplicacion.personas.Paciente;

import java.io.Serializable;
import java.util.ArrayList;

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

    public static ArrayList<Servicio> obtenerServiciosSinPagar(Paciente paciente) {
        HistoriaClinica historiaClinicaPaciente = paciente.getHistoriaClinica();
        ArrayList<Servicio> serviciosSinPagar = new ArrayList<>();

        // Obtiene todos los servicios brindados al paciente
        serviciosSinPagar.addAll(historiaClinicaPaciente.getHistorialCitas());
        serviciosSinPagar.addAll(historiaClinicaPaciente.getListaFormulas());

        // Filtra los servicios pagados
        serviciosSinPagar.removeIf(Servicio::isEstadoPago);

        return serviciosSinPagar;
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
