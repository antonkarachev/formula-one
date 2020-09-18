package ru.karachev.formulaone.creator;

import org.junit.jupiter.api.Test;
import ru.karachev.formulaone.domain.Racer;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class RaceCreatorImplTest {

    private final RaceCreator raceCreator = new RaceCreatorImpl();

    @Test
    void createRaceShouldReturnListOfRacersSortedByPlace() {

        LocalTime startTimeAAA = LocalTime.of(12, 0, 0, 0);
        LocalTime startTimeBBB = LocalTime.of(12, 10, 0, 0);
        LocalTime startTimeCCC = LocalTime.of(12, 15, 0, 0);
        LocalTime endTimeAAA = LocalTime.of(12, 1, 11, 111000000);
        LocalTime endTimeBBB = LocalTime.of(12, 12, 22, 222000000);
        LocalTime endTimeCCC = LocalTime.of(12, 18, 33, 333000000);

        Racer racer1 = Racer.builder()
                .withAbbreviation("AAA")
                .withName("Anton")
                .withTeamName("Best Team")
                .withBestLapTime(Duration.between(startTimeAAA, endTimeAAA))
                .build();
        Racer racer2 = Racer.builder()
                .withAbbreviation("BBB")
                .withName("Donny")
                .withTeamName("Not a best team")
                .withBestLapTime(Duration.between(startTimeBBB, endTimeBBB))
                .build();
        Racer racer3 = Racer.builder()
                .withAbbreviation("CCC")
                .withName("Johny")
                .withTeamName("Worst Team")
                .withBestLapTime(Duration.between(startTimeCCC, endTimeCCC))
                .build();

        Map<String, String> decryptedAbbreviation = new HashMap<>();
        decryptedAbbreviation.put("AAA", "Anton_Best Team");
        decryptedAbbreviation.put("BBB", "Donny_Not a best team");
        decryptedAbbreviation.put("CCC", "Johny_Worst Team");

        Map<String, Duration> bestLapTime = new HashMap<>();
        bestLapTime.put("AAA", Duration.between(startTimeAAA, endTimeAAA));
        bestLapTime.put("BBB", Duration.between(startTimeBBB, endTimeBBB));
        bestLapTime.put("CCC", Duration.between(startTimeCCC, endTimeCCC));

        List<Racer> expected = new ArrayList<>();
        expected.add(racer1);
        expected.add(racer2);
        expected.add(racer3);

        List<Racer> actual = raceCreator.createRace(decryptedAbbreviation, bestLapTime);

        assertThat(actual).isEqualTo(expected);
    }

}
