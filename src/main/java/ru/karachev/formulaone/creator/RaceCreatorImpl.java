package ru.karachev.formulaone.creator;

import ru.karachev.formulaone.domain.Racer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RaceCreatorImpl implements RaceCreator {

    private static final String UNDERSCORE = "_";

    @Override
    public Map<Integer, Racer> createRace(Map<String, String> decryptedAbbreviation,
                                          Map<String, Duration> bestLapTime) {

        Map<String, Racer> abbreviationToRacer = decryptedAbbreviation.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        x -> new Racer(
                                x.getKey(),
                                getNameFromAbb(decryptedAbbreviation, x.getKey()),
                                getTeamNameFromAbb(decryptedAbbreviation, x.getKey()),
                                bestLapTime.get(x.getKey()))));

        List<Racer> racersSortedByTime = new ArrayList<>(abbreviationToRacer.values());
        racersSortedByTime.sort(Comparator.comparing(Racer::getBestLapTime));

        return racersSortedByTime
                .stream()
                .collect(LinkedHashMap::new,
                        (map, racer) -> map.put(map.size() + 1, racer),
                        (map, map2) -> {
                        });
    }

    public String getNameFromAbb(Map<String, String> decryptedAbbreviation,
                                 String abbreviation) {
        return decryptedAbbreviation.get(abbreviation).split(UNDERSCORE)[0];
    }

    public String getTeamNameFromAbb(Map<String, String> decryptedAbbreviation,
                                     String abbreviation) {
        return decryptedAbbreviation.get(abbreviation).split(UNDERSCORE)[1];
    }
}
