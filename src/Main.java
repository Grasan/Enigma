package src;

public class Main {
    public static StringBuilder encodedMessage = new StringBuilder();

    public static void main(String[] args) {

        // Rotor setup part 1
        Rotor r1 = new Rotor(0, 1);
        Rotor r2 = new Rotor(0, 2);
        Rotor r3 = new Rotor(0, 3);
        Reflector reflector = new Reflector();

        String message = "hello";

        // Rotor setup part 2
        r1.setNextRotor(r2);
        r2.setNextRotor(r3);
        r2.setPreviusRotor(r1);
        r3.setPreviusRotor(r2);
        r3.setReflector(reflector);
        reflector.setLastRotor(r3);

        // Testing the encoding here.
        encodeStringLinkedList(message, r1);  
        
        System.out.println("Encoded message: " + encodedMessage.toString());
    }

    /**
     * This looks WAY better and uses recursion 
     * 
     * but it doesnt work as intended... yet
     * @param stringToEncrypt
     * @return
     */
    public static void encodeStringLinkedList(String stringToEncrypt, Rotor head) {
        for (char letter : stringToEncrypt.toCharArray()) {
            if (Character.isLetter(letter)) {
                letter = Character.toUpperCase(letter);

                System.out.println("Encoding '" + letter);
                
                letter = head.encodeCharacterNextRotor(letter);
                head.updateRotorPosition();
    
                System.out.println("\nResult: " + letter + "\n");
    
                encodedMessage.append(letter);
            }
        }
    }
}