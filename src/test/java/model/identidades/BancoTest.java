package model.identidades;

import model.cuentas.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BancoTest {
    CCEmpresa cce1,cce2;
    CCPersonal ccp1,ccp2;

    String iban1, iban2;

    Empresa empresa;
    Banco banco;

    @BeforeEach
    void initEquals(){
        banco = new Banco();
        empresa = new Empresa("B54191234S");
        iban1 = "ES1234123412341234123412";
        iban2 = "ES1234123412341234123413";
        cce1 = new CCEmpresa(new Iban(iban1),empresa);
        ccp1 = new CCPersonal(new Iban(iban2),new Particular("543419847T"));
    }
    @Test
    void existeCuenta() {
        banco.abrirCuenta(cce1);
        assertTrue(banco.existeCuenta(new Iban(iban1)));
    }

    @Test
    void testDeleteAccountWithBalanceReturnFalse() {
        banco.abrirCuenta(cce1);
        banco.ingresoCuenta(cce1.getIban(), 120);
        assertFalse(banco.eliminarCuenta(cce1.getIban()));
    }

    @Test
    void testDeleteAccountWithoutBalanceReturnTrue() {
        banco.abrirCuenta(cce1);
        banco.ingresoCuenta(cce1.getIban(), 120);
        banco.retiradaCuenta(cce1.getIban(), 120);
        assertTrue(banco.eliminarCuenta(cce1.getIban()));
    }
}