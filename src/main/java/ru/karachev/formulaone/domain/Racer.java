package ru.karachev.formulaone.domain;

import java.time.Duration;
import java.util.Objects;

public class Racer {

    private String abbreviation;
    private String name;
    private String teamName;
    private Duration bestLapTime;

    private Racer (){

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

    public static Builder newBuilder(){
        return new Racer().new Builder();
    }

    public class Builder{

        private Builder (){

        }

        public Builder withAbbreviation (String abbreviation){
            Racer.this.abbreviation = abbreviation;
            return this;
        }

        public Builder withName (String name) {
            Racer.this.name = name;
            return this;
        }

        public Builder withTeamName (String teamName){
            Racer.this.teamName = teamName;
            return this;
        }

        public Builder withBestLapTime (Duration bestLapTime){
            Racer.this.bestLapTime = bestLapTime;
            return this;
        }

        public Racer build(){
            Racer racer = new Racer();
            racer.abbreviation = Racer.this.abbreviation;
            racer.name = Racer.this.name;
            racer.teamName = Racer.this.teamName;
            racer.bestLapTime = Racer.this.bestLapTime;
            return racer;
        }
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

}
