package ru.karachev.formulaone.domain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader {

    public List<String> readFile(String filePath) {
        List<String> data;

        try(Stream <String > timesData = Files.lines(Paths.get(filePath))){
            data = timesData.collect(Collectors.toList());
        } catch (IOException e){
            throw new IllegalArgumentException("There is no such file at this path -" + filePath);
        }

        return data;
    }
}
