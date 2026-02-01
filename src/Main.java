package src;

import static src.Globals.REFLECTOR_STANDARD_TRANSPOSITION;

public class Main {
    public StringBuilder encodedMessage = new StringBuilder();
    public StringBuilder decodedMessage = new StringBuilder();

    final int[] positions = {0, 0, 0};

    void main() {
        // Rotor initialization
        Rotor rotor1 = new Rotor(positions[0], 0);
        Rotor rotor2 = new Rotor(positions[1], 1);
        Rotor rotor3 = new Rotor(positions[2], 2);
        Reflector reflector = new Reflector(rotor3, REFLECTOR_STANDARD_TRANSPOSITION);

        // Rotor setup
        rotor1.setNextRotor(rotor2);
        rotor2.setNextRotor(rotor3);
        rotor2.setPreviousRotor(rotor1);
        rotor3.setPreviousRotor(rotor2);
        rotor3.setReflector(reflector);

        // Testing the encoding here.
        String message = "This is a secret message";

        encodeStringLinkedList(message, rotor1, encodedMessage);
        IO.println("Encoded message: " + encodedMessage.toString());

        // Reset rotor positions
        rotor1.setRotorPosition(positions[0]);
        rotor2.setRotorPosition(positions[1]);
        rotor3.setRotorPosition(positions[2]);

        encodeStringLinkedList(encodedMessage.toString(), rotor1, decodedMessage);
        IO.println("Decoded message: " + decodedMessage.toString());
    }

    void encodeStringLinkedList(String stringToEncrypt, Rotor head, StringBuilder output) {
        for (char letter : stringToEncrypt.toCharArray()) {
            if (letter == ' ') {
                output.append(letter);
            } else {
                letter = Character.toUpperCase(letter);

                IO.println("Encoding '" + letter);

                letter = head.cipherCharacterNextRotor(letter);
                head.updateRotorPosition();

                IO.println("\nResult: " + letter + "\n");

                output.append(letter);
            }
        }
    }
}