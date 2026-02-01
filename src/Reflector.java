package src;

import static src.Globals.ALPHABET_SIZE;

public class Reflector {
    private Rotor lastRotor;
    private int transposition;

    public Reflector(Rotor lastRotor, int transposition) {
        setLastRotor(lastRotor);
        setTransposition(transposition);
    }

    public void setLastRotor(Rotor rotor) {
        lastRotor = rotor;
    }

    public void setTransposition(int transposition) {
        this.transposition = transposition;
    }

    public char reflect(char input) {
        var charIndex = input - 'A';
        if (charIndex < 0 || charIndex >= ALPHABET_SIZE)
            throw new IllegalArgumentException("Character out of range: " + input);

        var transposedIndex = (charIndex + transposition) % ALPHABET_SIZE;
        return  (char) (transposedIndex + 'A');
    }

    public char sendBack(char input) {
        var reflectedChar = reflect(input);

        IO.println("\nReflecting: " + input + " -> " + reflectedChar);

        return lastRotor.cipherCharacterPreviousRotor(reflectedChar);
    }
}
