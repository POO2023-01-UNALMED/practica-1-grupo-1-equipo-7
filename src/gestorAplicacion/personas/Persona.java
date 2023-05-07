package gestorAplicacion.personas;

import java.io.Serializable;

public class Persona implements Serializable {

    private int cedula;
    private String nombre;

    public Persona(int cedula, String nombre) {
        this.cedula = cedula;
        this.nombre = nombre;
    }

    public int getCedula() {
        return cedula;
    }
}
