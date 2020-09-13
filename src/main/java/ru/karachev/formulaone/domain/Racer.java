package ru.karachev.formulaone.domain;

import java.time.Duration;
import java.util.Objects;

public class Racer {

    private String abbreviation;
    private String name;
    private String teamName;
    private Duration bestLapTime;

    public Racer(String abbreviation, String name, String teamName, Duration bestLapTime) {
        this.abbreviation = abbreviation;
        this.name = name;
        this.teamName = teamName;
        this.bestLapTime = bestLapTime;
    }

    public Duration getBestLapTime() {
        return bestLapTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Racer racer = (Racer) o;
        return Objects.equals(abbreviation, racer.abbreviation) &&
                Objects.equals(name, racer.name) &&
                Objects.equals(teamName, racer.teamName) &&
                Objects.equals(bestLapTime, racer.bestLapTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(abbreviation, name, teamName, bestLapTime);
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getName() {
        return name;
    }
}
