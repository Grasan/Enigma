package src;

import static src.Globals.ALPHABET_SIZE;

public class Reflector {
    private Rotor lastRotor;

    public void setLastRotor(Rotor input) { lastRotor = input; }

    public char reflect(char input) {
        var charIndex = input - 'A';
        if (charIndex < 0 || charIndex >= ALPHABET_SIZE)
            throw new IllegalArgumentException("Character out of range: " + input);

        var transposedIndex = (charIndex + 13) % ALPHABET_SIZE;
        var reflectedChar = (char) (transposedIndex + 'A');

        IO.println("\nReflecting: " + input + " -> " + reflectedChar);
        
        return lastRotor.encodeCharacterPreviusRotor(reflectedChar);
    }
}
