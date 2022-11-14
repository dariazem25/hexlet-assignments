package exercise;


// BEGIN

import java.util.*;

public class App {

    public static Map<String, Integer> getWordCount(String sentence) {

        if (sentence == null) {
            throw new RuntimeException("Please provide sentence");
        }

        sentence = sentence.toLowerCase();
        List<String> list = Arrays.asList(sentence.split(" "));
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < list.size(); i++) {
            if (map.containsKey(list.get(i)) || list.get(i).isEmpty()){
                continue;
            }
            int count = Collections.frequency(list,list.get(i));
            map.put(list.get(i),count);
        }
        return map;
    }

    public static String toString(Map<String, Integer> map) {
        if (map == null) {
            throw new RuntimeException("Map does not exist");
        }

        StringBuilder result = new StringBuilder();
        result.append("{");

        for (String e : map.keySet()) {
            result.append("\n\s\s").append(e).append(": ").append(map.get(e));
        }

        return map.isEmpty() ? "{}" : result.append("\n}").toString();
    }
}

//END
