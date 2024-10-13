package src;

public class Rotor {
    private String name;
    private Rotor nextRotor, previousRotor;
    private Reflector reflector = null;

    private int rotorPosition;   // Rotor position, will update after each encoded letter
    private String wiring;  // The internal wiring for the rotor, check en.wikipedia.org/wiki/Enigma_rotor_details 
    private static final int ALPHABET_SIZE = 26;

    public Rotor (int rotorPosition, String wiring, String name) {
        this.rotorPosition = rotorPosition % ALPHABET_SIZE;
        this.wiring = wiring.toUpperCase();
        this.name = name;
    }

    public void SetNextRotor(Rotor input) { nextRotor = input; }
    public Rotor GetNextRotor() { return nextRotor; }
    public void SetPreviusRotor(Rotor input) { previousRotor = input; }
    public Rotor GetPreviuosRotor() { return previousRotor; }
    public void SetReflector(Reflector input) { reflector = input; }
    public Reflector getReflector() { return reflector; }

    public void UpdateRotorPosition() {
        rotorPosition = (rotorPosition + 1) % ALPHABET_SIZE;

        if (rotorPosition == 0 && nextRotor != null) 
            nextRotor.UpdateRotorPosition();
    }

    /**
     * This function will encrypt the input character in a similar way the enigma rotors do.
     * @param charToEncrypt Character to be encryptet
     * @return
     */
    public char EncodeCharacter(char charToEncrypt) {
            // Get index of character in the alphabet
        int charIndex = charToEncrypt - 'A';
        if (charIndex < 0 || charIndex >= ALPHABET_SIZE)
            throw new IllegalArgumentException("Character out of range: " + charToEncrypt);

            // the alphabet position and current position of the this rotor 
        int transposedIndex = (charIndex + rotorPosition) % ALPHABET_SIZE;  


        char encodedChar = wiring.charAt(transposedIndex);
        int encodedIndex = (encodedChar - 'A' - rotorPosition + ALPHABET_SIZE) % ALPHABET_SIZE;

        System.out.println("(" + name +") " + "char at " + transposedIndex + " = " + encodedChar + " : ");

        return (char) (encodedIndex + 'A');
    }

    // Recursive methods calling the rotors to the left/right passing on the encoded char
    public char EncodeCharacterNextRotor(char charToEncrypt) {
        char encodedChar = EncodeCharacter(charToEncrypt);

        if (nextRotor != null)
            return nextRotor.EncodeCharacterNextRotor(encodedChar);
        else
            return reflector.Reflect(encodedChar);
    }
    public char EncodeCharacterPreviusRotor(char charToEncrypt) {
        char encodedChar = EncodeCharacter(charToEncrypt);

        if (previousRotor != null)
            return previousRotor.EncodeCharacterPreviusRotor(encodedChar);
        else 
            return encodedChar;
    }
}
