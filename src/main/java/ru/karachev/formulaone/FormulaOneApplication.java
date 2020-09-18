package ru.karachev.formulaone;

import ru.karachev.formulaone.creator.BestLapCreator;
import ru.karachev.formulaone.creator.BestLapCreatorImpl;
import ru.karachev.formulaone.creator.RaceCreator;
import ru.karachev.formulaone.creator.RaceCreatorImpl;
import ru.karachev.formulaone.creator.ViewCreator;
import ru.karachev.formulaone.creator.ViewCreatorImpl;
import ru.karachev.formulaone.decryptor.AbbreviationDecryptor;
import ru.karachev.formulaone.decryptor.AbbreviationDecryptorImpl;
import ru.karachev.formulaone.domain.FileReader;
import ru.karachev.formulaone.domain.DataSource;
import ru.karachev.formulaone.validator.Validator;
import ru.karachev.formulaone.validator.ValidatorImpl;

public class FormulaOneApplication {

    public static void main(String[] args) {

        int numberOfPrizes = 15;
        String startLog = "./src/main/resources/start.log";
        String endLog = "./src/main/resources/end.log";
        String abbreviationsTxt = "./src/main/resources/abbreviations.txt";

        Validator validator = new ValidatorImpl();
        FileReader fileReader = new FileReader();
        AbbreviationDecryptor abbreviationDecryptor = new AbbreviationDecryptorImpl();
        BestLapCreator bestLapCreator = new BestLapCreatorImpl();
        RaceCreator raceCreator = new RaceCreatorImpl();
        ViewCreator viewCreator = new ViewCreatorImpl();
        DataSource dataSource = DataSource.builder()
                .withStartLogFilePath(startLog)
                .withEndLogFilePath(endLog)
                .withAbbreviationsTxtFilePath(abbreviationsTxt)
                .withNumberOfPrizes(numberOfPrizes)
                .build();

        ReportMaker reportMaker = new ReportMaker(validator, fileReader, abbreviationDecryptor,
                bestLapCreator, raceCreator, viewCreator);


        System.out.println(reportMaker.makeReport(dataSource));

    }
}
