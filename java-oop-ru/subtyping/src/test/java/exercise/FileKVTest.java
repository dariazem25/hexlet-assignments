package exercise;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import com.fasterxml.jackson.databind.ObjectMapper;

// BEGIN
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
// END


class FileKVTest {

    private static Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.CREATE);
    }

    // BEGIN
    @Test
    void writeValueToFile() {
        KeyValueStorage fileKV = new FileKV(filepath.toString(), Map.of());
        fileKV.set("key", "value");
        assertThat(fileKV.get("key", "value")).isEqualTo("value");
    }

    @Test
    void getDefaultValueFromFile() {
        KeyValueStorage fileKV = new FileKV(filepath.toString(), Map.of());
        fileKV.set("key1", "value1");
        assertThat(fileKV.get("key5", "defaultValue")).isEqualTo("defaultValue");
    }

    @Test
    void removeValueFromFile() {
        KeyValueStorage fileKV = new FileKV(filepath.toString(), Map.of());
        fileKV.set("key2", "value2");
        fileKV.unset("key2");
        assertThat(fileKV.get("key2", "defaultValue")).isEqualTo("defaultValue");
    }

    @Test
    void readAllFile() {
        KeyValueStorage fileKV = new FileKV(filepath.toString(), Map.of());
        fileKV.set("key5", "value5");
        fileKV.set("key2", "value2");
        fileKV.set("key3", "value3");
        Map<String, String> actual = fileKV.toMap();
        assertThat(actual).isEqualTo(Map.of("key5", "value5", "key2", "value2", "key3", "value3"));
    }

    @Test
    void readEmptyFile() {
        KeyValueStorage fileKV = new FileKV(filepath.toString(), new HashMap<>());
        Map<String, String> actual = fileKV.toMap();
        assertThat(actual).isEqualTo(Map.of());
    }

    @Test
    void readFileWithInitialData() {
        KeyValueStorage fileKV = new FileKV(filepath.toString(), Map.of("key10", "value10"));
        Map<String, String> actual = fileKV.toMap();
        assertThat(actual).isEqualTo(Map.of("key10", "value10"));
    }

    @Test
    void fileIsNotChanged() {
        KeyValueStorage fileKV = new FileKV(filepath.toString(), Map.of("key11", "value11"));
        Map<String, String> actual = fileKV.toMap();
        actual.put("key7", "value7");
        assertThat(fileKV.toMap()).isEqualTo(Map.of("key11", "value11"));
    }
    // END
}
