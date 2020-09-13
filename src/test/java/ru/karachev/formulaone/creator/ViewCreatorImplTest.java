package ru.karachev.formulaone.creator;

import org.junit.jupiter.api.Test;
import ru.karachev.formulaone.domain.Racer;

import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class ViewCreatorImplTest {

    private final ViewCreator viewCreator = new ViewCreatorImpl();

    @Test
    void createViewShouldReturnStringInOrderFormatWhenGettingMapWithPlaceToRacer() {

        LocalTime startTimeAAA = LocalTime.of(12, 00, 00, 0);
        LocalTime startTimeBBB = LocalTime.of(12, 10, 00, 0);
        LocalTime startTimeCCC = LocalTime.of(12, 15, 00, 0);
        LocalTime endTimeAAA = LocalTime.of(12, 01, 11, 111000000);
        LocalTime endTimeBBB = LocalTime.of(12, 12, 22, 222000000);
        LocalTime endTimeCCC = LocalTime.of(12, 18, 33, 333000000);

        Racer racer1 = new Racer("AAA", "Anton",
                "Best Team", Duration.between(startTimeAAA, endTimeAAA));
        Racer racer2 = new Racer("BBB", "Donny",
                "Not a best team", Duration.between(startTimeBBB, endTimeBBB));
        Racer racer3 = new Racer("CCC", "Johny",
                "Worst Team", Duration.between(startTimeCCC, endTimeCCC));

        Map<Integer, Racer> placeToRacer = new HashMap<>();
        placeToRacer.put(1, racer1);
        placeToRacer.put(2, racer2);
        placeToRacer.put(3, racer3);

        StringBuilder expectedView = new StringBuilder();
        String expected = expectedView.append(String.format
                ("\n%02d. %-18s|%-27s|%02d:%02d.%03d\n", 1, "Anton", "Best Team", 1, 11, 111))
                .append(String.format("%02d. %-18s|%-27s|%02d:%02d.%03d\n", 2, "Donny", "Not a best team", 2, 22, 222))
                .append(String.format("%02d. %-18s|%-27s|%02d:%02d.%03d\n", 3, "Johny", "Worst Team", 3, 33, 333))
                .append("------------------------------------------------------------\n")
                .toString();

        String actual = viewCreator.createView(placeToRacer);

        assertThat(actual, is(expected));
    }
}