package ru.karachev.formulaone.creator;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BestLapCreatorImpl implements BestLapCreator {

    private static final String SEPARATOR = "_";
    private static final int ABBREVIATION_STARTS_AT = 0;
    private static final int ABBREVIATION_ENDS_AT = 3;
    private static final int TIME_PLACE_IN_LINE = 1;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_TIME;

    @Override
    public Map<String, Duration> countBestLap(List<String> startTimesData, List<String> endTimesData) {
        Map<String, LocalTime> startTimesToAbbreviation = readTimesFromData(startTimesData);
        Map<String, LocalTime> endTimesToAbbreviation = readTimesFromData(endTimesData);

        return startTimesToAbbreviation.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        x -> Duration.between(x.getValue(), endTimesToAbbreviation.get(x.getKey()))));
    }

    private Map<String, LocalTime> readTimesFromData(List<String> timesData) {

        return timesData.stream().collect(Collectors.toMap(
                x -> x.substring(ABBREVIATION_STARTS_AT,
                        ABBREVIATION_ENDS_AT),
                x -> LocalTime.parse(x.split(SEPARATOR)[TIME_PLACE_IN_LINE],
                        DATE_FORMAT)));
    }
}
