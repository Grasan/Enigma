package src;

public class Main {

    // Legit wiring for enigma rotors, check en.wikipedia.org/wiki/Enigma_rotor_details 
    public static String[] rotorWiring = {
        "EKMFLGDQVZNTOWYHXUSPAIBRCJ",   // Model: Enigma 1, Rotor# I
        "AJDKSIRUXBLHWTMCQGZNPYFVOE",   // Model: Enigma 1, Rotor# II
        "BDFHJLCPRTXVZNYEIWGAKMUSQO"    // Model: Enigma 1, Rotor# III
    };

    // Rotor setup part 1
    private static Rotor r1 = new Rotor(0, rotorWiring[0]);
    private static Rotor r2 = new Rotor(0, rotorWiring[1]);
    private static Rotor r3 = new Rotor(0, rotorWiring[2]);

    public static void main(String[] args) {
        // Rotor setup part 2
        r1.SetNextRotor(r2);
        r2.SetNextRotor(r3);
        r2.SetPreviusRotor(r1);
        r3.SetPreviusRotor(r2);

        // Testing the encoding here.
        System.out.println(EncodeStringLinkedList("hello", r1));
    }

    /**
     * This way of encoding kinda works but is ugly as sh*t
     * 
     * way too nested for my taste
     * @param input
     * @return
     */
    /*public static String EncodeString(String input) {
        String result = "";

        for (char letter : input.toCharArray()) {
            char temp = letter;

            if (letter == ' ')
                result += letter;   // Leaving spaces as is
            else {
                System.out.print(temp + " : ");

                for (int i = 0; i < rotors.length; i++) 
                    temp = rotors[i].EncodeCharacter(temp);
                
                for (int i = rotors.length -1; i > -1; i--)
                    temp = rotors[i].EncodeCharacter(temp);

                System.out.println(" : " + temp);

                result += temp;
            }
        }
        return result;
    }*/

    /**
     * This way looks better and uses recursion 
     * 
     * but it doesnt work as intended
     * @param input
     * @return
     */
    public static String EncodeStringLinkedList(String input, Rotor head) {
        String result = "";
        char temp;

        Rotor end = GetLastRotor(head);

        for (char letter : input.toCharArray()) {
            if (letter == ' ')
                result += letter;
            else {
                temp = letter;

                System.out.print(temp + " : ");
                
                temp = r1.EncodeCharacterNextRotor(temp);
                temp = end.EncodeCharacterPreviusRotor(temp);
    
                System.out.println(temp);
    
                result += temp;
            }
        }

        return result;
    }

    public static Rotor GetLastRotor(Rotor head) {
        Rotor temp = head;

        while (true) {
            if (temp.GetNextRotor() == null)
                return temp;
            else 
                temp = temp.GetNextRotor();
        }
    }
}