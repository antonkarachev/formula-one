package ru.karachev.formulaone;

import ru.karachev.formulaone.creator.BestLapCreator;
import ru.karachev.formulaone.creator.RaceCreator;
import ru.karachev.formulaone.creator.ViewCreator;
import ru.karachev.formulaone.decryptor.AbbreviationDecryptor;
import ru.karachev.formulaone.domain.DataSource;
import ru.karachev.formulaone.domain.FileReader;
import ru.karachev.formulaone.domain.Racer;
import ru.karachev.formulaone.validator.Validator;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class ReportMaker {

    private final Validator validator;
    private final FileReader fileReader;
    private final AbbreviationDecryptor abbreviationDecryptor;
    private final BestLapCreator bestLapCreator;
    private final RaceCreator raceCreator;
    private final ViewCreator viewCreator;

    public ReportMaker(Validator validator, FileReader fileReader,
                       AbbreviationDecryptor abbreviationDecryptor,
                       BestLapCreator bestLapCreator, RaceCreator raceCreator,
                       ViewCreator viewCreator) {
        this.validator = validator;
        this.fileReader = fileReader;
        this.abbreviationDecryptor = abbreviationDecryptor;
        this.bestLapCreator = bestLapCreator;
        this.raceCreator = raceCreator;
        this.viewCreator = viewCreator;
    }

    public String makeReport(DataSource dataSource) {

        String startLog = dataSource.getStartLogFilePath();
        String endLog = dataSource.getEndLogFilePath();
        String abbreviationsTxt = dataSource.getAbbreviationsTxtFilePath();
        int numberOfPrizes = dataSource.getNumberOfPrizes();

        validator.validate(startLog);
        validator.validate(endLog);
        validator.validate(abbreviationsTxt);
        validator.validate(numberOfPrizes);

        List<String> dataFromStartLog = fileReader.readFile(startLog);
        List<String> dataFromEndLog = fileReader.readFile(endLog);
        List<String> dataFromAbbreviationsTxt = fileReader.readFile(abbreviationsTxt);

        Map<String, String> abbreviationToNameAndTeam =
                abbreviationDecryptor.decryptAbbreviation(dataFromAbbreviationsTxt);

        Map<String, Duration> abbreviationToBestLapTime =
                bestLapCreator.countBestLap(dataFromStartLog, dataFromEndLog);

        List<Racer> racerSortedByPlace = raceCreator.createRace(abbreviationToNameAndTeam,
                abbreviationToBestLapTime);

        return viewCreator.createView(racerSortedByPlace, numberOfPrizes);
    }

}
