package ru.karachev.formulaone;

public class FormulaOneApplication {

    public static void main(String[] args) {

        ReportMaker reportMaker = new ReportMaker();
        String startLog = "./src/main/resources/start.log";
        String endLog = "./src/main/resources/end.log";
        String abbreviationsTxt = "./src/main/resources/abbreviations.txt";

        System.out.println(reportMaker.makeReport(startLog, endLog, abbreviationsTxt));

    }
}
