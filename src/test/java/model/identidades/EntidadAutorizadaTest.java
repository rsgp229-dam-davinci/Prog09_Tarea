package model.identidades;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntidadAutorizadaTest {

    @BeforeEach
    public void initEach(){
        EntidadAutorizada e1 = new EntidadAutorizada("Telefónica");
    }
    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }
}