package ru.karachev.formulaone.domain;

import java.time.Duration;

public interface Racer {

    String getTeamName();

    String getName();

    Duration getBestLapTime();

}
