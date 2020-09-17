package ru.karachev.formulaone.domain;

public class DataRepository {

    private final String startLogFilePath;
    private final String endLogFilePath;
    private final String abbreviationsTxtFilePath;
    private final int numberOfPrizes;

    private DataRepository(Builder builder) {
        startLogFilePath = builder.startLogFilePath;
        endLogFilePath = builder.endLogFilePath;
        abbreviationsTxtFilePath = builder.abbreviationsTxtFilePath;
        numberOfPrizes = builder.numberOfPrizes;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getStartLogFilePath() {
        return startLogFilePath;
    }

    public String getEndLogFilePath() {
        return endLogFilePath;
    }

    public String getAbbreviationsTxtFilePath() {
        return abbreviationsTxtFilePath;
    }

    public int getNumberOfPrizes() {
        return numberOfPrizes;
    }

    public static class Builder {

        private String startLogFilePath;
        private String endLogFilePath;
        private String abbreviationsTxtFilePath;
        private int numberOfPrizes;

        private Builder() {
        }

        public Builder withStartLogFilePath(String startLogFilePath) {
            this.startLogFilePath = startLogFilePath;
            return this;
        }

        public Builder withEndLogFilePath(String endLogFilePath) {
            this.endLogFilePath = endLogFilePath;
            return this;
        }

        public Builder withAbbreviationsTxtFilePath(String abbreviationsTxtFilePath) {
            this.abbreviationsTxtFilePath = abbreviationsTxtFilePath;
            return this;
        }

        public Builder withNumberOfPrizes(int numberOfPrizes) {
            this.numberOfPrizes = numberOfPrizes;
            return this;
        }

        public DataRepository build() {
            return new DataRepository(this);
        }

    }
}
