/**
 * assignment 1.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-03-15
 */

/**
 * In number theory, Fermat's Last Theorem (sometimes called Fermat's conjecture, especially in older texts)
 * states that no three positive integers a, b, and c satisfy the equation a^n + b^n = c^n
 * for any integer value of n greater than 2.
 * The cases n = 1 and n = 2 have been known since antiquity to have infinitely many solutions.
 */
public class Fermat {

    /**
     * This is a program which receives 2 positive Integers as arguments: n, range.
     * The program should print all a,b,c for which the Pythagorean equation a^n + b^n = c^n is respected,
     * s.t a, b and c are between 1 and range (the second argument).
     * @param args - receives 2 positive Integers as arguments: n, range.
     */
    public static void main(String[] args) {
        // edge case number one: checking if we get correct number of arguments
        if (args.length != 2) {
            System.out.println("Invalid input");
            return;
        }
        /*
         edge case number two: checking if the arguments are integers
         (on this mission we can assume that we will get integers numbers)
         */
        for (int i = 0; i < 2; i++) {
            try {
                Integer.parseInt(args[i]);
            } catch (Exception ex) {
                System.out.println("Invalid input");
                return;
            }
        }
        // saving the arguments
        int n = Integer.parseInt(args[0]);
        int range = Integer.parseInt(args[1]);
        // edge case number three: must be positive (we will assume that positive numbers are more than 0)
        if ((n <= 0) || (range <= 0)) {
            System.out.println("Invalid input");
            return;
        }
        /*
          a^n + b^n = c^n has no solutions in positive integers if n is an integer greater than 2.
          Actually the Pythagorean equation is for power 2
         */
        if (n > 2) {
            System.out.println("no");
            return;
        }
        /*
        initialize our equation's parameters
        (I choosed to initialize them here to save memory location in case they weren't needed at first)
         */
        int a, b, c = 0;
        // flag to check if there is a triple or not
        boolean flag = false;
        // running on our optionals a, b, c since 1 until our range (assuming a<=b in order to avoid duplications)
        for (a = 1; a < range; a++) {
            for (b = a; b < range; b++) {
                for (c = 1; c < range; c++) {
                    // our Pythagorean equation a^n + b^n = c^n
                    if ((Math.pow(a, n) + Math.pow(b, n)) == Math.pow(c, n)) {
                        System.out.println(a + "," + b + "," + c);
                        flag = true;
                    } else {
                        continue;
                    }
                }
            }
        }
        // if no Pythagorean equation is found, print "no"
        if (!flag) {
            System.out.println("no");
        }
    }
}