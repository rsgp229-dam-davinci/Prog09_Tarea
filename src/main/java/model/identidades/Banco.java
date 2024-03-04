package model.identidades;

import model.cuentas.CCPersonal;
import model.cuentas.CuentaAhorro;
import model.cuentas.CuentaBancaria;
import model.cuentas.Iban;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/**
 * Clase que representa una entidad Bancaria.
 * <p>
 * Para la ejecución del ejercicio 08, se ha dejado el código anterior entre comentarios
 * para poder compararlo con más facilidad.
 */
public class Banco {

    private CCPersonal fake;
    private Map<Iban, CuentaBancaria> cuentasEntidad;
    //private CuentaBancaria[] cuentas = new CuentaBancaria[100];

    public Banco() {
        cuentasEntidad = new HashMap<>(100);
    }


    public boolean abrirCuenta(CuentaBancaria nuevaCuenta) {
        if (nuevaCuenta != null && !cuentasEntidad.containsKey(nuevaCuenta.getIban())) {
            cuentasEntidad.put(nuevaCuenta.getIban(), nuevaCuenta);
            return true;
        }
        return false;

        /* Código anterior
        for (int i = 0; i < cuentas.length; i++) {
            if (cuentas[i] == null) {
                cuentas[i] = nuevaCuenta;
                return true;
            }
        }
        return false;*/
    }


    public String[] listadoCuentas() {
        String[] infoCuentas = new String[cuentasEntidad.size()];
        int counter = 0;
        Iterator<CuentaBancaria> accountIterator = cuentasEntidad.values().iterator();
        while (accountIterator.hasNext()) {
            infoCuentas[counter] = accountIterator.next().devolverInfoString();
            counter++;
        }
        return infoCuentas;

        /*Código anterior
        int openAccounts = 0;
        for (CuentaBancaria cuenta : cuentas) {
            if (cuenta != null) {
                openAccounts++;
            }
        }
        String[] infoCuentas = new String[openAccounts];
        for (int i = 0, x = 0; i < cuentas.length; i++) {
            if (cuentas[i] != null) {
                infoCuentas[x] = cuentas[i].devolverInfoString();
                x++;
            }
        }
        return infoCuentas;*/
    }

    public String informacionCuenta(Iban iban) {
        if (iban != null) {
            CuentaBancaria cuenta = cuentasEntidad.get(iban);
            if (cuenta != null) {
                return cuenta.devolverInfoString();
            }
        }
        return null;

        /*Código anterior
        for (CuentaBancaria cuenta : cuentas) {
            if (cuenta != null && cuenta.getIban().equals(iban)) {
                return cuenta.devolverInfoString();
            }
        }
        return null;*/
    }

    public boolean ingresoCuenta(Iban iban, double amount) {
        if (iban != null) {
            CuentaBancaria cuenta = cuentasEntidad.get(iban);
            if (cuenta != null) {
                cuenta.addSaldo(amount);
                return true;
            }
        }
        return false;
        /* Código anterior
        for (CuentaBancaria cuenta : cuentasEntidad) {
            if (cuenta != null && cuenta.getIban().equals(iban)) {
                cuenta.addSaldo(amount);
                return true;
            }
        }
        return false;*/
    }

    public boolean retiradaCuenta(Iban iban, double amount) {
        if (iban == null) return false;
        if (cuentasEntidad.containsKey(iban)) {
            return cuentasEntidad.get(iban).substractSaldo(amount);
        }
        return false;

        /*for (CuentaBancaria cuenta : cuentasEntidad){
            if (cuenta != null && cuenta.getIban().equals(iban)) {
                return cuenta.substractSaldo(amount);
            }
        }
        return false;*/
    }

    public double obtenerSaldo(Iban iban) {
        if (iban != null) {
            CuentaBancaria cuenta = cuentasEntidad.get(iban);
            if (cuenta != null) {
                return cuenta.getSaldo();
            }
        }
        return 0.0;

        /*if (iban != null){
            for (CuentaBancaria cuenta : cuentasEntidad) {
                if (cuenta != null && cuenta.getIban().equals(iban)) {
                    return cuenta.getSaldo();
                }
            }
        }
        return 0.0;*/
    }

    public boolean existeCuenta(Iban iban) {
        return cuentasEntidad.containsKey(iban);

        /* Código anterior
        for (CuentaBancaria cuenta : cuentasEntidad) {
            if (cuenta != null && cuenta.getIban().equals(iban)) {
                return true;
            }
        }
        return false;*/
    }

    /**
     * Método nuevo para el ejercicio 8.
     * Permite eliminar una cuenta bancaria si el saldo es superior a 0
     * @param iban La cuenta a eliminar
     * @return True si se ha podido eliminar, false en cualquier otro caso
     */
    public boolean eliminarCuenta(Iban iban) {
        if (iban == null) return false;
        if (cuentasEntidad.containsKey(iban)) {
            if (cuentasEntidad.get(iban).getSaldo() > 0.0)
                return false;
            else {
                cuentasEntidad.remove(iban);
                return true;
            }
        } else
            return false;
    }
}
