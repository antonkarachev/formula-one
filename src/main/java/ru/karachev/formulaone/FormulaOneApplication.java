package ru.karachev.formulaone;

import ru.karachev.formulaone.creator.BestLapCreator;
import ru.karachev.formulaone.creator.BestLapCreatorImpl;
import ru.karachev.formulaone.creator.RaceCreator;
import ru.karachev.formulaone.creator.RaceCreatorImpl;
import ru.karachev.formulaone.creator.ViewCreator;
import ru.karachev.formulaone.creator.ViewCreatorImpl;
import ru.karachev.formulaone.decryptor.AbbreviationDecryptor;
import ru.karachev.formulaone.decryptor.AbbreviationDecryptorImpl;
import ru.karachev.formulaone.domain.StreamMaker;

public class FormulaOneApplication {

    public static void main(String[] args) {

        StreamMaker streamMaker = new StreamMaker();
        AbbreviationDecryptor abbreviationDecryptor = new AbbreviationDecryptorImpl();
        BestLapCreator bestLapCreator = new BestLapCreatorImpl();
        RaceCreator raceCreator = new RaceCreatorImpl();
        ViewCreator viewCreator = new ViewCreatorImpl();

        ReportMaker reportMaker = new ReportMaker(streamMaker, abbreviationDecryptor,
                bestLapCreator, raceCreator, viewCreator);
        String startLog = "./src/main/resources/start.log";
        String endLog = "./src/main/resources/end.log";
        String abbreviationsTxt = "./src/main/resources/abbreviations.txt";

        System.out.println(reportMaker.makeReport(startLog, endLog, abbreviationsTxt));

    }
}
