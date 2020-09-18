package ru.karachev.formulaone.creator;

import ru.karachev.formulaone.domain.Racer;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public interface RaceCreator {

    List<Racer> createRace(Map<String, String> decryptedAbbreviation,
                           Map<String, Duration> bestLapTime);
}
