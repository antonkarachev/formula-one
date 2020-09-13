package ru.karachev.formulaone.decryptor;

import java.util.HashMap;
import java.util.stream.Stream;

public interface AbbreviationDecryptor {

    HashMap<String, String> decryptAbbreviation(Stream<String> dataFromFile);

}
