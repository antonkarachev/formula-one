package ru.karachev.formulaone.decryptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbbreviationDecryptorImpl implements AbbreviationDecryptor {

    @Override
    public Map<String, String> decryptAbbreviation(List<String> dataFromFile) {
        HashMap<String, String> abbreviationToNameAndTeam = new HashMap<>();

        dataFromFile.forEach(line -> abbreviationToNameAndTeam
                .put(line.substring(0, 3), line.substring(4)));

        return abbreviationToNameAndTeam;
    }

}
