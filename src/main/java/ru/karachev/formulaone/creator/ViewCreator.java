package ru.karachev.formulaone.creator;

import ru.karachev.formulaone.domain.Racer;

import java.util.List;

public interface ViewCreator {

    String createView(List<Racer> racerSortedByPlace, int numberOfPrizes);
}
