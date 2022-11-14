package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

// BEGIN
@Value
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    private static ObjectMapper objectMapper = new ObjectMapper();

    public String serialize() throws JsonProcessingException {
        return objectMapper.writeValueAsString(this);
    }

    public static Car unserialize(String carJson) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(carJson, Car.class);
    }
    // END
}
