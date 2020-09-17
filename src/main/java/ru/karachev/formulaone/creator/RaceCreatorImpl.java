package ru.karachev.formulaone.creator;

import ru.karachev.formulaone.domain.Racer;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RaceCreatorImpl implements RaceCreator {

    private static final String UNDERSCORE = "_";
    private static final int PLACE_OF_NAME = 0;
    private static final int PLACE_OF_TEAM_NAME = 1;

    @Override
    public List<Racer> createRace(Map<String, String> decryptedAbbreviation,
                                  Map<String, Duration> bestLapTime) {

        return bestLapTime.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .map(x -> Racer.builder()
                        .withAbbreviation(x.getKey())
                        .withName(getFromAbbreviation(PLACE_OF_NAME, decryptedAbbreviation.get(x.getKey())))
                        .withTeamName(getFromAbbreviation(PLACE_OF_TEAM_NAME, decryptedAbbreviation.get(x.getKey())))
                        .withBestLapTime(x.getValue())
                        .build())
                .collect(Collectors.toList());
    }

    private String getFromAbbreviation(int placeOfWhatGet, String abbreviationLine) {
        return abbreviationLine.split(UNDERSCORE)[placeOfWhatGet];
    }

}
