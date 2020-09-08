package ru.karachev.formulaone.creator;

import ru.karachev.formulaone.domain.Racer;

import java.time.Duration;
import java.util.Map;

public interface RaceCreator {

    Map<Integer, Racer> createRace(Map<String, String> decryptedAbbreviation,
                                   Map<String, Duration> bestLapTime);
}
