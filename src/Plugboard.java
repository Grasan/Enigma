package src;

import javafx.util.Pair;

import java.util.*;

import static src.Globals.GET_LETTER_INDEX;

public class Plugboard {
    private char[] plugboardSetting = {
        'A','B','C','D','E','F','G','H','I','J',
        'K','L','M','N','O','P','Q','R','S','T',
        'U','V','W','X','Y','Z'
    };

    /*public Plugboard(char[][] newSetting) {
        // Check for duplicates



    }*/

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
