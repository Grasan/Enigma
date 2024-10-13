package src;

public class Main {
    public static StringBuilder encodedMessage = new StringBuilder();

    public static void main(String[] args) {
        // Legit wiring for enigma rotors, check en.wikipedia.org/wiki/Enigma_rotor_details 
        String[] rotorWiring = {
            "EKMFLGDQVZNTOWYHXUSPAIBRCJ",   // Model: Enigma 1, Rotor# I
            "AJDKSIRUXBLHWTMCQGZNPYFVOE",   // Model: Enigma 1, Rotor# II
            "BDFHJLCPRTXVZNYEIWGAKMUSQO"    // Model: Enigma 1, Rotor# III
        };

        // Rotor setup part 1
        Rotor r1 = new Rotor(0, rotorWiring[0], "R1");
        Rotor r2 = new Rotor(0, rotorWiring[1], "R2");
        Rotor r3 = new Rotor(0, rotorWiring[2], "R3");
        Reflector reflector = new Reflector();

        String message = "eyh";

        // Rotor setup part 2
        r1.SetNextRotor(r2);
        r2.SetNextRotor(r3);
        r2.SetPreviusRotor(r1);
        r3.SetPreviusRotor(r2);
        r3.SetReflector(reflector);
        reflector.SetLastRotor(r3);

        // Testing the encoding here.
        EncodeStringLinkedList(message, r1);  
        
        System.out.println("Encoded message: " + encodedMessage.toString());
    }

    /**
     * This way looks better and uses recursion 
     * 
     * but it doesnt work as intended
     * @param stringToEncrypt
     * @return
     */
    public static void EncodeStringLinkedList(String stringToEncrypt, Rotor head) {
        for (char letter : stringToEncrypt.toCharArray()) {
            if (Character.isLetter(letter)) {
                letter = Character.toUpperCase(letter);

                System.out.println("Encoding '" + letter + "' : ");
                
                letter = head.EncodeCharacterNextRotor(letter);
                head.UpdateRotorPosition();
    
                System.out.println(letter);
    
                encodedMessage.append(letter);
            }
        }
    }
}