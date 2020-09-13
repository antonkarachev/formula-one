package ru.karachev.formulaone;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.karachev.formulaone.creator.BestLapCreatorImpl;
import ru.karachev.formulaone.creator.RaceCreatorImpl;
import ru.karachev.formulaone.creator.ViewCreatorImpl;
import ru.karachev.formulaone.decryptor.AbbreviationDecryptorImpl;
import ru.karachev.formulaone.domain.Racer;
import ru.karachev.formulaone.domain.StreamMaker;

import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportMakerTest {

    @InjectMocks
    private ReportMaker reportMaker;

    @Mock
    private StreamMaker mockedStreamMaker;

    @Mock
    private AbbreviationDecryptorImpl mockedAbbreviationDecryptor;

    @Mock
    private BestLapCreatorImpl mockedBestLapCounter;

    @Mock
    private RaceCreatorImpl mockedRaceCreator;

    @Mock
    private ViewCreatorImpl mockedViewCreator;

    @Test
    void makeReportShouldReturnStringWhenGetPathsInStringOfStartAndEndLogAndAbbreviationTxt() {

        String startLog = "start.log";
        String endLog = ".end.log";
        String abbreviationsTxt = "abbreviations.txt";

        Stream<String> abbreviationStream = Stream.of("AAA_Anton_Best Team",
                "BBB_Donny_Not a best team",
                "CCC_Johny_Worst Team");

        Stream<String> startTimeDataStream = Stream.of("AAA2018-05-24_12:00:00.000",
                "BBB2018-05-24_12:10:00.000",
                "CCC2018-05-24_12:15:00.000");

        Stream<String> endTimeDataStream = Stream.of("AAA2018-05-24_12:01:11.111",
                "BBB2018-05-24_12:12:22.222",
                "CCC2018-05-24_12:18:33.333");

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

        Racer racer1 = new Racer("AAA", "Anton",
                "Best Team", Duration.between(startTimeAAA, endTimeAAA));
        Racer racer2 = new Racer("BBB", "Donny",
                "Not a best team", Duration.between(startTimeBBB, endTimeBBB));
        Racer racer3 = new Racer("CCC", "Johny",
                "Worst Team", Duration.between(startTimeCCC, endTimeCCC));

        Map<Integer, Racer> racersSortedByTime = new HashMap<>();
        racersSortedByTime.put(1, racer1);
        racersSortedByTime.put(2, racer2);
        racersSortedByTime.put(3, racer3);

        StringBuilder expectedView = new StringBuilder();
        String expected = expectedView.append(String.format
                ("\n%02d. %-18s|%-27s|%02d:%02d.%03d\n", 1, "Anton", "Best Team", 1, 11, 111))
                .append(String.format("%02d. %-18s|%-27s|%02d:%02d.%03d\n", 2, "Donny", "Not a best team", 2, 22, 222))
                .append(String.format("%02d. %-18s|%-27s|%02d:%02d.%03d\n", 3, "Johny", "Worst Team", 3, 33, 333))
                .append("------------------------------------------------------------\n")
                .toString();

        when(mockedStreamMaker.makeStreamFromFile(anyString()))
                .thenReturn(abbreviationStream)
                .thenReturn(startTimeDataStream)
                .thenReturn(endTimeDataStream);
        when(mockedAbbreviationDecryptor.decryptAbbreviation(any()))
                .thenReturn(abbreviationToNameAndTeam);
        when(mockedBestLapCounter.countBestLap(any(), any()))
                .thenReturn(abbreviationToBestLapTime);
        when(mockedRaceCreator.createRace(anyMap(), anyMap())).thenReturn(racersSortedByTime);
        when(mockedViewCreator.createView(anyMap())).thenReturn(expected);

        String actual = reportMaker.makeReport(startLog, endLog, abbreviationsTxt);

        assertThat(actual, is(expected));

        verify(mockedStreamMaker, times(3)).makeStreamFromFile(anyString());
        verify(mockedAbbreviationDecryptor).decryptAbbreviation(any());
        verify(mockedBestLapCounter).countBestLap(any(),any());
        verify(mockedRaceCreator).createRace(anyMap(),anyMap());
        verify(mockedViewCreator).createView(anyMap());

    }
}