package ru.karachev.formulaone.domain;

import java.util.HashMap;
import java.util.stream.Stream;

public class AbbreviationDecryptorImpl implements AbbreviationDecryptor {

    @Override
    public HashMap<String, String> decryptAbbreviation(Stream<String> dataFromFile) {
        HashMap<String, String> decryptedAbbreviation = new HashMap<>();

        dataFromFile.forEach(line -> decryptedAbbreviation
                .put(line.substring(0, 3), line.substring(4)));

        return decryptedAbbreviation;
    }

}
