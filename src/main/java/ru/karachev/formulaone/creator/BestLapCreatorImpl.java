package ru.karachev.formulaone.creator;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BestLapCreatorImpl implements BestLapCreator {

    private static final String SEPARATOR = "_";
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ISO_TIME;

    @Override
    public Map<String, Duration> countBestLap(Stream<String> startTimeDataStream, Stream<String> endTimeDataStream) {
        Map<String, LocalTime> startTimesToAbbreviation = readStartTimesFromLog(startTimeDataStream);
        Map<String, LocalTime> endTimesToAbbreviation = readEndTimesFromLog(endTimeDataStream);

        return startTimesToAbbreviation.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        x -> Duration.between(x.getValue(), endTimesToAbbreviation.get(x.getKey()))));
    }

    private Map<String, LocalTime> readStartTimesFromLog(Stream<String> startTimesData) {
        HashMap<String, LocalTime> startTimesToAbbreviation = new HashMap<>();

        startTimesData.forEach(line ->
                startTimesToAbbreviation.put(line.substring(0, 3),
                        LocalTime.parse(line.split(SEPARATOR)[1], dateFormat)));

        return startTimesToAbbreviation;
    }

    private Map<String, LocalTime> readEndTimesFromLog(Stream<String> endTimesData) {
        HashMap<String, LocalTime> endTimesToAbbreviation = new HashMap<>();

        endTimesData.forEach(line ->
                endTimesToAbbreviation.put(line.substring(0, 3),
                        LocalTime.parse(line.split(SEPARATOR)[1], dateFormat)));

        return endTimesToAbbreviation;
    }
}
