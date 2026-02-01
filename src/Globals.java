package src;
public class Globals {
    public static final int ALPHABET_SIZE = 26;
    public static final int REFLECTOR_STANDARD_TRANSPOSITION = 13;

    // Legit wiring for enigma rotors, check en.wikipedia.org/wiki/Enigma_rotor_details
    public static final String[] WIRE_SETTING = {
            "EKMFLGDQVZNTOWYHXUSPAIBRCJ",   // Model: Enigma 1, Rotor# I
            "AJDKSIRUXBLHWTMCQGZNPYFVOE",   // Model: Enigma 1, Rotor# II
            "BDFHJLCPRTXVZNYEIWGAKMUSQO"    // Model: Enigma 1, Rotor# III
    };

    public static int GET_LETTER_INDEX(char input) {
        return input - 'A';
    }
}
