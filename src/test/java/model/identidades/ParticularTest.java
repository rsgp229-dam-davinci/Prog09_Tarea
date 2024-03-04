package model.identidades;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParticularTest {

    Particular p1, p2, p3, p4, p5;
    @BeforeEach
    public void initEach(){
        p1 = new Particular("12345");
        p2 = new Particular("12345");
        p3 = new Particular("123456");
        p4 = new Particular("123456AB");
        p5 = new Particular("123456ab");
    }

    @Test
    void badConstructorArgumentsThrowsException(){
        String[] s = {null, ""};
        for (String test : s){
            assertThrows(IllegalArgumentException.class, () -> new Particular(test));
        }
    }
    @Test
    void testEqualsWithEquals() {
        assertEquals(p1,p2);
        assertEquals(p4,p5);
    }

    @Test
    void testEqualsWithNoEquals(){
        assertNotEquals(p1,p3);
        assertNotEquals(p1,p4);
        assertNotEquals(p3,p4);
    }

    @Test
    void devolverInfoString() {
    }
}