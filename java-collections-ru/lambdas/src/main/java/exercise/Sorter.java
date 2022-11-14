package exercise;

import java.util.Comparator;
import java.util.Map;
import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

// BEGIN
public class Sorter {

    public static List<String> takeOldestMan(List<Map<String, String>> users) {

        if (users == null) {
            throw new RuntimeException("List didn't provide");
        }

        return users.stream()
                .filter(m -> m.containsValue("male"))
                .filter(m -> m.containsKey("birthday"))
                .filter(m -> m.containsKey("name"))
                .sorted(Comparator.comparing(m -> LocalDate.parse(m.get("birthday"))))
                .map(m -> m.get("name"))
                .collect(Collectors.toList());
    }
}

// END
