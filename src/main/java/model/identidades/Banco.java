package model.identidades;

import model.cuentas.CCPersonal;
import model.cuentas.CuentaAhorro;
import model.cuentas.CuentaBancaria;
import model.cuentas.Iban;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/**
 * Clase que representa una entidad Bancaria.
 *
 */
public class Banco {
    private final String PERSISTENCY_FILE_NAME = "datoscuentasbancarias.dat";
    private HashMap<Iban, CuentaBancaria> cuentasEntidad;

    public Banco() {
        cuentasEntidad = loadAccounts();
    }


    public boolean abrirCuenta(CuentaBancaria nuevaCuenta) {
        if (nuevaCuenta != null && !cuentasEntidad.containsKey(nuevaCuenta.getIban())) {
            cuentasEntidad.put(nuevaCuenta.getIban(), nuevaCuenta);
            return true;
        }
        return false;
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
    }

    public String informacionCuenta(Iban iban) {
        if (iban != null) {
            CuentaBancaria cuenta = cuentasEntidad.get(iban);
            if (cuenta != null) {
                return cuenta.devolverInfoString();
            }
        }
        return null;

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
    }

    public boolean retiradaCuenta(Iban iban, double amount) {
        if (iban == null) return false;
        if (cuentasEntidad.containsKey(iban)) {
            return cuentasEntidad.get(iban).substractSaldo(amount);
        }
        return false;
    }

    public double obtenerSaldo(Iban iban) {
        if (iban != null) {
            CuentaBancaria cuenta = cuentasEntidad.get(iban);
            if (cuenta != null) {
                return cuenta.getSaldo();
            }
        }
        return 0.0;
    }

    public boolean existeCuenta(Iban iban) {
        return cuentasEntidad.containsKey(iban);
    }

    /**
     * Permite eliminar una cuenta bancaria si el saldo es 0
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

    public void saveAccounts(){
        File persistencyFile = new File(PERSISTENCY_FILE_NAME);
        try (FileOutputStream fileOS = new FileOutputStream(persistencyFile);
             ObjectOutputStream objectOS = new ObjectOutputStream(fileOS)) {
            objectOS.writeObject(cuentasEntidad);
        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado");
        } catch (IOException e) {
            System.err.println("Ha ocurrido algún otro error. " + e.getMessage());
        }
    }

    private HashMap<Iban, CuentaBancaria> loadAccounts(){
        File persistencyFile = new File((PERSISTENCY_FILE_NAME));
        if (persistencyFile.exists()){
            HashMap<Iban,CuentaBancaria> output;
            try (FileInputStream fileIS = new FileInputStream(persistencyFile);
                 ObjectInputStream objectIS = new ObjectInputStream(fileIS)) {
                output = (HashMap<Iban, CuentaBancaria>) objectIS.readObject();
                return output;
            } catch (FileNotFoundException e) {
                System.err.println("No se ha encontrado el archivo");
            } catch (ClassNotFoundException e) {
                System.err.println("No se ha podido deserializar la clase " + e.getMessage());
            } catch (IOException e) {
                System.err.println("Ha ocurrido algún error inesperado" + e.getMessage());
            }
        }
        return new HashMap<Iban, CuentaBancaria>(100);
    }

}