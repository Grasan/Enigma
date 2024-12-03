package src;

public class Rotor {
    private Rotor nextRotor, previousRotor;
    private Reflector reflector = null;

    private int rotorPosition;   // Rotor position, will update after each encoded letter
    // Legit wiring for enigma rotors, check en.wikipedia.org/wiki/Enigma_rotor_details 
    private String[] wireSettings = {
        "EKMFLGDQVZNTOWYHXUSPAIBRCJ",   // Model: Enigma 1, Rotor# I
        "AJDKSIRUXBLHWTMCQGZNPYFVOE",   // Model: Enigma 1, Rotor# II
        "BDFHJLCPRTXVZNYEIWGAKMUSQO"    // Model: Enigma 1, Rotor# III
    };
    // The internal wiring for the rotor, check en.wikipedia.org/wiki/Enigma_rotor_details
    private String wiring;   
    private static final int ALPHABET_SIZE = 26;

    public Rotor (int rotorPosition, int wireSetting) {
        this.rotorPosition = rotorPosition % ALPHABET_SIZE;
        this.wiring = wireSettings[wireSetting - 1].toUpperCase();
    }

    public void setRotorPosition(int position) { rotorPosition = position % ALPHABET_SIZE; }
    public int getRotorPosition() { return rotorPosition; }
    public void setNextRotor(Rotor input) { nextRotor = input; }
    public Rotor getNextRotor() { return nextRotor; }
    public void setPreviusRotor(Rotor input) { previousRotor = input; }
    public Rotor getPreviuosRotor() { return previousRotor; }
    public void setReflector(Reflector input) { reflector = input; }
    public Reflector getReflector() { return reflector; }
    public String getWiring() { return wiring; }

    public void updateRotorPosition() {
        rotorPosition = (rotorPosition + 1) % ALPHABET_SIZE;
            
        if (rotorPosition == 0 && nextRotor != null) 
            nextRotor.updateRotorPosition();
    }

    /**
     * This function will encrypt the input character in a similar way the enigma rotors do.
     * @param charToEncrypt Character to be encryptet
     * @return
     */
    public char encodeCharacter(char charToEncrypt) {
        // Get index of character in the alphabet
        int charIndex = charToEncrypt - 'A';

        // the alphabet position and current position of the this rotor 
        int transposedIndex = (charIndex + rotorPosition) % ALPHABET_SIZE;  

        char encodedChar = wiring.charAt(transposedIndex);
        int encodedIndex = (encodedChar - 'A' - rotorPosition + ALPHABET_SIZE) % ALPHABET_SIZE;

        System.out.print(": " + charToEncrypt + " -> " + encodedChar + " :");

        return (char) (encodedIndex + 'A');
    }

    // Recursive methods calling the rotors to the left/right passing on the encoded char
    public char encodeCharacterNextRotor(char charToEncrypt) {
        char encodedChar = encodeCharacter(charToEncrypt);

        if (nextRotor != null)
            return nextRotor.encodeCharacterNextRotor(encodedChar);
        else
            return reflector.reflect(encodedChar);
    }
    public char encodeCharacterPreviusRotor(char charToEncrypt) {
        char encodedChar = encodeCharacter(charToEncrypt);

        if (previousRotor != null)
            return previousRotor.encodeCharacterPreviusRotor(encodedChar);
        else 
            return encodedChar;
    }
}
