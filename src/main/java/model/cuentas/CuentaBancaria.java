package model.cuentas;

import model.Imprimible;
import model.identidades.Cliente;

import java.util.Objects;

/**
 * Esta clase representa una cuenta bancaria genérica.
 * Implementa la funcionalidad básica de toda cuenta bancaria:<br>
 *  1.- Todas las cuentas tienen una numeración (IBAN en el caso del ejercicio)<br>
 *  2.- Todas las cuentas tienen un titular<br>
 *  3.- Todas las cuentas tienen un saldo asociado<br>
 *
 *  Implementa la interfaz Imprimible con la información que contiene. En el resto de las clases se llama a super
 *  para añadir incrementalmente la información de cada clase
 *  También implementa un método interno para comprobar que el IBAN introducido es correcto
 *  mediante una expresión regular, según se indica en el ejercicio.<br>
 *  En esta y en prácticamente todas las clases que necesitan una comparación se han sobreescrito los métodos
 *  equals() y hashCode() de manera que se puedan realizar comparaciones con facilidad. Para ello se han elegido, en
 *  cada clase, los parámetros más representativos o unívocos para ambos métodos.
 *
 */
public abstract class CuentaBancaria implements Imprimible {

    private Cliente titular;
    protected double saldo;
    private Iban iban;

    public Cliente getTitular() {
        return titular;
    }

    public void setTitular(Cliente titular) {
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void addSaldo(double cantidad){
        saldo += cantidad;
    }

    /**
     * El método para retirar saldo de una cuenta se declara abstracto y sin implementar.
     * Cada cuenta debe implementar su manera de retirar saldo, ya que hay cuentas que permiten descubierto y
     * otras no.
     * @param cantidad
     * @return
     */
    public abstract boolean substractSaldo(double cantidad);

    public Iban getIban() {
        return iban;
    }

    public void setIban(Iban iban) {
        this.iban = iban;
    }

    @Override
    public String devolverInfoString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Número de cuenta: ").append(getIban()).append(System.lineSeparator());
        sb.append("Titular: ").append(titular.devolverInfoString()).append(System.lineSeparator());
        sb.append("Saldo: ").append(getSaldo()).append(System.lineSeparator());
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CuentaBancaria that = (CuentaBancaria) o;
        return Objects.equals(iban, that.iban);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iban);
    }
}
