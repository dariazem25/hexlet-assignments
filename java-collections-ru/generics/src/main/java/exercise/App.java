package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

// BEGIN
public class App {

    public static <K, V> List<Map<K, V>> findWhere(List<Map<K, V>> books, Map<K, V> values) {
        List<Map<K, V>> list = new ArrayList<>();

        for (Map<K, V> map : books) {
            int count = 0;
            for (Map.Entry<K, V> entry1 : values.entrySet()) {
                for (Map.Entry<K, V> entry2 : map.entrySet()) {
                    if (entry1.getKey().equals(entry2.getKey()) && entry1.getValue().equals(entry2.getValue())) {
                        count++;
                        break;
                    }
                }
                if (map.size() == values.size() && count == 0) {
                    break;
                }
            }
            if (count == values.size()) {
                list.add(map);
            }
        }
        return list;
    }
}
//END
