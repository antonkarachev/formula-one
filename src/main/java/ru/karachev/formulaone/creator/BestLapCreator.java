package ru.karachev.formulaone.creator;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public interface BestLapCreator {

    Map<String, Duration> countBestLap(List<String> startTimeDataStream,
                                       List<String> endTimeDataStream);

}
