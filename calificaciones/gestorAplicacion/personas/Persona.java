package gestorAplicacion.personas;

public class Persona {

    private long cedula;
    private String nombre;

    public Persona (long cedula, String nombre) {
        this.cedula = cedula;
        this.nombre = nombre;
    }

    public long getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }
}
