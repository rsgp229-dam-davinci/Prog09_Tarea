package model.identidades;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    Cliente c1,c2;
    @BeforeEach
    public void initEach(){
        c1 = new Cliente() {
            @Override
            public String devolverInfoString() {
                return "Testing";
            }
        };

        c2 = new Cliente() {
            @Override
            public String devolverInfoString() {
                return "Testing";
            }
        };
    }

   @Test
    void testHashCode() {
        c1.setDocumento("1234");
        c2.setDocumento("1234");
        assertEquals(c1.hashCode(), c2.hashCode());
    }

    @Test
    void testHashCodeFail(){
        c1.setDocumento("1234");
        c2.setDocumento("1235");
        assertNotEquals(c1.hashCode(),c2.hashCode());
    }
}