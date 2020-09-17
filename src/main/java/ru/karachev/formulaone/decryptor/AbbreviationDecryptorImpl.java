package ru.karachev.formulaone.decryptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbbreviationDecryptorImpl implements AbbreviationDecryptor {

    private static final int ABBREVIATION_STARTS_AT = 0;
    private static final int ABBREVIATION_ENDS_AT = 3;
    private static final int TEAM_AND_NAME_PLACE_IN_LINE = 4;

    @Override
    public Map<String, String> decryptAbbreviation(List<String> dataFromFile) {
        Map<String, String> abbreviationToNameAndTeam = new HashMap<>();

        dataFromFile.forEach(line -> abbreviationToNameAndTeam
                .put(line.substring(ABBREVIATION_STARTS_AT, ABBREVIATION_ENDS_AT),
                        line.substring(TEAM_AND_NAME_PLACE_IN_LINE)));

        return abbreviationToNameAndTeam;
    }

}
