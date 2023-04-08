package Prueba;
import java.util.ArrayList;

public class HistoriaClinica {
	private Paciente paciente;
    static private ArrayList<Cita> historialCitas = new ArrayList<Cita>();
    
    static public void actualizarHistoriaClinica(Cita citaAsignada) {
    	historialCitas.add(citaAsignada);
    }
}
