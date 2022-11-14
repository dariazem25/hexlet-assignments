package exercise;

import java.util.ArrayList;
import java.util.List;

// BEGIN
public class App {
    public static List<String> buildAppartmentsList(List<Home> list, int elements) {
        List<String> newList = new ArrayList<>();
        if (!list.isEmpty()) {
            list.sort(Home::compareTo);
            for (int i = 0; i < elements; i++) {
                newList.add(list.get(i).toString());
            }
        }
        return newList;
    }
}

// END
