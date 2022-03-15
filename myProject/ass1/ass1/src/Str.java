import java.util.ArrayList;

/**
 * assignment 1.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-03-16
 */

/**
 * In computer programming, a string is traditionally a sequence of characters, either as a literal constant or as some
 * kind of variable. The latter may allow its elements to be mutated and the length changed,
 * or it may be fixed (after creation). A string is generally considered as a data type
 * and is often implemented as an array data structure of bytes (or words) that stores a sequence of elements,
 * typically characters, using some character encoding.
 * String may also denote more general arrays or other sequence (or list) data types and structures.
 */
public class Str {

    /**
     * This is a program which receives two strings as arguments: query and sequence.
     * The program should print two groups of words:
     * 1. The words in the sequence that start with the query (case sensitive).
     * 2. All words in the sequence that include the query (case sensitive).
     * Each group of words should be printed in the order they appear in the original sequence.
     *
     * @param args - receives two strings as arguments: query (any string) and sequence (list of words separated by _).
     */
    public static void main(String[] args) {
        // edge case number one: checking if we get correct number of arguments
        if (args.length != 2) {
            System.out.println("Invalid input");
            return;
        }
        String query = args[0];
        String sequence = args[1];
        // saving the word that start with our query so we will not repeat them
        ArrayList<String> wordStartWithQuery = new ArrayList<String>();
        // indicates if all the chars in the query are at the beginning of the string we split
        int indicates = 0;
        int queryLength = query.length();
        String[] parts = sequence.split("_");
        // the words in the sequence that start with the query
        for (int j = 0; j < parts.length; j++) {
            if (parts[j].length() >= query.length()) {
                for (int i = 0; i < query.length(); i++) {
                    // checking if every char at our query is at the beginning of the split string
                    if (query.charAt(i) == parts[j].charAt(i)) {
                        indicates++;
                        continue;
                    } else {
                        indicates = 0;
                        break;
                    }
                }
            }
            if (indicates == query.length()) {
                System.out.println(parts[j]);
                wordStartWithQuery.add(parts[j]);
            }
        }
        // all words in the sequence that include the query
        for (int j = 0; j < parts.length; j++) {
            if (!(wordStartWithQuery.contains(parts[j]))) {
                if (parts[j].length() >= query.length()) {
                    if (parts[j].contains(query)) {
                        System.out.println(parts[j]);
                    }
                }
            }
        }
    }
}