package model.cuentas;

import model.identidades.Particular;

/**
 * Clase que representa una cuenta de ahorro. Extiende la clase CuentaBancaria y añade los campos y métodos
 * necesarios para representar los parámetros solicitados en el ejercicio.
 *
 * @author Rafael SGP
 */
public class CuentaAhorro extends CuentaBancaria{
    private double interesRemuneracion;

    /**
     * Este constructor no añade nada nuevo y eleva la llamada a la clase padre
     * @param iban
     * @param cliente
     */
    public CuentaAhorro (Iban iban, Particular cliente){
        super.setIban(iban);
        super.setTitular(cliente);
    }

    public double getInteresRemuneracion() {
        return interesRemuneracion;
    }

    public void setInteresRemuneracion(double interesRemuneracion) {
        this.interesRemuneracion = interesRemuneracion;
    }

    /**
     * Este método sobreescribe el método abstracto declarado en la clase CuentaBancaria, realizando la mera
     * comprobación del saldo disponible.
     * @param cantidad La cantidad a sustraer
     * @return Indica si la operación ha podido realizarse
     */
    @Override
    public boolean substractSaldo(double cantidad) {
        if (cantidad <= getSaldo()){
            saldo -= cantidad;
            return true;
        } else return false;
    }

    /**
     * Este médodo llama al método de la clase superior y añade la información que falta y corresponde el tipo de cuenta
     * @return
     */
    @Override
    public String devolverInfoString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.devolverInfoString());
        sb.append("Interés de Remuneración: ").append(interesRemuneracion).append(System.lineSeparator());
        return sb.toString();
    }
}
