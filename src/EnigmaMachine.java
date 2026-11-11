package src;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class EnigmaMachine {
    private Plugboard pb;
    private final ArrayList<Rotor> rotors = new ArrayList<>();
    private Reflector reflector;

    private final ArrayList<Integer> positions = new ArrayList<>();

    private StringBuilder cipheredMessage;

    public EnigmaMachine(
            ArrayList<Pair<Integer, String>> rotorSettings,
            ArrayList<Pair<Character, Character>> pbSettings
    ) {
        pb = new Plugboard();
        if (pbSettings != null)
            pb.setup(pbSettings);

        for (Pair<Integer, String> setting : rotorSettings) {
            positions.add(setting.getKey());
            Rotor rotor = new Rotor(setting.getKey(), setting.getValue());
            rotors.add(rotor);

            if (rotors.size() <= 1)
                continue;

            Rotor last = rotors.get(rotors.size() - 2);
            last.setNextRotor(rotor);
            rotor.setPreviousRotor(last);
        }

        reflector = new Reflector(rotors.getLast());
        rotors.getLast().setReflector(reflector);
    }

    public String cipherMessage(String message) {
        Rotor head = rotors.getFirst();
        StringBuilder cipher = new StringBuilder();

        for (char letter : message.toCharArray()) {
            if (letter == ' ') {
                cipher.append(letter);
            } else {
                // letter going through plugboard first
                letter = pb.passthrough(letter);

                // send the letter all the way through the rotors and reflector
                head.updateRotorPosition();
                letter = head.cipherCharacterNextRotor(letter);

                // pass the letter once again through the plugboard
                letter = pb.passthrough(letter);

                cipher.append(letter);
            }
        }

        return cipher.toString();
    }

    public void resetMachine() {
        IntStream.range(0, rotors.size()).forEach(i -> rotors.get(i).setRotorPosition(positions.get(i)));
    }
}
