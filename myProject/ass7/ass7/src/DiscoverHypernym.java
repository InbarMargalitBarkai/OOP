import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * assignment 7.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-06-29
 */
public class DiscoverHypernym {
    // constant for the first time the value is in the hypernym
    static final Integer FIRST = 1;

    /**
     * Creating a list that will contain all the files we will need to read and check.
     * @param path - the path of the main directory.
     * @param arg - the lemma.
     * @throws IOException - throw exception if the file not found..
     */
    public static void listOfAllFiles(File path, String arg) throws IOException {
        File[] filesList = path.listFiles();
        moveOnFIle(filesList, arg);
    }

    /**
     * Moving on all the files, checking the 5 cases and grouping all the hypernyms and hyponyms we found.
     * At the end we get list of all the lines with the hypernyms and hyponyms we found.
     * @param filesList - the direction to all files.
     * @param arg - the lemma.
     * @throws IOException - throws exception if there was problem wth the input of the files by reading them
     * or something else.
     */
    public static void moveOnFIle(File[] filesList, String arg) throws IOException {
        String line;
        LinkedList<String> listCaseOne = new LinkedList(),
                listCaseTwo = new LinkedList(),
                listCaseThree = new LinkedList(),
                listCaseFour = new LinkedList(),
                listCaseFive = new LinkedList();
        LinkedList<LinkedList<String>> listGroup = new LinkedList<>();
        for (File file : filesList) {
            BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
            while ((line = reader.readLine()) != null) {
                // case one: NP {,} such as NP {, NP, ..., {and|or} NP}
                String caseOne = "<np>[A-Z|a-z| |]*(</np> , |</np> )such as <np>[A-Z|a-z| ]*</np>(,|"
                        + "and|or| |<np>[A-Z|a-z| ]*</np>)*";
                Pattern p1 = Pattern.compile(caseOne);
                Matcher matcherOne = p1.matcher(line);
                while (matcherOne.find()) {
                    // saving the whole sentence
                    listCaseOne.add(matcherOne.group(0));
                }
                // case two: such NP as NP {, NP, ..., {and|or} NP}
                String caseTwo = "such <np>[A-Z|a-z| ]*</np> as <np>[A-Z|a-z| ]*</np>(,|and|or| |"
                        + "<np>[A-Z|a-z| ]*</np>)*";
                Pattern p2 = Pattern.compile(caseTwo);
                Matcher matcherTwo = p2.matcher(line);
                while (matcherTwo.find()) {
                    listCaseTwo.add(matcherTwo.group(0));
                }
                // case three: NP {,} including NP {, NP, ..., {and|or} NP}
                String caseThree = "<np>[A-Z|a-z| |]*(</np> , |</np> )including <np>[A-Z|a-z| ]*</np>(,|"
                        + "and|or| |<np>[A-Z|a-z| ]*</np>)*";
                Pattern p3 = Pattern.compile(caseThree);
                Matcher matcherThree = p3.matcher(line);
                while (matcherThree.find()) {
                    listCaseThree.add(matcherThree.group(0));
                }
                // case four: NP {,} especially NP {, NP, ..., {and|or} NP}
                String caseFour = "<np>[A-Z|a-z| |]*(</np> , |</np> )especially <np>[A-Z|a-z| ]*</np>(,|"
                        + "and|or| |<np>[A-Z|a-z| ]*</np>)*";
                Pattern p4 = Pattern.compile(caseFour);
                Matcher matcherFour = p4.matcher(line);
                while (matcherFour.find()) {
                    listCaseFour.add(matcherFour.group(0));
                }
                // case five: NP {,} which is {{an example|a kind|a class} of} NP
                String caseFive = "<np>[A-Z|a-z| |]*(</np> , |</np> )which is (an example|a kind|a class)"
                        + " of <np>[A-Z|a-z| ]*<\\/np>";
                Pattern p5 = Pattern.compile(caseFive);
                Matcher matcherFive = p5.matcher(line);
                while (matcherFive.find()) {
                    listCaseFive.add(matcherFive.group(0));
                }
            }
            reader.close();
        }
        listGroup.add(listCaseOne);
        listGroup.add(listCaseTwo);
        listGroup.add(listCaseThree);
        listGroup.add(listCaseFour);
        listGroup.add(listCaseFive);
        organizeExpressions(listGroup, arg);
    }

