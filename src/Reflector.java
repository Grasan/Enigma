package src;

import static src.Alphabet.ALPHABET_SIZE;

public class Reflector {
    private Rotor lastRotor;
    private int transposition;
    private static final int REFLECTOR_STANDARD_TRANSPOSITION = 13;

    public Reflector(Rotor lastRotor, int transposition) {
        setLastRotor(lastRotor);
        setTransposition(transposition);
    }
    public Reflector(Rotor lastRotor) {
        setLastRotor(lastRotor);
        setTransposition(REFLECTOR_STANDARD_TRANSPOSITION);
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

        return lastRotor.cipherCharacterPreviousRotor(reflectedChar);
    }
}
