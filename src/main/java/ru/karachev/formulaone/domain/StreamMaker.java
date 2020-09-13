package ru.karachev.formulaone.domain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class StreamMaker{

    public Stream<String> makeStreamFromFile(String filePath) {
        Stream<String> dataFromFile = null;
        try{
            dataFromFile = Files.lines(Paths.get(filePath));
        } catch (IOException e){
            System.out.println("There is no such file at this path -" + filePath);;
        }
        return dataFromFile;
    }
}
