package test;

import org.testng.annotations.Test;
import src.Rotor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static src.Globals.REFLECTOR_STANDARD_TRANSPOSITION;
import static src.Globals.WIRE_SETTING;

public class RotorTester {
    Rotor rotor1 = new Rotor(0, 0),
        rotor2 = new Rotor(0, 1),
        rotor3 = new Rotor(0, 2);

    @Test
    public void simpleCharForwardCipher() {
        rotor1.setRotorPosition(0);
        char testChar = 'A';

        char encryptedChar = rotor1.forwardCipher(testChar);

        assertEquals(WIRE_SETTING[0].charAt(0), encryptedChar);
    }

    @Test
    public void simpleCharReverseCipher() {
        rotor1.setRotorPosition(0);
        char testChar = 'A';

        char encryptedChar = rotor1.reverseCipher(testChar);

        assertEquals(WIRE_SETTING[0].charAt(4 + REFLECTOR_STANDARD_TRANSPOSITION), encryptedChar);
    }

    @Test
    public void forwardCipherAtSpecificRotorPosition() {
        int position = 5;
        rotor1.setRotorPosition(position);
        char testChar = 'A';

        char encryptedChar = rotor1.forwardCipher(testChar);

        assertEquals(WIRE_SETTING[0].charAt(position), encryptedChar);
    }

    @Test
    public void reversedCipherAtSpecificRotorPosition() {
        int position = 5;
        rotor1.setRotorPosition(position);
        char testChar = 'A';

        char encryptedChar = rotor1.reverseCipher(testChar);

        assertEquals(WIRE_SETTING[0].charAt(6 + REFLECTOR_STANDARD_TRANSPOSITION), encryptedChar);
    }

    @Test
    public void rotorPositionSettingModulo() {
        int position = 26;

        rotor1.setRotorPosition(position);

        assertEquals(0, rotor1.getRotorPosition());
    }

    @Test
    public void rotorRevolution() {
        rotor1.setRotorPosition(25);

        rotor1.updateRotorPosition();

        assertEquals(0, rotor1.getRotorPosition());
    }

    @Test
    public void rotorRevolutionEffectNextRotor() {
        rotor1.setNextRotor(rotor2);
        rotor1.setRotorPosition(25);
        rotor2.setRotorPosition(0);

        rotor1.updateRotorPosition();

        assertEquals(0, rotor1.getRotorPosition());
        assertEquals(1, rotor2.getRotorPosition());
    }

    @Test
    public void rotorRevolutionEffectAllRotors() {
        rotor1.setRotorPosition(25);
        rotor2.setRotorPosition(25);
        rotor3.setRotorPosition(0);

        rotor1.setNextRotor(rotor2);
        rotor2.setNextRotor(rotor3);

        rotor1.updateRotorPosition();

        assertEquals(0, rotor1.getRotorPosition());
        assertEquals(0, rotor2.getRotorPosition());
        assertEquals(1, rotor3.getRotorPosition());
    }
}
