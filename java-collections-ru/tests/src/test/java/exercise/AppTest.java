package exercise;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AppTest {

    @Test
    void testTake() {
        // BEGIN
        // the first test
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4);
        List<Integer> expected1 = Arrays.asList(1, 2, 3, 4);
        List<Integer> actual1 = App.take(list1, list1.size());
        Assertions.assertEquals(expected1, actual1);

        // the second test
        List<Integer> list2 = Arrays.asList(5, 2, 3, 4, 10, 100);
        List<Integer> expected2 = List.of(5);
        List<Integer> actual2 = App.take(list2, 1);
        Assertions.assertEquals(expected2, actual2);

        // the third test
        List<Integer> list3 = Arrays.asList(8, -1, 2, 0, 89);
        List<Integer> expected3 = Arrays.asList(8, -1, 2, 0, 89);
        List<Integer> actual3 = App.take(list3, list3.size() + 1);
        Assertions.assertEquals(expected3, actual3);

        // the fourth test
        List<Integer> list4 = new ArrayList<>();
        List<Integer> actual4 = App.take(list4, 1);
        assertThat(actual4).isEmpty();

        // the fifth test
        List<Integer> list5 = new ArrayList<>();
        List<Integer> actual5 = App.take(list5, -1);
        assertThat(actual5).isEmpty();

        // the sixth test
        List<Integer> list6 = new ArrayList<>();
        List<Integer> actual6 = App.take(list6, 0);
        assertThat(actual6).isEmpty();
        // END
    }
}
