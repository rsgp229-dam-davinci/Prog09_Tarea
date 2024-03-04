package model.cuentas;

import model.identidades.Particular;

/**
 *  Implementación concreta de una Cuenta Personal. No permite descubierto pero sí el cobro de comisión de mantenimiento
 *
 *  Al igual que las demás, sobreescribe la interfaz Imprimible para añadir la información propia del tipo de cuenta.
 */
public class CCPersonal extends CuentaCorriente{
    private double comisionMantenimiento;

    public CCPersonal (Iban iban, Particular cliente){
        super.setIban(iban);
        super.setTitular(cliente);
        this.comisionMantenimiento = 0;
    }

    public double getComisionMantenimiento() {
        return comisionMantenimiento;
    }

    public void setComisionMantenimiento(double comisionMantenimiento) {
        this.comisionMantenimiento = comisionMantenimiento;
    }

    @Override
    public boolean substractSaldo(double cantidad) {
        if (saldo >= cantidad){
            saldo-= cantidad;
            return true;
        } else
            return false;


    }

    @Override
    public String devolverInfoString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.devolverInfoString());
        sb.append("Comisión de Mantenimiento: ").append(comisionMantenimiento).append(System.lineSeparator());
        return sb.toString();
    }

}
