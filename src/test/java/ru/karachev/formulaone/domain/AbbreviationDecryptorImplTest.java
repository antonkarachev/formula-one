package ru.karachev.formulaone.domain;

import org.junit.jupiter.api.Test;
import ru.karachev.formulaone.decryptor.AbbreviationDecryptor;
import ru.karachev.formulaone.decryptor.AbbreviationDecryptorImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class AbbreviationDecryptorImplTest {

    private final AbbreviationDecryptor abbreviationDecryptor = new AbbreviationDecryptorImpl();

    @Test
    void decryptAbbreviationShouldReturnMapWhenGetStream() {
        Stream<String> stream = Stream.of("AAK_Anton Karachev_BEST TEAM IN THE WORLD",
                "AKR_dfsfds_TEAMTEAM");

        Map<String, String> expected = new HashMap<>();
        expected.put("AAK", "Anton Karachev_BEST TEAM IN THE WORLD");
        expected.put("AKR", "dfsfds_TEAMTEAM");

        Map<String, String> actual = abbreviationDecryptor.decryptAbbreviation(stream);

        assertThat (actual, is(expected));
        assertThat(actual.size(), is(2));

    }

}