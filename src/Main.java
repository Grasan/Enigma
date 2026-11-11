package src;

import javafx.util.Pair;

import java.util.ArrayList;

public class Main {
    public static EnigmaMachine machine;

    private static String message = "This is a secret message".toUpperCase();
    private static String cipher;
    private static String decipher;

    // Legit wiring for enigma rotors, check en.wikipedia.org/wiki/Enigma_rotor_details
    private static final String[] STANDARD_WIRE_SETTINGS = {
        "EKMFLGDQVZNTOWYHXUSPAIBRCJ",   // Model: Enigma 1, Rotor# I
        "AJDKSIRUXBLHWTMCQGZNPYFVOE",   // Model: Enigma 1, Rotor# I
        "BDFHJLCPRTXVZNYEIWGAKMUSQO"    // Model: Enigma 1, Rotor# III
    };

    void main() {
        ArrayList<Pair<Integer, String>> rotorSettings = new ArrayList<>();
        rotorSettings.add(new Pair<>(0, STANDARD_WIRE_SETTINGS[0]));
        rotorSettings.add(new Pair<>(0, STANDARD_WIRE_SETTINGS[1]));
        rotorSettings.add(new Pair<>(0, STANDARD_WIRE_SETTINGS[2]));

        ArrayList<Pair<Character, Character>> plugboardSettings = new ArrayList<>();
        plugboardSettings.add(new Pair<>('A', 'H'));
        plugboardSettings.add(new Pair<>('F', 'Y'));
        plugboardSettings.add(new Pair<>('V', 'Q'));
        plugboardSettings.add(new Pair<>('R', 'N'));
        plugboardSettings.add(new Pair<>('L', 'T'));

        machine = new EnigmaMachine(rotorSettings, plugboardSettings);

        // Testing the encoding here.
        firstRun(message);

        // Reset the machines rotor positions
        machine.resetMachine();

        secondRun(cipher);
    }

    private static void firstRun(String message) {
        IO.println("Message to cipher: " + message);
        cipher = machine.cipherMessage(message);
        IO.println("Result: " + cipher);
    }

    private static void secondRun(String message) {
        IO.println("Message to de-cipher: " + message);
        decipher = machine.cipherMessage(message);
        IO.println("Result: " + decipher);
    }
}