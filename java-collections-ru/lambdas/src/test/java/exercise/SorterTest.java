package exercise;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

// BEGIN
class SorterTest {
    @Test
    void testTakeOldestMan() {

        // first
        List<Map<String, String>> list1 = List.of(
                Map.of("name", "Vladimir Nikolaev", "birthday", "1989-11-23", "gender", "male"),
                Map.of("name", "Andrey Petrov", "birthday", "1989-11-22", "gender", "male"),
                Map.of("name", "Anna Sidorova", "birthday", "1996-09-09", "gender", "female"),
                Map.of("name", "John Smith", "birthday", "1991-03-11", "gender", "male"),
                Map.of("name", "Vanessa Vulf", "birthday", "1985-11-16", "gender", "female"),
                Map.of("name", "Alice Lucas", "birthday", "1986-01-01", "gender", "female"),
                Map.of("name", "Elsa Oscar", "birthday", "1970-03-10", "gender", "female")
        );

        List<String> actual1 = Sorter.takeOldestMan(list1);
        List<String> expected1 = List.of("Andrey Petrov", "Vladimir Nikolaev", "John Smith");
        assertThat(actual1).isEqualTo(expected1);

        //second
        List<Map<String, String>> list2 = List.of();
        List<String> actual2 = Sorter.takeOldestMan(list2);
        assertThat(actual2).isEmpty();

        //third
        List<Map<String, String>> list3 = List.of(
                Map.of("name", "Vladimir Nikolaev", "gender", "male"),
                Map.of("name", "Andrey Petrov", "birthday", "1989-11-22", "gender", "male"),
                Map.of("name", "Anna Sidorova", "birthday", "1996-09-09", "gender", "female"),
                Map.of("name", "John Smith", "birthday", "1991-03-11", "gender", "male")
        );
        List<String> actual3 = Sorter.takeOldestMan(list3);
        List<String> expected3 = List.of("Andrey Petrov", "John Smith");
        assertThat(actual3).isEqualTo(expected3);

        //fourth
        List<Map<String, String>> list4 = List.of(
                Map.of("birthday", "1989-11-23", "gender", "male"),
                Map.of("name", "Andrey Petrov", "birthday", "1989-11-22", "gender", "male"),
                Map.of("name", "Anna Sidorova", "birthday", "1996-09-09", "gender", "female"),
                Map.of("name", "John Smith", "birthday", "1991-03-11", "gender", "male")
        );
        List<String> actual4 = Sorter.takeOldestMan(list4);
        List<String> expected4 = List.of("Andrey Petrov", "John Smith");
        assertThat(actual4).isEqualTo(expected4);

        //fifth
        List<Map<String, String>> list5 = List.of(
                Map.of(),
                Map.of("name", "Andrey Petrov", "birthday", "1989-11-22", "gender", "male"),
                Map.of("name", "Anna Sidorova", "birthday", "1996-09-09", "gender", "female"),
                Map.of("name", "John Smith", "birthday", "1991-03-11", "gender", "male")
        );
        List<String> actual5 = Sorter.takeOldestMan(list5);
        List<String> expected5 = List.of("Andrey Petrov", "John Smith");
        assertThat(actual5).isEqualTo(expected5);

        //sixth
        List<Map<String, String>> list6 = List.of(
                Map.of("name", "Vladimir Nikolaev", "birthday", "1989-11-23"),
                Map.of("name", "Andrey Petrov", "birthday", "1989-11-22"),
                Map.of("name", "Anna Sidorova", "birthday", "1996-09-09", "gender", "female"),
                Map.of("name", "John Smith", "birthday", "1991-03-11", "gender", "male")
        );
        List<String> actual6 = Sorter.takeOldestMan(list6);
        List<String> expected6 = List.of("John Smith");
        assertThat(actual6).isEqualTo(expected6);

        //seventh
        List<Map<String, String>> list7 = List.of(
                Map.of("name", "Vladimir Nikolaev", "gender", "male"),
                Map.of("name", "Andrey Petrov", "gender", "male"),
                Map.of("name", "Anna Sidorova", "birthday", "1996-09-09", "gender", "female"),
                Map.of("name", "John Smith", "gender", "male")
        );
        List<String> actual7 = Sorter.takeOldestMan(list7);
        assertThat(actual7).isEmpty();

        //eight
        List<Map<String, String>> list8 = List.of(
                Map.of(),
                Map.of(),
                Map.of()
        );
        List<String> actual8 = Sorter.takeOldestMan(list8);
        assertThat(actual8).isEmpty();

        //ninth
        List<Map<String, String>> list9 = List.of(
                Map.of("name", "Vladimir Nikolaev", "birthday", "1989-11-22", "gender", "male"),
                Map.of("name", "Andrey Petrov", "birthday", "1989-11-22", "gender", "male"),
                Map.of("name", "Anna Sidorova", "birthday", "1996-09-09", "gender", "female")
        );
        List<String> actual9 = Sorter.takeOldestMan(list9);
        List<String> expected9 = List.of("Vladimir Nikolaev", "Andrey Petrov");
        assertThat(actual9).containsExactlyInAnyOrderElementsOf(expected9);

        //tenth
        Throwable exception = assertThrows(
                RuntimeException.class, () -> {
                    Sorter.takeOldestMan(null);
                }
        );
        assertThat("List didn't provide").isEqualTo(exception.getMessage());
    }
}

// END


