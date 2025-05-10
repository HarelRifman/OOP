/*
 * Harel Rifman 217398338
 *
 * ASS1 5/10/24
 *
 * This class calculates the sum of the digits of an integer using a recursive method.
 */
public class SumOfInteger {

    /**
     * The main method of the program.
     *
     * @param args Command line arguments. Expects a single argument: an integer to calculate the sum of its digits.
     */
    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                System.out.println("Invalid input.");
                return;
            }
            System.out.println(sumOfIntegers(Integer.parseInt(args[0])));
        }
        catch (Exception e) {
            System.out.println("Invalid input");
            return;
        }
    }

    /**
     * Calculates the sum of the digits of an integer recursively.
     *
     * @param n The integer for which to calculate the sum of its digits.
     * @return The sum of the digits of the integer.
     */
    public static int sumOfIntegers(int n) {
        if (n == 0) {
            return 0;
        }
        return n % 10 + sumOfIntegers(n / 10);
    }
}
