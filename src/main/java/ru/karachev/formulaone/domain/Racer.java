package ru.karachev.formulaone.domain;

import java.time.Duration;
import java.util.Objects;

public class Racer {

    private final String abbreviation;
    private final String name;
    private final String teamName;
    private final Duration bestLapTime;

    private Racer(Builder builder) {
        abbreviation = builder.abbreviation;
        name = builder.name;
        teamName = builder.teamName;
        bestLapTime = builder.bestLapTime;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Duration getBestLapTime() {
        return bestLapTime;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Racer{" +
                "abbreviation='" + abbreviation + '\'' +
                ", name='" + name + '\'' +
                ", teamName='" + teamName + '\'' +
                ", bestLapTime=" + bestLapTime +
                '}';
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

    public static class Builder {

        private String abbreviation;
        private String name;
        private String teamName;
        private Duration bestLapTime;

        private Builder() {
        }

        public Builder withAbbreviation(String abbreviation) {
            this.abbreviation = abbreviation;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withTeamName(String teamName) {
            this.teamName = teamName;
            return this;
        }

        public Builder withBestLapTime(Duration bestLapTime) {
            this.bestLapTime = bestLapTime;
            return this;
        }

        public Racer build() {
            return new Racer(this);
        }
    }

}
