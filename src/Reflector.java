package src;

public class Reflector {
    private Rotor lastRotor;
    private static final int ALPHABET_SIZE = 26;

    public void SetLastRotor(Rotor input) { lastRotor = input; }

    public char Reflect(char input) {
        int charIndex = input - 'A';
        if (charIndex < 0 || charIndex >= ALPHABET_SIZE)
            throw new IllegalArgumentException("Character out of range: " + input);

        int transposedIndex = (charIndex + 13) % ALPHABET_SIZE;
        char reflectedChar = (char) (transposedIndex + 'A');

        System.out.println("Reflecting: " + input + " -> " + reflectedChar);
        
        return lastRotor.EncodeCharacterPreviusRotor(reflectedChar);
    }
}
