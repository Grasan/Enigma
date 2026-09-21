package src;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

import static src.Globals.GET_LETTER_INDEX;

public class Plugboard {
    private char[] plugboardSetting = {
        'A','B','C','D','E','F','G','H','I','J',
        'k','L','M','N','O','P','Q','R','S','T',
        'U','V','W','X','Y','Z'
    };

    public Plugboard(char[][] newSetting) {
        // Check for duplicates

        for (char[] chars : newSetting) {
            int index1 = GET_LETTER_INDEX(chars[0]),
                index2 = GET_LETTER_INDEX(chars[1]);

            plugboardSetting[index1] = chars[0];
            plugboardSetting[index2] = chars[1];
        }
    }

    public Boolean hasDuplicates(char[][] plugs) {
        Set<Character> seen = new HashSet<>();

        return Arrays.stream(plugs)
            .flatMap(row -> IntStream.range(0, row.length)
                .mapToObj(i -> row[i]))
            .anyMatch(c -> !seen.add(c));
    }

    public char passthrough(char input) {
        return plugboardSetting[GET_LETTER_INDEX(input)];
    }
}
