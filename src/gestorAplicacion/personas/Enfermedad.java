package gestorAplicacion.personas;

public class Enfermedad {
    private String especialidad;
    private String nombre;
    private String tipologia;

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }
    @Override
    public String toString(){
        return nombre + " " + tipologia + " Especialidad que la trata: " + especialidad;
    }
}
