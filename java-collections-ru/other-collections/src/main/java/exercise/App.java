package exercise;

import java.util.*;

// BEGIN
public class App {

    public static LinkedHashMap<String, String> genDiff(Map<String, Object> firstMap, Map<String, Object> secondMap) {
        LinkedHashMap<String, String> newMap = new LinkedHashMap<>();
        Set<String> setOfKeys = new TreeSet<>(firstMap.keySet());
        setOfKeys.addAll(secondMap.keySet());

        if (firstMap == null || secondMap == null) {
            throw new RuntimeException("Map does not exist");
        }

        if (firstMap.isEmpty() && !secondMap.isEmpty()) {
            for (String second : secondMap.keySet()) {
                newMap.put(second, "added");
            }
        } else if (secondMap.isEmpty() && !firstMap.isEmpty()) {
            for (String first : firstMap.keySet()) {
                newMap.put(first, "deleted");
            }
        }

        for (String key : setOfKeys) {
            if (secondMap.containsKey(key) && firstMap.containsKey(key) && secondMap.get(key).equals(firstMap.get(key))) {
                newMap.put(key, "unchanged");
            }

            if (secondMap.containsKey(key) && firstMap.containsKey(key) && !(secondMap.get(key).equals(firstMap.get(key)))) {
                newMap.put(key, "changed");
            }

            if (!secondMap.containsKey(key)) {
                newMap.put(key, "deleted");
            }

            if (!firstMap.containsKey(key)) {
                newMap.put(key, "added");
            }
        }

        return newMap;
    }
}

//END
