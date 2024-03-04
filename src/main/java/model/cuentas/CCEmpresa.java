package model.cuentas;

import model.identidades.Empresa;

/**
 * Clase que implementa la funcionalidad completa de una cuenta corriente de empresa.
 * Permite las funciones respecto del descubierto y comisiones indicadas en el ejercicio.
 *
 * Al igual que las demás, sobreescribe la interfaz Imprimible para añadir la información propia de la cuenta
 *
 */
public class CCEmpresa extends CuentaCorriente{
    private double descubiertoPermitido;
    private double interesPorDescubierto;
    private double comisionFijaPorCadaDescubierto;

    public CCEmpresa(Iban iban, Empresa titular){
        super.setTitular(titular);
        super.setIban(iban);
    }

    public double getDescubiertoPermitido() {
        return descubiertoPermitido;
    }

    public void setDescubiertoPermitido(double descubiertoPermitido) {
        this.descubiertoPermitido = descubiertoPermitido;
    }

    public double getInteresPorDescubierto() {
        return interesPorDescubierto;
    }

    public void setInteresPorDescubierto(double interesPorDescubierto) {
        this.interesPorDescubierto = interesPorDescubierto;
    }

    public double getComisionFijaPorCadaDescubierto() {
        return comisionFijaPorCadaDescubierto;
    }

    public void setComisionFijaPorCadaDescubierto(double comisionFijaPorCadaDescubierto) {
        this.comisionFijaPorCadaDescubierto = comisionFijaPorCadaDescubierto;
    }

    @Override
    public boolean substractSaldo(double cantidad) {
        if (cantidad <= saldo + descubiertoPermitido) {
            saldo -= cantidad;
            return true;
        } else
            return false;
    }

    @Override
    public String devolverInfoString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.devolverInfoString());
        sb.append("Descubierto permitido: ").append(descubiertoPermitido).append(System.lineSeparator());
        sb.append("Interés por descubierto: ").append(interesPorDescubierto).append(System.lineSeparator());
        sb.append("Comisión fija por cada descubierto: ").append(comisionFijaPorCadaDescubierto).append(System.lineSeparator());
        return sb.toString();
    }
}
