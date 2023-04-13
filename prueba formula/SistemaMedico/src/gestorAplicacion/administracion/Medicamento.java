package gestorAplicacion.administracion;

public class Medicamento {
	private String nombre;
	private String tipo;
	private String descripcion;
	private int cantidad = 0;
	
	public Medicamento(String nombre, String tipo, String descripcion, int cantidad) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.descripcion = descripcion;
		this.cantidad=cantidad;
	}
	
	public void eliminarCantidad() {
		this.cantidad=this.cantidad-1;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	
	public String toString() {
		return "Nombre: " + nombre + ", " + "Tipo: " + tipo + ", " + "Descripcion: " + descripcion;
	}

}
