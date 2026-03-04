package src;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

import static src.Globals.getLetterIndex;

public class Plugboard {
    private char[] plugboardSetting = {
        'A','B','C','D','E','F','G','H','I','J',
        'k','L','M','N','O','P','Q','R','S','T',
        'U','V','W','X','Y','Z'
    };

    private char[][] settings;

    public Plugboard() {
        /*
        // Check for duplicates
        if (hasDuplicates(plugs))
            throw new RuntimeException("Plugboard contains duplicates");
        else
            IntStream.range(0, plugs.length).forEach(i -> switchCharacterPositions(plugs[i][0], plugs[i][1]));

         */
    }

    public void setSettings(char[][] input) {
        // Validate
        if (hasDuplicates(input))
            throw new RuntimeException("Contains duplicates!");

        settings = input;
    }

    public Boolean hasDuplicates(char[][] plugs) {
        Set<Character> seen = new HashSet<>();

        return Arrays.stream(plugs)
            .flatMap(row -> IntStream.range(0, row.length)
                .mapToObj(i -> row[i]))
            .anyMatch(c -> !seen.add(c));
    }

    public void switchCharacterPositions(char from, char to) {
        int firstIndex = getLetterIndex(from),
            secondIndex = getLetterIndex(to);

        plugboardSetting[firstIndex] = to;
        plugboardSetting[secondIndex] = from;
    }

    public char passthrough(char input) {
        var inputIndex = Globals.getLetterIndex(input);

        return input == plugboardSetting[inputIndex] ? input : plugboardSetting[inputIndex];
    }
}
