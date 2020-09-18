package ru.karachev.formulaone.validator;

import java.io.File;

public class ValidatorImpl implements Validator {

    @Override
    public void validate(String filePath) {
        File file = new File(filePath);

        if (filePath.isEmpty()) {
            throw new IllegalArgumentException("Path to file is empty");
        }
        if (!file.exists()){
            throw new IllegalArgumentException("There is no file in this path " +
                    "or it`s incorrect path");
        }
    }

    @Override
    public void validate(int numberOfPrizes) {
        if (numberOfPrizes < 1) {
            throw new IllegalArgumentException("The number of prizes is less than one");
        }
    }

}
