package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {
    public static Map<String, String> swapKeyValue(KeyValueStorage storage) {
        Map<String, String> swapMap = new HashMap<>(storage.toMap());

        for (Map.Entry<String, String> map : swapMap.entrySet()) {
            storage.unset(map.getKey());
            storage.set(map.getValue(), map.getKey());
        }
        return storage.toMap();
    }
}
// END
