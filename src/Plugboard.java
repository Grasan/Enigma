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
        HashMap<Character, Character> seen = new HashMap<>();

        for (int i = 0; i < plugs.size(); i++) {
            if (seen.containsKey(plugs.getLast().getKey()) || seen.containsValue(plugs.getLast().getValue()) ||
                seen.containsKey(plugs.getLast().getValue()) || seen.containsValue(plugs.getLast().getKey()))
                return true;

            seen.put(plugs.get(i).getKey(), plugs.get(i).getValue());
        }

        return false;
    }

    public char passthrough(char input) {
        return plugboardSetting[GET_LETTER_INDEX(input)];
    }
}
