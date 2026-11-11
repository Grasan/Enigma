package src;

public class Alphabet {

    public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static final int ALPHABET_SIZE = ALPHABET.length(); // 26

    public static int GET_LETTER_INDEX(char input) {
        return input - 'A';
    }
    public static char GET_LETTER_FROM_INDEX(int index) { return ALPHABET.charAt(index); }

}
