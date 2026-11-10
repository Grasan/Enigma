package test;

import org.junit.jupiter.api.Test;
import src.Reflector;
import src.Rotor;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReflectorTester {
    Rotor rotor = new Rotor(0, 0);

    @Test
    public void standardReflection() {
        Reflector reflector = new Reflector(rotor);

        var testChar = 'A';
        var reflectedChar = reflector.reflect(testChar);

        assertEquals('N', reflectedChar);
    }

    @Test
    public void customReflection() {
        Reflector reflector = new Reflector(rotor, 20);

        var testChar = 'A';
        var reflectedChar = reflector.reflect(testChar);

        assertEquals('U', reflectedChar);
    }
}
