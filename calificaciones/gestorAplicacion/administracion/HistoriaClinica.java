package gestorAplicacion.administracion;

import gestorAplicacion.personas.Paciente;

import java.util.ArrayList;

public class HistoriaClinica {

    private Paciente paciente;
    private ArrayList<Cita> historialCitas = new ArrayList<>();

    public HistoriaClinica (Paciente paciente) {
        this.paciente = paciente;
    }

    public void actualizarHistoriaClinica(Cita citaAsignada) {
        historialCitas.add(citaAsignada);
    }

    public ArrayList<Cita> getHistorialCitas() {
        return historialCitas;
    }


}
