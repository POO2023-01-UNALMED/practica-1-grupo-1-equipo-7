package gestorAplicacion.personas;

import gestorAplicacion.administracion.CuentaBancaria;

public class Persona {
	private int cedula;
    private String nombre;
	private CuentaBancaria cuenta;

    
    public Persona(int cedula, String nombre) {
    	this.cedula = cedula;
    	this.nombre = nombre;
		cuenta = new CuentaBancaria(this);
    }
    
	public int getCedula() {
		return cedula;
	}
	public void setCedula(int cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public CuentaBancaria getCuenta() {
		return cuenta;
	}

	public void setCuenta(CuentaBancaria cuenta) {
		this.cuenta = cuenta;
	}
}
