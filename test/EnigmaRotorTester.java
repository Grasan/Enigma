package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import src.*;

public class EnigmaRotorTester {
    
    @Test
    public void TestSimpleCharEncoding() {
        Rotor r = new Rotor(0, Main.rotorWiring[0]);
        char testChar = 'A';

        assertEquals(Main.rotorWiring[0].charAt(0), r.EncodeCharacter(testChar));

    }

    @Test
    public void TestEncodingAtSpecifikRotorSetting() {
        Rotor r = new Rotor(15, Main.rotorWiring[0]);
        char testChar = 'A';

        assertEquals(Main.rotorWiring[0].charAt(15), r.EncodeCharacter(testChar));
    }
}
