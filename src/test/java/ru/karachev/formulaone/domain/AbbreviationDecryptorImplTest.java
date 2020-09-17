package ru.karachev.formulaone.domain;

import org.junit.jupiter.api.Test;
import ru.karachev.formulaone.decryptor.AbbreviationDecryptor;
import ru.karachev.formulaone.decryptor.AbbreviationDecryptorImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class AbbreviationDecryptorImplTest {

    private final AbbreviationDecryptor abbreviationDecryptor = new AbbreviationDecryptorImpl();

    @Test
    void decryptAbbreviationShouldReturnMapWhenGetListOfStringsWithDataFromFile() {
        List<String> data = new ArrayList<>();
        data.add("AAK_Anton Karachev_BEST TEAM IN THE WORLD");
        data.add("AKR_dfsfds_TEAMTEAM");

        Map<String, String> expected = new HashMap<>();
        expected.put("AAK", "Anton Karachev_BEST TEAM IN THE WORLD");
        expected.put("AKR", "dfsfds_TEAMTEAM");

        Map<String, String> actual = abbreviationDecryptor.decryptAbbreviation(data);

        assertThat(actual).isEqualTo(expected);

    }

}
