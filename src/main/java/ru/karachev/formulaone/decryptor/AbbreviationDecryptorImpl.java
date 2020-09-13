package ru.karachev.formulaone.decryptor;

import java.util.HashMap;
import java.util.stream.Stream;

public class AbbreviationDecryptorImpl implements AbbreviationDecryptor {

    @Override
    public HashMap<String, String> decryptAbbreviation(Stream<String> dataFromFile) {
        HashMap<String, String> abbreviationToNameAndTeam = new HashMap<>();

        dataFromFile.forEach(line -> abbreviationToNameAndTeam
                .put(line.substring(0, 3), line.substring(4)));

        return abbreviationToNameAndTeam;
    }

}
