package ru.karachev.formulaone.creator;

import org.junit.jupiter.api.Test;
import ru.karachev.formulaone.domain.Racer;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ViewCreatorImplTest {

    private final ViewCreator viewCreator = new ViewCreatorImpl();

    @Test
    void createViewShouldReturnStringInOrderFormatWhenGettingMapWithPlaceToRacer() {

        LocalTime startTimeAAA = LocalTime.of(12, 0, 0, 0);
        LocalTime startTimeBBB = LocalTime.of(12, 10, 0, 0);
        LocalTime startTimeCCC = LocalTime.of(12, 15, 0, 0);
        LocalTime endTimeAAA = LocalTime.of(12, 1, 11, 111000000);
        LocalTime endTimeBBB = LocalTime.of(12, 12, 22, 222000000);
        LocalTime endTimeCCC = LocalTime.of(12, 18, 33, 333000000);

        Racer racer1 = Racer.newBuilder()
                .withAbbreviation("AAA")
                .withName("Anton")
                .withTeamName("Best Team")
                .withBestLapTime(Duration.between(startTimeAAA, endTimeAAA))
                .build();
        Racer racer2 = Racer.newBuilder()
                .withAbbreviation("BBB")
                .withName("Donny")
                .withTeamName("Not a best team")
                .withBestLapTime(Duration.between(startTimeBBB, endTimeBBB))
                .build();
        Racer racer3 = Racer.newBuilder()
                .withAbbreviation("CCC")
                .withName("Johny")
                .withTeamName("Worst Team")
                .withBestLapTime(Duration.between(startTimeCCC, endTimeCCC))
                .build();

        List<Racer> racerSortedByPlace = new ArrayList<>();
        racerSortedByPlace.add(racer1);
        racerSortedByPlace.add(racer2);
        racerSortedByPlace.add(racer3);

        StringBuilder expectedView = new StringBuilder();
        String expected = expectedView.append(String.format
                ("%02d. %-18s|%-27s|%02d:%02d.%03d\n", 1, "Anton", "Best Team", 1, 11, 111))
                .append(String.format("%02d. %-18s|%-27s|%02d:%02d.%03d\n", 2, "Donny", "Not a best team", 2, 22, 222))
                .append("------------------------------------------------------------\n")
                .append(String.format("%02d. %-18s|%-27s|%02d:%02d.%03d\n", 3, "Johny", "Worst Team", 3, 33, 333))
                .toString();

        String actual = viewCreator.createView(racerSortedByPlace, 2);

        assertThat(actual).isEqualTo(expected);
    }
}