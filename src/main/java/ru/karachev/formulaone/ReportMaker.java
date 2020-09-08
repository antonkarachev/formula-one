package ru.karachev.formulaone;

import ru.karachev.formulaone.creator.BestLapCreator;
import ru.karachev.formulaone.creator.BestLapCreatorImpl;
import ru.karachev.formulaone.creator.RaceCreator;
import ru.karachev.formulaone.creator.RaceCreatorImpl;
import ru.karachev.formulaone.creator.ViewCreator;
import ru.karachev.formulaone.creator.ViewCreatorImpl;
import ru.karachev.formulaone.domain.AbbreviationDecryptorImpl;
import ru.karachev.formulaone.domain.Racer;
import ru.karachev.formulaone.domain.StreamMakerImpl;

import java.time.Duration;
import java.util.Map;

public class ReportMaker {

    private final StreamMakerImpl streamMaker = new StreamMakerImpl();
    private final AbbreviationDecryptorImpl abbreviationDecryptor = new AbbreviationDecryptorImpl();
    private final BestLapCreator bestLapCreator = new BestLapCreatorImpl();
    private final RaceCreator raceCreator = new RaceCreatorImpl();
    private final ViewCreator viewCreator = new ViewCreatorImpl();

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
