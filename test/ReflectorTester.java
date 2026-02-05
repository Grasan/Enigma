package test;

import org.testng.annotations.Test;
import src.Reflector;
import src.Rotor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static src.Globals.REFLECTOR_STANDARD_TRANSPOSITION;

public class ReflectorTester {
    Rotor rotor = new Rotor(0, 0);
    Reflector reflector = new Reflector(rotor, 0);

    @Test
    public void standardReflection() {
        reflector.setTransposition(REFLECTOR_STANDARD_TRANSPOSITION);

        var testChar = 'A';
        var reflectedChar = reflector.reflect(testChar);

        assertEquals('N', reflectedChar);
    }

    @Test
    public void customReflection() {
        reflector.setTransposition(20);

        var testChar = 'A';
        var reflectedChar = reflector.reflect(testChar);

        assertEquals('U', reflectedChar);
    }
}
