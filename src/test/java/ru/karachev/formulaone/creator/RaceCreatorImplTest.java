package ru.karachev.formulaone.creator;

import org.junit.jupiter.api.Test;
import ru.karachev.formulaone.domain.Racer;

import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class RaceCreatorImplTest {

    RaceCreator raceCreator = new RaceCreatorImpl();

    @Test
    void createRaceShouldReturnMapPlaceToRacersSortedByPlace() {

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

        Map<Integer, Racer> expected = new HashMap<>();
        expected.put(1, racer1);
        expected.put(2, racer2);
        expected.put(3, racer3);

        Map<String, String> decryptedAbbreviation = new HashMap<>();
        decryptedAbbreviation.put("AAA", "Anton_Best Team");
        decryptedAbbreviation.put("BBB", "Donny_Not a best team");
        decryptedAbbreviation.put("CCC", "Johny_Worst Team");

        Map<String, Duration> bestLapTime = new HashMap<>();
        bestLapTime.put("AAA", Duration.between(startTimeAAA, endTimeAAA));
        bestLapTime.put("BBB", Duration.between(startTimeBBB, endTimeBBB));
        bestLapTime.put("CCC", Duration.between(startTimeCCC, endTimeCCC));

        Map<Integer, Racer> actual = raceCreator.createRace(decryptedAbbreviation, bestLapTime);

        assertThat(actual, is(expected));
    }

}