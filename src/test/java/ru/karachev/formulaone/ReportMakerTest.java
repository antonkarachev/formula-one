package ru.karachev.formulaone;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.karachev.formulaone.creator.BestLapCreator;
import ru.karachev.formulaone.creator.RaceCreator;
import ru.karachev.formulaone.creator.ViewCreator;
import ru.karachev.formulaone.decryptor.AbbreviationDecryptor;
import ru.karachev.formulaone.domain.FileReader;
import ru.karachev.formulaone.domain.DataSource;
import ru.karachev.formulaone.domain.Racer;
import ru.karachev.formulaone.validator.Validator;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportMakerTest {

    @InjectMocks
    private ReportMaker reportMaker;

    @Mock
    Validator mockedValidator;

    @Mock
    private FileReader mockedFileReader;

    @Mock
    private AbbreviationDecryptor mockedAbbreviationDecryptor;

    @Mock
    private BestLapCreator mockedBestLapCounter;

    @Mock
    private RaceCreator mockedRaceCreator;

    @Mock
    private ViewCreator mockedViewCreator;

    @Test
    void makeReportShouldReturnStringWhenGetPathsInStringOfStartAndEndLogAndAbbreviationTxt() {

        String startLog = "start.log";
        String endLog = ".end.log";
        String abbreviationsTxt = "abbreviations.txt";
        int numberOfPrizes = 3;

        DataSource dataSource = DataSource.builder()
                .withStartLogFilePath(startLog)
                .withEndLogFilePath(endLog)
                .withAbbreviationsTxtFilePath(abbreviationsTxt)
                .withNumberOfPrizes(numberOfPrizes)
                .build();

        List<String> abbreviationData = new ArrayList<>();
        abbreviationData.add("AAA_Anton_Best Team");
        abbreviationData.add("BBB_Donny_Not a best team");
        abbreviationData.add("CCC_Johny_Worst Team");

        List<String> startTimeData = new ArrayList<>();
        startTimeData.add("AAA2018-05-24_12:00:00.000");
        startTimeData.add("BBB2018-05-24_12:10:00.000");
        startTimeData.add("CCC2018-05-24_12:15:00.000");

        List<String> endTimeData = new ArrayList<>();
        endTimeData.add("AAA2018-05-24_12:01:11.111");
        endTimeData.add("BBB2018-05-24_12:12:22.222");
        endTimeData.add("CCC2018-05-24_12:18:33.333");

        HashMap<String, String> abbreviationToNameAndTeam = new HashMap<>();
        abbreviationToNameAndTeam.put("AAA", "Anton_Best Team");
        abbreviationToNameAndTeam.put("BBB", "Donny_Not a best team");
        abbreviationToNameAndTeam.put("CCC", "Johny_Worst Team");

        LocalTime startTimeAAA = LocalTime.of(12, 0, 0, 0);
        LocalTime startTimeBBB = LocalTime.of(12, 10, 0, 0);
        LocalTime startTimeCCC = LocalTime.of(12, 15, 0, 0);
        LocalTime endTimeAAA = LocalTime.of(12, 1, 11, 111000000);
        LocalTime endTimeBBB = LocalTime.of(12, 12, 22, 222000000);
        LocalTime endTimeCCC = LocalTime.of(12, 18, 33, 333000000);

        HashMap<String, Duration> abbreviationToBestLapTime = new HashMap<>();
        abbreviationToBestLapTime.put("AAA", Duration.between(startTimeAAA, endTimeAAA));
        abbreviationToBestLapTime.put("BBB", Duration.between(startTimeBBB, endTimeBBB));
        abbreviationToBestLapTime.put("CCC", Duration.between(startTimeCCC, endTimeCCC));

        Racer racer1 = Racer.builder()
                .withAbbreviation("AAA")
                .withName("Anton")
                .withTeamName("Best Team")
                .withBestLapTime(Duration.between(startTimeAAA, endTimeAAA))
                .build();
        Racer racer2 = Racer.builder()
                .withAbbreviation("BBB")
                .withName("Donny")
                .withTeamName("Not a best team")
                .withBestLapTime(Duration.between(startTimeBBB, endTimeBBB))
                .build();
        Racer racer3 = Racer.builder()
                .withAbbreviation("CCC")
                .withName("Johny")
                .withTeamName("Worst Team")
                .withBestLapTime(Duration.between(startTimeCCC, endTimeCCC))
                .build();

        List<Racer> placesToRacer = new ArrayList<>();
        placesToRacer.add(racer1);
        placesToRacer.add(racer2);
        placesToRacer.add(racer3);

        StringBuilder expectedView = new StringBuilder();
        String expected = expectedView.append(String.format
                ("\n%02d. %-18s|%-27s|%02d:%02d.%03d\n", 1, "Anton", "Best Team", 1, 11, 111))
                .append(String.format("%02d. %-18s|%-27s|%02d:%02d.%03d\n", 2, "Donny", "Not a best team", 2, 22, 222))
                .append(String.format("%02d. %-18s|%-27s|%02d:%02d.%03d\n", 3, "Johny", "Worst Team", 3, 33, 333))
                .toString();

        doNothing().when(mockedValidator).validate(anyString());
        when(mockedFileReader.readFile(anyString()))
                .thenReturn(abbreviationData)
                .thenReturn(startTimeData)
                .thenReturn(endTimeData);
        when(mockedAbbreviationDecryptor.decryptAbbreviation(anyList()))
                .thenReturn(abbreviationToNameAndTeam);
        when(mockedBestLapCounter.countBestLap(anyList(), anyList()))
                .thenReturn(abbreviationToBestLapTime);
        when(mockedRaceCreator.createRace(anyMap(), anyMap())).thenReturn(placesToRacer);
        when(mockedViewCreator.createView(anyList(), anyInt())).thenReturn(expected);

        String actual = reportMaker.makeReport(dataSource);

        assertThat(actual).isEqualTo(expected);

        verify(mockedFileReader, times(3)).readFile(anyString());
        verify(mockedAbbreviationDecryptor).decryptAbbreviation(any());
        verify(mockedBestLapCounter).countBestLap(any(), any());
        verify(mockedRaceCreator).createRace(anyMap(), anyMap());
        verify(mockedViewCreator).createView(anyList(), anyInt());
    }

    @Test
    void makeReportShouldThrowExceptionWhenGetNotValidFilePath() {
        String startLog = "start.log";
        String endLog = ".end.log";
        String abbreviationsTxt = "abbreviations.txt";
        int numberOfPrizes = 3;

        DataSource dataSource = DataSource.builder()
                .withStartLogFilePath(startLog)
                .withEndLogFilePath(endLog)
                .withAbbreviationsTxtFilePath(abbreviationsTxt)
                .withNumberOfPrizes(numberOfPrizes)
                .build();
        doThrow(new IllegalArgumentException()).when(mockedValidator).validate(anyString());
        assertThrows(IllegalArgumentException.class, () ->
                reportMaker.makeReport(dataSource));
        verifyZeroInteractions(mockedFileReader, mockedAbbreviationDecryptor, mockedBestLapCounter,
                mockedRaceCreator, mockedViewCreator);
    }
}
