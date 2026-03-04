package test;

import org.testng.annotations.Test;
import src.Plugboard;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class plugboardTester {
    char[][] noDuplicates = {{'A', 'B', 'C'}, {'D', 'E', 'F'}};
    char[][] duplicatesSameRow = {{'A', 'B', 'A'}, {'D', 'E', 'F'}};
    char[][] duplicatesDifferentRows = {{'A', 'B', 'C'}, {'D', 'E', 'A'}};

    Plugboard pb = new Plugboard();

    @Test
    public void simpleNoDuplicates() {
        assertEquals(false, pb.hasDuplicates(noDuplicates));
    }

    @Test
    public void duplicatedSameRow() {
        assertEquals(true, pb.hasDuplicates(duplicatesSameRow));
    }

    @Test
    public void duplicatedDifferentRow() {
        assertEquals(true, pb.hasDuplicates(duplicatesDifferentRows));
    }
}
