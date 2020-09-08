package ru.karachev.formulaone.domain;

import java.time.Duration;
import java.util.Objects;

public class RacerImpl implements Racer {

    private String abbreviation;
    private String name;
    private String teamName;
    private Duration bestLapTime;

    public RacerImpl(String abbreviation, String name, String teamName, Duration bestLapTime) {
        this.abbreviation = abbreviation;
        this.name = name;
        this.teamName = teamName;
        this.bestLapTime = bestLapTime;
    }

    @Override
    public Duration getBestLapTime() {
        return bestLapTime;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getTeamName() {
        return teamName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RacerImpl racer = (RacerImpl) o;
        return Objects.equals(abbreviation, racer.abbreviation) &&
                Objects.equals(name, racer.name) &&
                Objects.equals(teamName, racer.teamName) &&
                Objects.equals(bestLapTime, racer.bestLapTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(abbreviation, name, teamName, bestLapTime);
    }
}
