/*
 * Harel Rifman 217398338
 *
 * ASS1 5/10/24
 */
public class CountPairs {

    /**
     * The main method of the program.
     *
     * @param args Command line arguments. Expects at least two integers as input.
     */
    public static void main(String[] args) {
        try {
            if (args.length < 2) {
                System.out.println("Invalid input.");
                return;
            }
        }
        catch(Exception e){
            System.out.println("Invalid input.");
            return;
        }
        int []intArgs = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            intArgs[i] = Integer.parseInt(args[i]);
        }
        System.out.println(countPairs(intArgs));
    }

    /**
     * Counts pairs of elements in the array whose sum is less than the last element of the array.
     *
     * @param arr The array of integers in which to count pairs.
     * @return The count of pairs whose sum is less than the last element of the array.
     */
    public static int countPairs(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length - 1; j++) {
                if (arr[i] + arr[j] < arr[arr.length - 1]) {
                    count++;
                }
            }
        }
        return count;
    }
}
