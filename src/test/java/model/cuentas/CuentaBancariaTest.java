package model.cuentas;

import model.identidades.Empresa;
import model.identidades.Particular;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CuentaBancariaTest {

    CCEmpresa cce1,cce2;
    CCPersonal ccp1,ccp2;

    String iban1, iban2;

    Empresa empresa;

    @BeforeEach
    void initEquals(){
        empresa = new Empresa("B54191234S");
        iban1 = "ES1234123412341234123412";
        iban2 = "ES1234123412341234123413";
        cce1 = new CCEmpresa(new Iban(iban1),empresa);
        ccp1 = new CCPersonal(new Iban(iban2),new Particular("543419847T"));
        ccp2 = new CCPersonal(new Iban(iban1),new Particular("543419847T"));
    }
    @Test
    void testDifferentAccountTypesWithSameIban() {
      assertEquals(cce1,ccp2);
    }

    @Test
    void testDifferentIbanFails(){
        assertNotEquals(ccp1,ccp2);
    }
}