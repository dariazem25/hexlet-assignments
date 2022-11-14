package exercise;

import java.util.Arrays;
import java.util.stream.Stream;

// BEGIN
public class App {

    public static String[][] enlargeArrayImage(String[][] image) {

        if (image == null) {
            throw new RuntimeException("Image does not exist");
        }

        return Arrays.stream(image)
                .map(App::doubleArray)
                .flatMap(x -> Stream.of(x, x))
                .toArray(String[][]::new);
    }

    private static String[] doubleArray(String[] array) {
        return Arrays.stream(array)
                .flatMap(x -> Stream.of(x, x))
                .toArray(String[]::new);
    }
}

// END
