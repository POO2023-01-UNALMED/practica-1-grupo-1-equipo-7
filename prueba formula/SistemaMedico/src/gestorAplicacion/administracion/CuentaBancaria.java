package gestorAplicacion.administracion;

import gestorAplicacion.personas.Persona;

public class CuentaBancaria {
    private float saldo;
    private Persona persona;

    public CuentaBancaria(int saldo, Persona persona){
        this.persona = persona;
        this.saldo = saldo;
    }
    public CuentaBancaria(Persona persona){
        this(0,persona);
    }

    public void restarSaldo(float valor){
        saldo -= valor;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