    /**
     * Organize the lines we got until we will get dictionary with only hypernyms and their hyponyms.
     * @param listGroup - list of all the lines with the hypernyms and hyponyms we found.
     * @param arg - the lemma.
     */
    public static void organizeExpressions(LinkedList<LinkedList<String>> listGroup, String arg) {
        // creating dictionaries that will save the hyperny and his hyponyms
        TreeMap<String, List<String>> dictionaryOne = new TreeMap<String,
                List<String>>((String.CASE_INSENSITIVE_ORDER)),
                dictionaryTwo = new TreeMap<String, List<String>>((String.CASE_INSENSITIVE_ORDER)),
                dictionaryThree = new TreeMap<String, List<String>>((String.CASE_INSENSITIVE_ORDER)),
                dictionaryFour = new TreeMap<String, List<String>>((String.CASE_INSENSITIVE_ORDER)),
                dictionaryFive = new TreeMap<String, List<String>>((String.CASE_INSENSITIVE_ORDER));
        // we know there is 5 lists in the list group
        for (int i = 0; i < listGroup.size(); i++) {
            int cases = i;
            switch (cases) {
                case 0:
                    // listCaseOne
                    LinkedList<String> listCaseOne = listGroup.get(cases);
                    if (!listCaseOne.isEmpty()) {
                        for (int j = 0; j < listCaseOne.size(); j++) {
                            List<String> list = new ArrayList<String>(Arrays.asList(listCaseOne.get(j).split("<np>|"
                                    + "</np>|such as|or|and|,")));
                            // deleting from the list all the null elements
                            list.removeAll(Arrays.asList(" ", null, ""));
                            // in this case the hypernym is the first element and after him the hyponyms
                            String hypernym = list.get(0);
                            List<String> secondList = new LinkedList<>();
                            for (int k = 1; k < list.size(); k++) {
                                secondList.add(list.get(k));
                                dictionaryOne.put(hypernym, secondList);
                            }
                        }
                    }
                    break;
                case 1:
                    // listCaseTwo
                    LinkedList<String> listCaseTwo = listGroup.get(cases);
                    if (!listCaseTwo.isEmpty()) {
                        for (int j = 0; j < listCaseTwo.size(); j++) {
                            List<String> list = new ArrayList<String>(Arrays.asList(listCaseTwo.get(j).split("<np>|"
                                    + "</np>|such|as|or|and|,")));
                            // deleting from the list all the null elements
                            list.removeAll(Arrays.asList(" ", null, ""));
                            // in this case the hypernym is the first element and after him the hyponyms
                            String hypernym = list.get(0);
                            List<String> secondList = new LinkedList<>();
                            for (int k = 1; k < list.size(); k++) {
                                secondList.add(list.get(k));
                                dictionaryTwo.put(hypernym, secondList);
                            }
                        }
                    }
                    break;
                case 2:
                    // listCaseThree
                    LinkedList<String> listCaseThree = listGroup.get(cases);
                    if (!listCaseThree.isEmpty()) {
                        for (int j = 0; j < listCaseThree.size(); j++) {
                            List<String> list = new ArrayList<String>(Arrays.asList(listCaseThree.get(j).split("<np>|"
                                    + "</np>|including|or|and|,")));
                            // deleting from the list all the null elements
                            list.removeAll(Arrays.asList(" ", null, ""));
                            // in this case the hypernym is the first element and after him the hyponyms
                            String hypernym = list.get(0);
                            List<String> secondList = new LinkedList<>();
                            for (int k = 1; k < list.size(); k++) {
                                secondList.add(list.get(k));
                                dictionaryThree.put(hypernym, secondList);
                            }
                        }
                    }
                    break;
                case 3:
                    // listCaseFour
                    LinkedList<String> listCaseFour = listGroup.get(cases);
                    if (!listCaseFour.isEmpty()) {
                        for (int j = 0; j < listCaseFour.size(); j++) {
                            List<String> list = new ArrayList<String>(Arrays.asList(listCaseFour.get(j).split("<np>|"
                                    + "</np>|especially|or|and|,")));
                            // deleting from the list all the null elements
                            list.removeAll(Arrays.asList(" ", null, ""));
                            // in this case the hypernym is the first element and after him the hyponyms
                            String hypernym = list.get(0);
                            List<String> secondList = new LinkedList<>();
                            for (int k = 1; k < list.size(); k++) {
                                secondList.add(list.get(k));
                                dictionaryFour.put(hypernym, secondList);
                            }
                        }
                    }
                    break;
                case 4:
                    // listCaseFive
                    LinkedList<String> listCaseFive = listGroup.get(cases);
                    if (!listCaseFive.isEmpty()) {
                        for (int j = 0; j < listCaseFive.size(); j++) {
                            List<String> list = new ArrayList<String>(Arrays.asList(listCaseFive.get(j).split("<np>|"
                                    + "</np>|which is|of|an example|a kind|a class|,")));
                            // deleting from the list all the null elements
                            list.removeAll(Arrays.asList(" ", null, ""));
                            // in this case the first NP is the hyponym and the second in a hypernym
                            String hyponym = list.get(0);
                            List<String> secondList = new LinkedList<>();
                            for (int k = 1; k < list.size(); k++) {
                                secondList.add(hyponym);
                                dictionaryFive.put(list.get(k), secondList);
                            }
                        }
                    }
                    break;
                default:
                    System.out.println("Invalid case");
            }
        }
        ArrayList<TreeMap<String, List<String>>> dictionaryList = new ArrayList();
        dictionaryList.add(dictionaryOne);
        dictionaryList.add(dictionaryTwo);
        dictionaryList.add(dictionaryThree);
        dictionaryList.add(dictionaryFour);
        dictionaryList.add(dictionaryFive);
        createHypernymy(dictionaryList, arg);
    }

