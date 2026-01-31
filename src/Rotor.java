package src;

import static src.Globals.ALPHABET_SIZE;
import static src.Globals.WIRE_SETTING;

public class Rotor {
    private Rotor nextRotor, previousRotor;
    private Reflector reflector = null;

    private int rotorPosition;   // Rotor position, will update after each encoded letter

    private final String wiring;

    public Rotor (int rotorPosition, int wireSetting) {
        this.rotorPosition = rotorPosition % ALPHABET_SIZE;
        this.wiring = WIRE_SETTING[wireSetting - 1].toUpperCase();
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

    private void displayEncodingResult(char inputChar, char encodedChar) {
        IO.print(": " + inputChar + " -> " + encodedChar + " :");
    }

    // Recursive methods calling the rotors to the left/right passing on the encoded char
    public char encodeCharacterNextRotor(char input) {
        var index = input - 'A';

        var transposedIndex = (index + rotorPosition) % ALPHABET_SIZE;

        var encodedChar = wiring.charAt(transposedIndex);

        displayEncodingResult(input, encodedChar);

        return nextRotor != null
            ? nextRotor.encodeCharacterNextRotor(encodedChar)
            : reflector.reflect(encodedChar);
    }

    public char encodeCharacterPreviousRotor(char input) {
        var indexAtWireSetting = wiring.indexOf(input);
        var transposedIndex = (indexAtWireSetting - rotorPosition) % ALPHABET_SIZE;
        if (transposedIndex < 0) transposedIndex += ALPHABET_SIZE;

        var result = (char)('A' + transposedIndex);

        displayEncodingResult(input, result);

        return previousRotor != null
            ? previousRotor.encodeCharacterPreviousRotor(result)
            : result;
    }
}
