package gestorAplicacion.personas;

public class Doctor extends Persona{
    private String especialidad;

    public Doctor(int cedula, String nombre) {
        super(cedula,nombre);
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