    /**
     * At the end of the process, group the hyponyms of the same hypernyms (also called co-hyponyms),
     * ignore hypernyms that have less than 3 hyponyms and save the predicted relations in a file.
     * @param dictionaryList - array list that will contain all the dictionary list with the hypernyms
     *                      and their hyponyms.
     * @param arg - the path to the output file.
     */
    public static void createHypernymy(ArrayList<TreeMap<String, List<String>>> dictionaryList, String arg) {
        TreeMap<String, TreeMap<String, Integer>> summary = new TreeMap<>((String.CASE_INSENSITIVE_ORDER));
        for (TreeMap<String, List<String>> dictionary : dictionaryList) {
            Set<String> keysHypernym = dictionary.keySet();
            for (String hypernym : keysHypernym) {
                if (!summary.containsKey(hypernym)) {
                    TreeMap<String, Integer> hyopnymCounter = new TreeMap<String, Integer>();
                    summary.put(hypernym, hyopnymCounter);
                }
                List<String> hyponyms = dictionary.get(hypernym);
                for (String hyponym : hyponyms) {
                    summary.get(hypernym).put(hyponym, 0);
                }
            }
        }
        for (TreeMap<String, List<String>> dictionary : dictionaryList) {
            Set<String> keysHypernym = dictionary.keySet();
            for (String hypernym : keysHypernym) {
                if (!summary.containsKey(hypernym)) {
                    TreeMap<String, Integer> hyopnymCounter = new TreeMap<String, Integer>();
                    summary.put(hypernym, hyopnymCounter);
                }
                List<String> hyponyms = dictionary.get(hypernym);
                for (String hyponym : hyponyms) {
                    Integer num = summary.get(hypernym).get(hyponym);
                    summary.get(hypernym).put(hyponym, num + 1);
                }
            }
        }
        createPattern(summary, arg);
    }

    /**
     * Write the hypernymys by the correct syntax
     * and checking if the lemma is in the values of the hypernys or not.
     * @param summary - dictionary with the hypernymy.
     * @param arg - the lemma.
     */
    public static void createPattern(TreeMap<String, TreeMap<String, Integer>> summary, String arg) {
        // checking if we found the lemma or not
        int flag = 0;
        // contain the hypernys of the hypernom if exists
        ArrayList<String> arr = new ArrayList<>();
        Set<String> keysHypernym = summary.keySet();
        for (String mainKey : keysHypernym) {
            TreeMap<String, Integer> hyponymsAndCounter = summary.get(mainKey);
            // organize by descending order
            //LinkedHashMap preserve the ordering of elements in which they are inserted
            LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();
            //Use Comparator.reverseOrder() for reverse ordering
            hyponymsAndCounter.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
            Set<String> hyponyms = reverseSortedMap.keySet();
            for (String hyponym : hyponyms) {
                if (hyponym.equals(arg)) {
                    String str = mainKey + ": " + "(" + reverseSortedMap.get(hyponym) + ")";
                    arr.add(str);
                    flag = 1;
                }
            }
        }
        if (flag == 1) { // we found the lemma
            for (int i = 0; i < arr.size(); i++) {
                System.out.println(arr.get(i));
            }
        } else { // flag=0
           System.out.println("The lemma doesn't appear in the corpus.");
        }
    }

    /**
     * The program will search all the possible hypernyms of the input lemma and print them to the console as follows.
     * @param args - will get 2 arguments: (1) the absolute path to the directory of the corpus and (2) a lemma.
     * @throws IOException - throw exception if there was a problem with the input..
     */
    public static void main(String[] args) throws IOException {
        // edge case number one: checking if we get correct number of arguments
        if (args.length != 2) {
            System.out.println("Invalid input");
            return;
        }
        // saving the arguments and send them
        String lemma = args[1];
        File directoryPath = new File(args[0]);
        listOfAllFiles(directoryPath, lemma);
    }
}
