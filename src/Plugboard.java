package src;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.List;

import static src.Alphabet.ALPHABET;

public class Plugboard {
    private char[] plugboardSetting = ALPHABET.toCharArray();

    private static int GET_LETTER_INDEX(char input) {
        return input - 'A';
    }


    public void setup(List<Pair<Character, Character>> plugs) {
        for (Pair<Character, Character> plug : plugs) {
            int index1 = GET_LETTER_INDEX(plug.getKey()),
                index2 = GET_LETTER_INDEX(plug.getValue());

            plugboardSetting[index1] = plug.getValue();
            plugboardSetting[index2] = plug.getKey();
        }
    }

    public Boolean hasDuplicates(List<Pair<Character, Character>> plugs) {
        HashSet<Character> seen = new HashSet<>();

        for (Pair<Character, Character> plug : plugs)
            if (!seen.add(plug.getKey()) || !seen.add(plug.getValue()))
                return true;

        return false;
    }

    public char passthrough(char input) {
        return plugboardSetting[GET_LETTER_INDEX(input)];
    }
}
