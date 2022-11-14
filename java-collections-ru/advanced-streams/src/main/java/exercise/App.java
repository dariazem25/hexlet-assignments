package exercise;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// BEGIN
public class App {
    public static String getForwardedVariables(String confFile) {
        return Arrays.stream(confFile.split("\n"))
                .filter(s -> s.startsWith("environment="))
                .map(s -> s.replaceAll("environment=", ""))
                .map(s -> s.replaceAll("\"", ""))
                .flatMap(s -> Stream.of(s.split(",")))
                .filter(s -> s.contains("X_FORWARDED_"))
                .map(s -> s.replaceAll("X_FORWARDED_", ""))
                .collect(Collectors.joining(","));
    }
}
//END
//