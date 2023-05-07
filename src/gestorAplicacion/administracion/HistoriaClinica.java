package gestorAplicacion.administracion;

import gestorAplicacion.personas.Paciente;
import gestorAplicacion.servicios.EjemploServicio;
import gestorAplicacion.servicios.Servicio;

import java.io.Serializable;
import java.util.ArrayList;

public class HistoriaClinica implements Serializable {

    private final Paciente paciente;

    private ArrayList<EjemploServicio> listaEjemploServicios = new ArrayList<>();

    public HistoriaClinica(Paciente paciente) {
        this.paciente = paciente;
    }

    public ArrayList<Servicio> obtenerServiciosSinPagar() {
        ArrayList<Servicio> serviciosSinPagar = new ArrayList<>();

        for (Servicio servicio :
                listaEjemploServicios) {
            if (!servicio.isEstadoPago()) {
                serviciosSinPagar.add(servicio);
            }
        }
        return serviciosSinPagar;
    }

    public ArrayList<EjemploServicio> getListaEjemploServicios() {
        return listaEjemploServicios;
    }
}
