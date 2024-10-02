package src;

public class Rotor {
    private Rotor next = null, previus = null;

    private int position;   // Rotor position, will update after each encoded letter
    private String wiring;  // The internal wiring for the rotor, check en.wikipedia.org/wiki/Enigma_rotor_details 

    public Rotor (int position, String wiring) {
        this.position = position;
        this.wiring = wiring;
    }

    public void SetNextRotor(Rotor input) { next = input; }
    public Rotor GetNextRotor() { return next; }
    public void SetPreviusRotor(Rotor input) { previus = input; }
    public Rotor GetPreviusRotor() { return previus; }

    public void UpdateRotorPosition() {
        if (position == 25) {
            position = 0;
            if (next != null)
                next.UpdateRotorPosition();
        } 
        else
            position++;
    }

    /**
     * This function will encrypt the input character in a similar way the enigma rotors do.
     * @param input Character to be encryptet
     * @return
     */
    public char EncodeCharacter(char input) {
            // Get index of character in the alphabet
        int alphabeticalPosition = Character.toLowerCase(input) - 97;  
            // the alphabet position and current position of the this rotor 
        int pos = alphabeticalPosition + position;  
        char encodedChar;                    

        // Makes sure the pos value isnt higher then the wiring lenght.
        if (pos > 25)
            pos = pos - 25;

        encodedChar = wiring.charAt(pos);

        System.out.print(input + "->" + encodedChar + " : ");

        return encodedChar;
    }

    // Recursive methods calling the rotors to the left/right passing on the encoded char
    public char EncodeCharacterNextRotor(char input) {
        if (next != null)
            return next.EncodeCharacterNextRotor(EncodeCharacter(input));
        else
            return EncodeCharacter(input);
    }
    public char EncodeCharacterPreviusRotor(char input) {
        if (previus != null)
            return previus.EncodeCharacterPreviusRotor(EncodeCharacter(input));
        else 
            return EncodeCharacter(input);
    }
}
