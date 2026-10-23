package test;

import javafx.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.Plugboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class plugboardTester {
    private Plugboard pb;

    private List<Pair<Character, Character>> noDoubles;
    private List<Pair<Character, Character>> doublesSameRow;
    private List<Pair<Character, Character>> doublesDifferentRow;

    @BeforeEach
    public void setUp() {
        pb = new Plugboard();

        noDoubles = Arrays.asList(
                new Pair<>('A', 'D'),
                new Pair<>('B', 'E'),
                new Pair<>('C', 'F')
        );

        doublesSameRow = Arrays.asList(
                new Pair<>('A', 'D'),
                new Pair<>('B', 'E'),
                new Pair<>('A', 'F')
        );

        doublesDifferentRow = Arrays.asList(
                new Pair<>('A', 'D'),
                new Pair<>('B', 'E'),
                new Pair<>('C', 'A')
        );
    }

    @Test
    public void simpleNoDuplicates() {
        pb = new Plugboard();

        assertEquals(false, pb.hasDuplicates(noDoubles));
    }

    @Test
    public void duplicatedSameRow() {
        pb = new Plugboard();

        assertEquals(true, pb.hasDuplicates(doublesSameRow));
    }

    @Test
    public void duplicatedDifferentRow() {
        pb = new Plugboard();

        assertEquals(true, pb.hasDuplicates(doublesDifferentRow));
    }

    @Test
    public void detectsDuplicatesBeforeLastPair() {
        List<Pair<Character, Character>> plugs = Arrays.asList(
                new Pair<>('A', 'D'),
                new Pair<>('A', 'E'),
                new Pair<>('B', 'F'),
                new Pair<>('C', 'G')
        );

        assertTrue(pb.hasDuplicates(plugs));
    }

    @Test
    public void detectsReversedDuplicatePair() {
        List<Pair<Character, Character>> plugs = Arrays.asList(
                new Pair<>('A', 'D'),
                new Pair<>('D', 'A')
        );

        assertTrue(pb.hasDuplicates(plugs));
    }

    @Test
    public void detectsDuplicateValue() {
        List<Pair<Character, Character>> plugs = Arrays.asList(
                new Pair<>('A', 'D'),
                new Pair<>('B', 'D')
        );

        assertTrue(pb.hasDuplicates(plugs));
    }

    @Test
    public void emptyPlugListHasNoDuplicates() {
        assertFalse(pb.hasDuplicates(Collections.emptyList()));
    }

    // -------------------------
    // setup() + passthrough()
    // -------------------------

    @Test
    public void simplePassthrough() {
        pb.setup(noDoubles);

        assertEquals('D', pb.passthrough('A'));
    }

    @Test
    public void passthroughWorksInBothDirections() {
        pb.setup(noDoubles);

        assertAll(
                () -> assertEquals('D', pb.passthrough('A')),
                () -> assertEquals('A', pb.passthrough('D')),
                () -> assertEquals('E', pb.passthrough('B')),
                () -> assertEquals('B', pb.passthrough('E')),
                () -> assertEquals('F', pb.passthrough('C')),
                () -> assertEquals('C', pb.passthrough('F'))
        );
    }

    @Test
    public void unconnectedLettersRemainUnchanged() {
        pb.setup(noDoubles);

        assertAll(
                () -> assertEquals('G', pb.passthrough('G')),
                () -> assertEquals('H', pb.passthrough('H')),
                () -> assertEquals('X', pb.passthrough('X')),
                () -> assertEquals('Z', pb.passthrough('Z'))
        );
    }

    @Test
    public void allAlphabetLettersRemainValid() {
        pb.setup(noDoubles);

        for (char c = 'A'; c <= 'Z'; c++) {
            char output = pb.passthrough(c);

            assertTrue(
                    output >= 'A' && output <= 'Z',
                    "Invalid output for input: " + c
            );
        }
    }

    @Test
    public void everyPlugIsItsOwnReverse() {
        pb.setup(noDoubles);

        for (Pair<Character, Character> plug : noDoubles) {
            char forward = pb.passthrough(plug.getKey());
            char backward = pb.passthrough(forward);

            assertEquals(
                    plug.getKey().charValue(),
                    backward,
                    "Failed reverse mapping for " + plug.getKey()
            );
        }
    }

    @Test
    public void emptySetupPreservesAlphabet() {
        pb.setup(Collections.emptyList());

        for (char c = 'A'; c <= 'Z'; c++) {
            assertEquals(c, pb.passthrough(c));
        }
    }

    // -------------------------
    // Stress tests
    // -------------------------

    @Test
    public void maximumNumberOfValidPlugs() {
        List<Pair<Character, Character>> plugs = Arrays.asList(
                new Pair<>('A', 'N'),
                new Pair<>('B', 'O'),
                new Pair<>('C', 'P'),
                new Pair<>('D', 'Q'),
                new Pair<>('E', 'R'),
                new Pair<>('F', 'S'),
                new Pair<>('G', 'T'),
                new Pair<>('H', 'U'),
                new Pair<>('I', 'V'),
                new Pair<>('J', 'W'),
                new Pair<>('K', 'X'),
                new Pair<>('L', 'Y'),
                new Pair<>('M', 'Z')
        );

        assertFalse(pb.hasDuplicates(plugs));

        pb.setup(plugs);

        for (Pair<Character, Character> plug : plugs) {
            assertEquals(
                    plug.getValue().charValue(),
                    pb.passthrough(plug.getKey())
            );

            assertEquals(
                    plug.getKey().charValue(),
                    pb.passthrough(plug.getValue())
            );
        }
    }

    @Test
    public void repeatedSetupWithDifferentValidMappings() {
        pb.setup(List.of(new Pair<>('A', 'F')));
        assertEquals('F', pb.passthrough('A'));

        pb = new Plugboard();
        pb.setup(List.of(new Pair<>('A', 'Z')));

        assertAll(
                () -> assertEquals('Z', pb.passthrough('A')),
                () -> assertEquals('A', pb.passthrough('Z'))
        );
    }

    @Test
    public void randomizedValidMappingsAreReversible() {
        List<Character> alphabet = new ArrayList<>();

        for (char c = 'A'; c <= 'Z'; c++) {
            alphabet.add(c);
        }

        java.util.Random random = new java.util.Random(42);

        for (int iteration = 0; iteration < 500; iteration++) {
            Collections.shuffle(alphabet, random);

            List<Pair<Character, Character>> plugs = new ArrayList<>();

            // Pair the shuffled letters without reuse.
            int pairCount = random.nextInt(14);

            for (int i = 0; i < pairCount * 2; i += 2) {
                plugs.add(new Pair<>(
                        alphabet.get(i),
                        alphabet.get(i + 1)
                ));
            }

            Plugboard testBoard = new Plugboard();

            assertFalse(testBoard.hasDuplicates(plugs));

            testBoard.setup(plugs);

            for (Pair<Character, Character> plug : plugs) {
                assertEquals(
                        plug.getValue().charValue(),
                        testBoard.passthrough(plug.getKey())
                );

                assertEquals(
                        plug.getKey().charValue(),
                        testBoard.passthrough(plug.getValue())
                );
            }

            // Every letter must map back to itself after two passes.
            for (char c = 'A'; c <= 'Z'; c++) {
                assertEquals(
                        c,
                        testBoard.passthrough(testBoard.passthrough(c)),
                        "Failed round trip in iteration " + iteration
                                + " for letter " + c
                );
            }
        }
    }
}
