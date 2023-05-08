package gestorAplicacion.administracion;

import gestorAplicacion.personas.Enfermedad;

public class Medicamento {
    private String nombre;
    private Enfermedad enfermedad;
    private String descripcion;
    private int cantidad;
    private float precio;

    public void eliminarCantidad() {
        this.cantidad -= 1;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Enfermedad getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(Enfermedad enfermedad) {
        this.enfermedad = enfermedad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    @Override
    public String toString() {
        return "Nombre: " + nombre + ", " + "Enfermedad: " + enfermedad.getNombre() + " " + enfermedad.getTipologia() + ", " + "Descripcion: " + descripcion;
    }
}
