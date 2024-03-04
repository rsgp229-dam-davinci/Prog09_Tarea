package controlador;

import model.cuentas.CCEmpresa;
import model.cuentas.CCPersonal;
import model.cuentas.CuentaAhorro;
import model.cuentas.Iban;
import model.identidades.Banco;
import model.identidades.Empresa;
import model.identidades.Particular;
import vista.Menus;

/**
 * Esta clase hace de controlador de de las operaciones que se solicitan en el ejercicio. Implementa exclusivamente
 * lo solicitado.
 *
 * @author Rafael SGP
 */
public class Program {
    private Banco banco;
    public void iniciar(Banco banco){
        this.banco = banco;
        menuPrincipal();
    }

    /**
     * Método que presenta el menú principal y realiza las llamadas al resto de las funciones.
     *
     * El código es bastante sencillo, tanto en esta como en los demás métodos, por lo que se omiten los
     * comentarios en el resto de métodos.
     */
    private void menuPrincipal(){
        int option = -1;
        while (option != 0){
            Menus.showOptionsWithTitle(new String[]{"Salir", "Abrir nueva cuenta",
                    "Ver cuentas disponibles", "Obtener datos de una cuenta", "Ingresar en cuenta",
                    "Retirar de una cuenta", "Consultar saldo"},"Menú Principal");
            option = InputHelper.getInteger("Introduzca una opción", 6);
            switch (option) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    abrirCuentaNueva();
                    break;
                case 2:
                    listarCuentas();
                    break;
                case 3:
                    mostrarDetalleCuenta();
                    break;
                case 4:
                    ingresarFondos();
                    break;
                case 5:
                    retirarFondos();
                    break;
                case 6:
                    consultarSaldo();
                    break;
            }
        }
    }

    private void consultarSaldo() {
        Iban iban = askIban();
        if (banco.existeCuenta(iban)) {
            double saldo = banco.obtenerSaldo(iban);
            System.out.println("El saldo de la cuenta es: " + saldo);
        } else {
            System.out.println("La cuenta indicada no existe");
        }
    }

    private void retirarFondos() {
        Iban iban = askIban();
        if (banco.existeCuenta(iban)) {
            int amount = InputHelper.getInteger("Introduzca la cantidad a retirar", Integer.MAX_VALUE);
            boolean result = banco.retiradaCuenta(iban, amount);
            if (result) {
                System.out.println("Retirada realizada correctamente. El saldo actual de la cuenta es " + banco.obtenerSaldo(iban));
            } else {
                System.out.println("No se pudo realizar la retirada. El saldo actual de la cuenta es " + banco.obtenerSaldo(iban));
            }
        } else {
            System.out.println("La cuenta indicada no existe");
        }
    }

    private void ingresarFondos() {
        Iban iban = askIban();
        if (banco.existeCuenta(iban)) {
            int amount = InputHelper.getInteger("Introduzca la cantidad a ingresar en la cuenta:", Integer.MAX_VALUE);
            boolean result = banco.ingresoCuenta(iban, amount);
            if (result) {
                System.out.println("Ingreso realizado correctamente");
            } else {
                System.out.println("No se pudo realizar el ingreso");
            }
        } else {
            System.out.println("La cuenta indicada no existe");
        }
    }

    private void mostrarDetalleCuenta() {
        Iban iban = askIban();
        if (banco.existeCuenta(iban)){
            String infoCuenta = banco.informacionCuenta(iban);
            System.out.println(infoCuenta);
        } else
            System.out.println("La cuenta no existe.");
    }

    private void listarCuentas() {
        String[] cuentas = banco.listadoCuentas();
        for (String cuenta : cuentas) {
            System.out.println(cuenta);
            System.out.println("--------------------------------");
        }
    }

    private void abrirCuentaNueva(){
        int selection = -1;
        while (selection != 0){
            Menus.showOptionsWithTitle(new String[]{"Salir","Cuenta de Ahorro","Cuenta Corriente Particular",
                    "Cuenta Corriente de Empresa"}, "Abrir cuenta");
            selection = InputHelper.getInteger("Indique el tipo de cuenta", 3);
            switch (selection) {
                case 0:
                    break;
                case 1:
                    abrirCuentaAhorro();
                    break;
                case 2:
                    abrirCuentaParticular();
                    break;
                case 3:
                    abrirCuentaEmpresa();
                    break;
            }
        }
    }

    private void abrirCuentaAhorro(){
        Particular titular = createParticular();
        Iban iban = askIban();
        CuentaAhorro nuevaCuenta = new CuentaAhorro(iban,titular);
        boolean cuentaAbierta = banco.abrirCuenta(nuevaCuenta);
        if (cuentaAbierta) {
            System.out.println("Cuenta de Ahorro abierta correctamente");
        } else {
            System.out.println("No se pudo abrir la cuenta");
        }
    }

    private void abrirCuentaParticular(){
        Particular titular = createParticular();
        Iban iban = askIban();
        CCPersonal nuevaCuenta = new CCPersonal(iban,titular);
        boolean cuentaAbierta = banco.abrirCuenta(nuevaCuenta);
        if (cuentaAbierta) {
            System.out.println("Cuenta Particular abierta correctamente");
        } else {
            System.out.println("No se pudo abrir la cuenta");
        }
    }

    private void abrirCuentaEmpresa(){
        Empresa titular = createEmpresa();
        Iban iban = askIban();
        CCEmpresa nuevaCuenta = new CCEmpresa(iban,titular);
        boolean cuentaAbierta = banco.abrirCuenta(nuevaCuenta);
        if (cuentaAbierta) {
            System.out.println("Cuenta Corriente de Empresa abierta correctamente");
        } else {
            System.out.println("No se pudo abrir la cuenta");
        }
    }

    private Particular createParticular(){
        Particular particular = null;
        while (particular == null){
            try{
                String dni = InputHelper.getString("Introduzca el DNI del cliente");
                particular = new Particular(dni);
            } catch (IllegalArgumentException e){
                System.out.printf("El DNI introducido no es válido");
            }
        }
        String nombre = InputHelper.getString("Introduzca el nombre del cliente");
        particular.setNombre(nombre);
        String apellidos = InputHelper.getString("Introduzca los apellidos del cliente");
        particular.setApellidos(apellidos);
        return particular;
    }

    private Empresa createEmpresa(){
        Empresa empresa = null;
        while (empresa == null){
            try{
                String cif = InputHelper.getString("Introduzca el CIF de la empresa");
                empresa = new Empresa(cif);
            } catch (IllegalArgumentException e){
                System.out.printf("El CIF introducido no es válido");
            }
        }
        String nombre = InputHelper.getString("Introduzca el nombre de la empresa");
        empresa.setNombre(nombre);
        return empresa;
    }

    private Iban askIban(){
        Iban iban = null;
        while (iban == null){
            String ibanNumber = InputHelper.getString("Introduzca el número de IBAN");
            try {
                iban = new Iban(ibanNumber);
            } catch (IllegalArgumentException e) {
                System.out.println("El número de IBAN introducido no es válido");
            }
        }
        return iban;
    }
}