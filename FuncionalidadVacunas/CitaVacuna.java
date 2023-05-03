package FuncionalidadVacunas;

public class CitaVacuna extends Cita{
    private Vacuna vacuna;

    public CitaVacuna(String fecha, Paciente paciente, Vacuna vacuna) {
        super(fecha, paciente, null);
        this.vacuna = vacuna;
    }

    public Vacuna getVacuna() {
        return vacuna;
    }
}
