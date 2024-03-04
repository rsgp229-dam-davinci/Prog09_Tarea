package model.identidades;

import model.cuentas.Iban;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class IBANTest {
    Iban i1, i2, i3;
    @BeforeEach
    void initEach(){
        i1 = new Iban("ES1234567890123456789012");
        i2 = new Iban("ES1234567890123456789012");
        i3 = new Iban("ES1234547890123456789012");
    }

    @Test
    public void badConstructorArgumentThrowsException(){
        assertThrows(IllegalArgumentException.class, () -> new Iban(""));
        assertThrows(IllegalArgumentException.class, () -> new Iban("ES"));
        assertThrows(IllegalArgumentException.class, () -> new Iban("ES12345478901234567890123"));
        assertThrows(IllegalArgumentException.class, () -> new Iban("es1234547890123456789012"));
        assertThrows(IllegalArgumentException.class, () -> new Iban("001234547890123456789012"));
        assertThrows(IllegalArgumentException.class, () -> new Iban("ESes12345478901234567890"));
        assertThrows(IllegalArgumentException.class, () -> new Iban("LT1234547890123456789012"));
    }

    @Test
    public void testEquals(){
        assertEquals(i1,i2);
    }
    @Test
    public void testNotEquals(){
        assertNotEquals(i1,i3);
    }
    @Test
    public void testHash(){
        assertEquals(i1.hashCode(),i2.hashCode());
    }
}
