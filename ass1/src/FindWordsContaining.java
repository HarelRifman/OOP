/*
 * Harel Rifman 217398338
 *
 * ASS1 5/10/24
 *
 * Given an array of strings and a character, prints the list of words that contain the character.
 * Words are printed in the order they were received.
 */
public class FindWordsContaining {

    /**
     * The main method of the program.
     *
     * @param args Command line arguments. Expects at least two arguments: an array of strings and a character.
     */
    public static void main(String[] args) {
        try {
            if (args.length < 2 || args[args.length - 1].length() != 1) {
                System.out.println("Invalid input");
                return;
            }
        }
        catch (Exception e) {
            System.out.println("Invalid input");
            return;
        }

        char x = args[args.length - 1].charAt(0);
        String[] strings = new String[args.length - 1];
        System.arraycopy(args, 0, strings, 0, args.length - 1);
        printWordsContainingCharacter(strings, x);
    }

    /**
     * Prints the words from the given array that contain the specified character.
     *
     * @param strings The array of strings to search.
     * @param x The character to search for.
     */
    public static void printWordsContainingCharacter(String[] strings, char x) {
        for (String string : strings) {
            if (string.contains(Character.toString(x))) {
                System.out.println(string);
            }
        }
    }
}