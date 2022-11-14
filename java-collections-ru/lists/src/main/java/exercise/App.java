package exercise;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

// BEGIN
public class App {

    public static boolean scrabble(String sequenceOfWords, String word) {

        if ((sequenceOfWords == null || word == null) || (sequenceOfWords.isEmpty() && word.isEmpty())) {
            throw new RuntimeException("Invalid values");
        }

        List<String> list1 = new ArrayList<>(sequenceOfWords.length());
        list1.addAll(Arrays.asList(sequenceOfWords.split("")));
        List<String> list2 = new ArrayList<>(word.length());
        list2.addAll(Arrays.asList(word.split("")));
        String result = "";

        for (String l : list2) {
            for (int j = 0; j < list1.size(); j++) {
                if (l.equalsIgnoreCase(list1.get(j))) {
                    result += list1.get(j);
                    list1.remove(j);
                    break;
                } else if (j == list1.size() - 1) {
                    return false;
                }
            }
        }
        return result.equalsIgnoreCase(word);
    }
}

//END
