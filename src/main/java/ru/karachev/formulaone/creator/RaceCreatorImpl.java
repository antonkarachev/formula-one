package ru.karachev.formulaone.creator;

import ru.karachev.formulaone.domain.Racer;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RaceCreatorImpl implements RaceCreator {

    private static final String UNDERSCORE = "_";
    private static final int PLACE_OF_NAME_IN_DESCRIPTION = 0;
    private static final int PLACE_OF_TEAM_NAME_IN_DESCRIPTION = 1;

    @Override
    public List<Racer> createRace(Map<String, String> decryptedAbbreviation,
                                  Map<String, Duration> bestLapTime) {

        return bestLapTime.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .map(x -> Racer.newBuilder()
                        .withAbbreviation(x.getKey())
                        .withName(getNameFromAbbreviation(decryptedAbbreviation.get(x.getKey())))
                        .withTeamName(getTeamNameFromAbbreviation(decryptedAbbreviation.get(x.getKey())))
                        .withBestLapTime(x.getValue())
                        .build())
                .collect(Collectors.toList());
    }

    private String getNameFromAbbreviation(String abbreviationLine) {

        return abbreviationLine.split(UNDERSCORE)[PLACE_OF_NAME_IN_DESCRIPTION];
    }

    private String getTeamNameFromAbbreviation(String abbreviationLine) {

        return abbreviationLine.split(UNDERSCORE)[PLACE_OF_TEAM_NAME_IN_DESCRIPTION];
    }

}
