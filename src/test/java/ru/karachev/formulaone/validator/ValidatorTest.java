package ru.karachev.formulaone.validator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ValidatorTest {

    private final Validator validator = new ValidatorImpl();

    @Test
    void validatorShouldThrowExceptionWhenPathToFileIsEmpty() {
        String path = "";
        assertThatThrownBy(() -> validator.validate(path))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Path to file is empty");
    }

    @Test
    void validatorShouldThrowExceptionWhenPathToFileIsWrong() {
        String path = "hello111";
        assertThatThrownBy(() -> validator.validate(path))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("There is no file in this path " +
                        "or it`s incorrect path");
    }

    @Test
    void validatorShouldNotThrowExceptionWhenPathIsCorrect(){
        String path= "./src/main/resources/start.log";
        assertDoesNotThrow(() -> validator.validate(path));
    }

    @Test
    void validatorShouldThrowExceptionWhenNumberOfPrizesIsLessThan1(){
        int numberOfPrizes = -5;
        assertThatThrownBy(() -> validator.validate(numberOfPrizes))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("The number of prizes is less than one");
    }

    @Test
    void validatorShouldNotThrowExceptionWhenNumberOfPrizesIsBiggerThan1(){
        int numberOfPrizes = 2;
        assertDoesNotThrow(() -> validator.validate(numberOfPrizes));
    }


}
