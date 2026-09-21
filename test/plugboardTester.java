package test;

import org.junit.jupiter.api.Test;
import src.Plugboard;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class plugboardTester {
    char[][] noDuplicates = {{'A', 'B', 'C'}, {'D', 'E', 'F'}};
    char[][] duplicatesSameRow = {{'A', 'B', 'A'}, {'D', 'E', 'F'}};
    char[][] duplicatesDifferentRows = {{'A', 'B', 'C'}, {'D', 'E', 'A'}};

    Plugboard pb;

    @Test
    public void simpleNoDuplicates() {
        pb = new Plugboard(noDuplicates);

        assertEquals(false, pb.hasDuplicates(noDuplicates));
    }

    @Test
    public void duplicatedSameRow() {
        pb = new Plugboard(duplicatesSameRow);

        assertEquals(true, pb.hasDuplicates(duplicatesSameRow));
    }

    @Test
    public void duplicatedDifferentRow() {
        pb = new Plugboard(duplicatesDifferentRows);

        assertEquals(true, pb.hasDuplicates(duplicatesDifferentRows));
    }

    @Test
    public void simplePassthrough() {
        pb = new Plugboard(noDuplicates);

        assertEquals('D', pb.passthrough('A'));
    }
}
