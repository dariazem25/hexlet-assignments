package exercise;

import java.util.List;

// BEGIN
public class App {

    public static long getCountOfFreeEmails(List<String> list) {
        long count = 0;
        List<String> freeDomains = List.of("gmail.com", "yandex.ru", "hotmail.com");

        if (list == null) {
            throw new RuntimeException("The list does not exist");
        } else if (!list.isEmpty()) {
            count = list.stream()
                    .map(x -> x.substring(x.indexOf("@") + 1))
                    .filter(freeDomains::contains)
                    .count();
        }
        return count;
    }
}

// END
