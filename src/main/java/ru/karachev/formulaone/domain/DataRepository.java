package ru.karachev.formulaone.domain;

public class DataRepository {

    private String startLogFilePath;
    private String endLogFilePath;
    private String abbreviationsTxtFilePath;
    private int numberOfPrizes;

    private DataRepository() {

    }

    public static Builder newBuilder() {
        return new DataRepository().new Builder();
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

    public class Builder {

        private Builder() {

        }

        public Builder withStartLogFilePath(String startLogFilePath) {
            DataRepository.this.startLogFilePath = startLogFilePath;
            return this;
        }

        public Builder withEndLogFilePath(String endLogFilePath) {
            DataRepository.this.endLogFilePath = endLogFilePath;
            return this;
        }

        public Builder withAbbreviationsTxtFilePath(String abbreviationsTxtFilePath) {
            DataRepository.this.abbreviationsTxtFilePath = abbreviationsTxtFilePath;
            return this;
        }

        public Builder withNumberOfPrizes(int numberOfPrizes) {
            DataRepository.this.numberOfPrizes = numberOfPrizes;
            return this;
        }

        public DataRepository build(){
            DataRepository dataRepository = new DataRepository();
            dataRepository.startLogFilePath = DataRepository.this.startLogFilePath;
            dataRepository.endLogFilePath = DataRepository.this.endLogFilePath;
            dataRepository.abbreviationsTxtFilePath = DataRepository.this.abbreviationsTxtFilePath;
            dataRepository.numberOfPrizes = DataRepository.this.numberOfPrizes;
            return dataRepository;
        }

    }
}
