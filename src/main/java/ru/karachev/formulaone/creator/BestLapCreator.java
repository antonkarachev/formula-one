package ru.karachev.formulaone.creator;

import java.time.Duration;
import java.util.Map;
import java.util.stream.Stream;

public interface BestLapCreator {

    Map<String, Duration> countBestLap(Stream<String> startTimeDataStream,
                                       Stream<String> endTimeDataStream);

}
