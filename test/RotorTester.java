package test;

import org.testng.annotations.Test;
import src.Rotor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static src.Globals.WIRE_SETTING;

public class RotorTester {
    Rotor rotor1 = new Rotor(0, 1),
        rotor2 = new Rotor(0, 2);
    
    @Test
    public void testSimpleCharEncoding() {
        // Setup
        rotor1.setRotorPosition(0);
        char testChar = 'A';
        
        // Testing function
        char encodedChar = rotor1.encodeCharacter(testChar);

        // Result
        assertEquals(WIRE_SETTING[0].charAt(0), encodedChar);
    }

    @Test
    public void testEncodingAtSpecificRotorSetting() {
        // Setup
        int position = 1;
        rotor1.setRotorPosition(position);
        char testChar = 'A';

        // Testing function
        char encodedChar = rotor1.encodeCharacter(testChar);

        // Result
        assertEquals(WIRE_SETTING[0].charAt(position), encodedChar);
    }

    @Test
    public void rotorPositionSettingModulo() {
        // Setup
        int position = 26;

        // Testing function
        rotor1.setRotorPosition(position);

        // Result
        assertEquals(0, rotor1.getRotorPosition());
    }

    @Test
    public void rotorRevolution() {
        // Setup
        rotor1.setRotorPosition(25);

        // Testing function
        rotor1.updateRotorPosition();

        // Result
        assertEquals(0, rotor1.getRotorPosition());
    }

    @Test
    public void rotorRevolutionEffectNextRotor() {
        // Setup
        rotor1.setNextRotor(rotor2);
        rotor1.setRotorPosition(25);

        // Testing function
        rotor1.updateRotorPosition();

        // Result
        assertEquals(1, rotor2.getRotorPosition());
    }
}
