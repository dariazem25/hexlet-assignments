package exercise;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;


class AppTest {

    @Test
    void testBuildAppartmentsList1() {
        List<Home> appartments = new ArrayList<>(List.of(
                new Flat(41, 3, 10),
                new Cottage(125.5, 2),
                new Flat(80, 10, 2),
                new Cottage(150, 3)
        ));

        List<String> expected = new ArrayList<>(List.of(
                "Квартира площадью 44.0 метров на 10 этаже",
                "Квартира площадью 90.0 метров на 2 этаже",
                "2 этажный коттедж площадью 125.5 метров"
        ));

        List<String> result = App.buildAppartmentsList(appartments, 3);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testBuildAppartmentsList2() {
        List<Home> appartments = new ArrayList<>(List.of(
                new Cottage(100, 1),
                new Flat(190, 10, 2),
                new Flat(180, 30, 5),
                new Cottage(250, 3)
        ));

        List<String> expected = new ArrayList<>(List.of(
                "1 этажный коттедж площадью 100.0 метров",
                "Квартира площадью 200.0 метров на 2 этаже",
                "Квартира площадью 210.0 метров на 5 этаже",
                "3 этажный коттедж площадью 250.0 метров"
        ));

        List<String> result = App.buildAppartmentsList(appartments, 4);
        assertThat(result).isEqualTo(expected);

    }

    @Test
    void testBuildAppartmentsList3() {
        List<Home> appartments = new ArrayList<>();
        List<String> expected = new ArrayList<>();
        List<String> result = App.buildAppartmentsList(appartments, 10);
        assertThat(result).isEqualTo(expected);
    }

    // BEGIN
    @Test
    void testReversedToString() {
        CharSequence text = new ReversedSequence("abcdef");
        var actual = text.toString();
        assertThat(actual).isEqualTo("fedcba");
    }

    @Test
    void testReversedCharAt() {
        CharSequence text = new ReversedSequence("abcaef");
        var actual = text.charAt(3);
        assertThat(actual).isEqualTo('c');
    }

    @Test
    void testReversedLength() {
        CharSequence text = new ReversedSequence("abcaef");
        var actual = text.length();
        assertThat(actual).isEqualTo(6);
    }

    @Test
    void testReversedSubsequence() {
        CharSequence text = new ReversedSequence("abcaef");
        var actual = text.subSequence(0, 5);
        assertThat(actual).isEqualTo("feacb");
    }

    @Test
    void testReversedNonExistentPosition() {
        CharSequence text = new ReversedSequence("abcaef");
        Exception exception = assertThrows(RuntimeException.class, () -> text.charAt(6));

        String expectedMessage = "Character does not exist";
        String actualMessage = exception.getMessage();
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    void testReversedEmptySubsequence() {
        CharSequence text = new ReversedSequence("");
        Exception exception = assertThrows(RuntimeException.class, () -> text.charAt(0));

        String expectedMessage = "Character does not exist";
        String actualMessage = exception.getMessage();
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    void testReversedSubsequenceGreaterThanSequence() {
        CharSequence text = new ReversedSequence("abcaef");
        assertThrows(StringIndexOutOfBoundsException.class, () -> text.subSequence(0, 10));
    }

    @Test
    void testReversedBeginIndexGreaterThanEnd() {
        CharSequence text = new ReversedSequence("abcaef");
        assertThrows(StringIndexOutOfBoundsException.class, () -> text.subSequence(5, 3));
    }

    @Test
    void testReversedLengthEmpty() {
        CharSequence text = new ReversedSequence("");
        var actual = text.length();
        assertThat(actual).isEqualTo(0);
    }

    @Test
    void testReversedToStringEmpty() {
        CharSequence text = new ReversedSequence("");
        var actual = text.toString();
        assertThat(actual).isEqualTo("");
    }
    // END
}
