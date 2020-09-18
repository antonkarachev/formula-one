package ru.karachev.formulaone.decryptor;

import java.util.List;
import java.util.Map;

public interface AbbreviationDecryptor {

    Map<String, String> decryptAbbreviation(List<String> dataFromFile);

}
