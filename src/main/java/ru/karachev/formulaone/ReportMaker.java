package ru.karachev.formulaone;

import ru.karachev.formulaone.creator.BestLapCreator;
import ru.karachev.formulaone.creator.RaceCreator;
import ru.karachev.formulaone.creator.ViewCreator;
import ru.karachev.formulaone.decryptor.AbbreviationDecryptor;
import ru.karachev.formulaone.domain.Racer;
import ru.karachev.formulaone.domain.StreamMaker;

import java.time.Duration;
import java.util.Map;

public class ReportMaker {

    private final StreamMaker streamMaker;
    private final AbbreviationDecryptor abbreviationDecryptor;
    private final BestLapCreator bestLapCreator;
    private final RaceCreator raceCreator;
    private final ViewCreator viewCreator;

    public ReportMaker(StreamMaker streamMaker, AbbreviationDecryptor abbreviationDecryptor,
                       BestLapCreator bestLapCreator, RaceCreator raceCreator, ViewCreator viewCreator) {
        this.streamMaker = streamMaker;
        this.abbreviationDecryptor = abbreviationDecryptor;
        this.bestLapCreator = bestLapCreator;
        this.raceCreator = raceCreator;
        this.viewCreator = viewCreator;
    }

    public String makeReport(String startLog, String endLog, String abbreviationsTxt) {

        Map<String, String> decryptedAbbreviation =
                abbreviationDecryptor.decryptAbbreviation(streamMaker.makeStreamFromFile(abbreviationsTxt));

        Map<String, Duration> abbreviationToBestLapTime =
                bestLapCreator.countBestLap(streamMaker.makeStreamFromFile(startLog),
                        streamMaker.makeStreamFromFile(endLog));

        Map<Integer, Racer> racersSortedByTime = raceCreator.createRace(decryptedAbbreviation,
                abbreviationToBestLapTime);

        return viewCreator.createView(racersSortedByTime);
    }

}
