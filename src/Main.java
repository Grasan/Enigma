package src;

public class Main {
    public static StringBuilder encodedMessage = new StringBuilder();

    void main() {
        // Rotor setup part 1
        Rotor r1 = new Rotor(0, 1);
        Rotor r2 = new Rotor(0, 2);
        Rotor r3 = new Rotor(0, 3);
        Reflector reflector = new Reflector();

        String message = "hello";

        // Rotor setup part 2
        r1.setNextRotor(r2);
        r2.setNextRotor(r3);
        r2.setPreviousRotor(r1);
        r3.setPreviousRotor(r2);
        r3.setReflector(reflector);
        reflector.setLastRotor(r3);

        // Testing the encoding here.
        encodeStringLinkedList(message, r1);  

        IO.println("Encoded message: " + encodedMessage.toString());
    }

    /**
     * This looks WAY better and uses recursion 
     * 
     * but it doesn't work as intended... yet
     * @param stringToEncrypt
     * @return
     */
    void encodeStringLinkedList(String stringToEncrypt, Rotor head) {
        for (char letter : stringToEncrypt.toCharArray()) {
            if (Character.isLetter(letter)) {
                letter = Character.toUpperCase(letter);

                IO.println("Encoding '" + letter);
                
                letter = head.encodeCharacterNextRotor(letter);
                head.updateRotorPosition();
    
                IO.println("\nResult: " + letter + "\n");
    
                encodedMessage.append(letter);
            }
        }
    }
}