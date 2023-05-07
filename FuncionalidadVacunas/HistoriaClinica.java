package FuncionalidadVacunas;

import java.util.ArrayList;

public class HistoriaClinica {
    private Paciente paciente;
    private ArrayList<Vacuna> historialVacunas= new ArrayList<Vacuna>();

    public ArrayList<Vacuna> getHistorialVacunas() {
        return historialVacunas;
    }
}
