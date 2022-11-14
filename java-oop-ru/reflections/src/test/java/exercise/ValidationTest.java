package exercise;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;


class ValidationTest {

    @Test
    void testValidate() {
        Address address1 = new Address("Russia", "Ufa", "Lenina", "54", null);
        List<String> result1 = Validator.validate(address1);
        List<String> expected1 = List.of();
        assertThat(result1).isEqualTo(expected1);

        Address address2 = new Address(null, "London", "1-st street", "5", "1");
        List<String> result2 = Validator.validate(address2);
        List<String> expected2 = List.of("country");
        assertThat(result2).isEqualTo(expected2);

        Address address3 = new Address("USA", null, null, null, "1");
        List<String> result3 = Validator.validate(address3);
        List<String> expected3 = List.of("city", "street", "houseNumber");
        assertThat(result3).isEqualTo(expected3);
    }

    // BEGIN
    @Test
    void testAdvancedValidate() {
        Address address1 = new Address("Russia", "Omsk", "Lenina", "54", null);
        Map<String, List<String>> result1 = Validator.advancedValidate(address1);
        assertThat(result1).isEqualTo(Map.of("country", List.of("length is less than 7")));

        Address address2 = new Address(null, "Omsk", "Lenina", "54", null);
        Map<String, List<String>> result2 = Validator.advancedValidate(address2);
        assertThat(result2).isEqualTo(Map.of("country", List.of("can not be null", "length is less than 7")));

        Address address3 = new Address("Argentina", "Ab", "Street12", "54", null);
        Map<String, List<String>> result3 = Validator.advancedValidate(address3);
        assertThat(result3).isEqualTo(Map.of("city", List.of("length is less than 3")));

        Address address4 = new Address(null, "Ab", "Lenina", "54", null);
        Map<String, List<String>> result4 = Validator.advancedValidate(address4);
        assertThat(result4).isEqualTo(Map.of("country", List.of("can not be null", "length is less than 7"),
                "city", List.of("length is less than 3")));

        Address address5 = new Address(null, null, "Lenina", "54", null);
        Map<String, List<String>> result5 = Validator.advancedValidate(address5);
        assertThat(result5).isEqualTo(Map.of("country", List.of("can not be null", "length is less than 7"),
                "city", List.of("can not be null", "length is less than 3")));

        Address address6 = new Address("Argentina", "Buenos Aires", null, "54", null);
        Map<String, List<String>> result6 = Validator.advancedValidate(address6);
        assertThat(result6).isEqualTo(Map.of("street", List.of("can not be null")));

        Address address7 = new Address("Argentina", "Buenos Aires", "12", "54", null);
        Map<String, List<String>> result7 = Validator.advancedValidate(address7);
        assertThat(result7).isEqualTo(Map.of());
    }
    // END
}
