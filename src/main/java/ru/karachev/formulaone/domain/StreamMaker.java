package ru.karachev.formulaone.domain;

import java.util.stream.Stream;

public interface StreamMaker {

    Stream<String> makeStreamFromFile(String filePath);
}
