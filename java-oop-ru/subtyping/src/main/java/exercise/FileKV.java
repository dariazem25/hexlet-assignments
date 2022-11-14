package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage {
    private String path;
    private Map<String, String> storage;

    public FileKV(String path, Map<String, String> storage) {
        this.path = path;
        this.storage = new HashMap<>(storage);
        var initial = Utils.serialize(this.storage);
        Utils.writeFile(this.path, initial);
    }

    @Override
    public void set(String key, String value) {
        storage.put(key, value);
        var result = Utils.serialize(storage);
        Utils.writeFile(path, result);
    }

    @Override
    public void unset(String key) {
        storage.remove(key);
        var result = Utils.serialize(storage);
        Utils.writeFile(path, result);
    }

    @Override
    public String get(String key, String defaultValue) {
        var read = Utils.readFile(path);
        var content = Utils.unserialize(read);
        return content.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        var content = Utils.readFile(path);
        return Utils.unserialize(content);
    }
}
// END
