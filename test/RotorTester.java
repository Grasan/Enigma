package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.Rotor;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RotorTester {

    private static final String[] EXPECTED_WIRING = {
            "EKMFLGDQVZNTOWYHXUSPAIBRCJ",   // Model: Enigma 1, Rotor# I
            "AJDKSIRUXBLHWTMCQGZNPYFVOE",   // Model: Enigma 1, Rotor# I
            "BDFHJLCPRTXVZNYEIWGAKMUSQO"    // Model: Enigma 1, Rotor# III
    };

    private Rotor rotor1 = new Rotor(0, EXPECTED_WIRING[0]);
    private Rotor rotor2 = new Rotor(0, EXPECTED_WIRING[1]);
    private Rotor rotor3 = new Rotor(0, EXPECTED_WIRING[2]);

    @BeforeEach
    public void setup() {
        rotor1 = new Rotor(0, EXPECTED_WIRING[0]);
        rotor2 = new Rotor(0, EXPECTED_WIRING[1]);
        rotor3 = new Rotor(0, EXPECTED_WIRING[2]);

        rotor1.setNextRotor(rotor2);
        rotor2.setNextRotor(rotor3);
        rotor2.setPreviousRotor(rotor1);
        rotor3.setPreviousRotor(rotor2);
    }

    @Test
    public void simpleCharForwardCipher() {
        rotor1.setRotorPosition(0);

        char encryptedChar = rotor1.forwardCipher('A');

        assertEquals('E', encryptedChar);
    }

    @Test
    public void completeForwardWiring() {
        rotor1.setRotorPosition(0);

        for (int i = 0; i < 26; i++) {
            char input = (char) ('A' + i);
            char expected = EXPECTED_WIRING[0].charAt(i);

            assertEquals(expected, rotor1.forwardCipher(input));
        }
    }

    @Test
    public void forwardAndReverseCipherAreInverses() {
        rotor1.setRotorPosition(0);

        for (char c = 'A'; c <= 'Z'; c++) {
            char encrypted = rotor1.forwardCipher(c);
            char decrypted = rotor1.reverseCipher(encrypted);

            assertEquals(c, decrypted);
        }
    }

    @Test
    public void simpleCharReverseCipher() {
        rotor1.setRotorPosition(0);

        char encryptedChar = rotor1.reverseCipher('A');

        // Important: 13 is the standard reflector transposition.
        assertEquals(EXPECTED_WIRING[0].charAt(4 + 13), encryptedChar);
    }

    @Test
    public void forwardCipherAtSpecificRotorPosition() {
        int position = 5;
        rotor1.setRotorPosition(position);

        char encryptedChar = rotor1.forwardCipher('A');

        assertEquals(EXPECTED_WIRING[0].charAt(position), encryptedChar);
    }

    @Test
    public void reversedCipherAtSpecificRotorPosition() {
        int position = 5;
        rotor1.setRotorPosition(position);

        char encryptedChar = rotor1.reverseCipher('A');

        // Important: 13 is the standard reflector transposition.
        assertEquals(EXPECTED_WIRING[0].charAt(6 + 13), encryptedChar);
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
    public void rotorRevolutionEffectAllRotors() {
        rotor1.setRotorPosition(25);
        rotor2.setRotorPosition(25);
        rotor3.setRotorPosition(0);

        rotor1.updateRotorPosition();

        assertAll(
                () -> assertEquals(0, rotor1.getRotorPosition()),
                () -> assertEquals(0, rotor2.getRotorPosition()),
                () -> assertEquals(1, rotor3.getRotorPosition())
        );
    }
}
