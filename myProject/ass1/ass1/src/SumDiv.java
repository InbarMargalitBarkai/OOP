/**
 * assignment 1.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-03-15
 */

/**
 * In mathematics, modular arithmetic is a system of arithmetic for integers,
 * where numbers wrap around when reaching a certain value, called the modulus.
 */
public class SumDiv {

    /**
     * This is a program which receives 3 positive Integers a,b,c as arguments
     * and prints out the numbers between 1 and a (including a itself) that is divisible by b or c.
     * and the sum of all these numbers.
     * @param args - receives 3 positive Integers a,b,c as arguments.
     */
    public static void main(String[] args) {
        // edge case number one: checking if we get correct number of arguments
        if (args.length != 3) {
            System.out.println("Invalid input");
            return;
        }
        /*
         edge case number two: checking if the arguments are integers
         (on this mission we can assume that we will get integers numbers)
         */
        for (int i = 0; i < 3; i++) {
            try {
                Integer.parseInt(args[i]);
            } catch (Exception ex) {
                System.out.println("Invalid input");
                return;
            }
        }
        // saving the arguments
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int c = Integer.parseInt(args[2]);
        /*
         edge case number three: must be positive number and we can not divide by zero
         (we will assume that positive numbers are more than 0)
         */
        if ((a <= 0) || (b <= 0) || (c <= 0)) {
            System.out.println("Invalid input");
            return;
        }
        // sum of all the number between 1 - a that divisible by b or c
        int sum = 0;
        for (int i = 1; i <= a; i++) {
            if (((i % b) == 0) || ((i % c) == 0)) {
                System.out.println(i);
                sum = sum + i;
            }
        }
        System.out.println(sum);
    }
}