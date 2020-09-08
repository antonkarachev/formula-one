package ru.karachev.formulaone.creator;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class BestLapCreatorImplTest {

    private final BestLapCreator bestLapCreator = new BestLapCreatorImpl();

    @Test
    void countBestLapShouldReturnMapAbbreviationToBestLapTime() {

        Stream startTimeData = Stream.of("AAA2018-05-24_12:00:00.000",
                "BBB2018-05-24_12:10:00.000",
                "CCC2018-05-24_12:15:00.000");

        Stream endTimeData = Stream.of("AAA2018-05-24_12:01:11.000",
                "BBB2018-05-24_12:12:22.000",
                "CCC2018-05-24_12:18:33.000");

        LocalTime startTimeAAA = LocalTime.of(12, 00, 00, 0);
        LocalTime startTimeBBB = LocalTime.of(12, 10, 00, 0);
        LocalTime startTimeCCC = LocalTime.of(12, 15, 00, 0);
        LocalTime endTimeAAA = LocalTime.of(12, 01, 11, 0);
        LocalTime endTimeBBB = LocalTime.of(12, 12, 22, 0);
        LocalTime endTimeCCC = LocalTime.of(12, 18, 33, 0);

        Map<String, Duration> expected = new HashMap<>();
        expected.put("AAA", Duration.between(startTimeAAA, endTimeAAA));
        expected.put("BBB", Duration.between(startTimeBBB, endTimeBBB));
        expected.put("CCC", Duration.between(startTimeCCC, endTimeCCC));

        Map<String, Duration> actual = bestLapCreator.countBestLap(startTimeData, endTimeData);

        assertThat(actual, is(expected));

    }
}
