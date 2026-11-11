package src;

import static src.Alphabet.ALPHABET_SIZE;
import static src.Alphabet.GET_LETTER_INDEX;

public class Rotor {
    private Rotor nextRotor, previousRotor;
    private Reflector reflector = null;

    private int rotorPosition;   // Rotor position, will update after each encoded letter

    private final String wiring;

    public Rotor (int rotorPosition, String wireSetting) {
        this.rotorPosition = rotorPosition % ALPHABET_SIZE;
        this.wiring = wireSetting;
    }

    public void setRotorPosition(int position) { rotorPosition = position % ALPHABET_SIZE; }
    public int getRotorPosition() { return rotorPosition; }
    public void setNextRotor(Rotor input) { nextRotor = input; }
    public Rotor getNextRotor() { return nextRotor; }
    public void setPreviousRotor(Rotor input) { previousRotor = input; }
    public Rotor getPreviuosRotor() { return previousRotor; }
    public void setReflector(Reflector input) { reflector = input; }
    public Reflector getReflector() { return reflector; }
    public String getWiring() { return wiring; }

    public void updateRotorPosition() {
        rotorPosition = (rotorPosition + 1) % ALPHABET_SIZE;
            
        if (rotorPosition == 0 && nextRotor != null) 
            nextRotor.updateRotorPosition();
    }

    public char forwardCipher(char input) {
        var index = GET_LETTER_INDEX(input);
        var transposedIndex = (index + rotorPosition) % ALPHABET_SIZE;

        return wiring.charAt(transposedIndex);
    }
    public char reverseCipher(char input) {
        var indexAtWireSetting = wiring.indexOf(input);
        var transposedIndex = (indexAtWireSetting - rotorPosition) % ALPHABET_SIZE;
        if (transposedIndex < 0) transposedIndex += ALPHABET_SIZE;

        return (char)('A' + transposedIndex);
    }

    public char cipherCharacterNextRotor(char input) {
        var cipherChar = forwardCipher(input);

        return nextRotor != null
            ? nextRotor.cipherCharacterNextRotor(cipherChar)
            : reflector.sendBack(cipherChar);
    }

    public char cipherCharacterPreviousRotor(char input) {
        var cipherChar = reverseCipher(input);

        return previousRotor != null
            ? previousRotor.cipherCharacterPreviousRotor(cipherChar)
            : cipherChar;
    }
}
