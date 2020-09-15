package ru.karachev.formulaone.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FileReaderTest {

    private final FileReader fileReader = new FileReader();

    @Test
    void fileReaderShouldReturnListOfStringsWhenGetFilePath(){
        String filePath = "./src/test/resources/for_testing_fileReader.txt";

        List<String> expected = new ArrayList<>();
        expected.add ("Hello");
        expected.add ("my");
        expected.add ("name");
        expected.add ("Anton");

        List<String> actual = fileReader.readFile(filePath);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void fileReaderShouldThrowExceptionWhenGetWrongFilePath(){
        String filePath = "no file path";
        assertThatThrownBy(() -> fileReader.readFile(filePath))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("There is no such file at this path -" + filePath);
    }

}
