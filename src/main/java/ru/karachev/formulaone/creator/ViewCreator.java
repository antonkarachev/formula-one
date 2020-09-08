package ru.karachev.formulaone.creator;

import ru.karachev.formulaone.domain.Racer;

import java.util.Map;

public interface ViewCreator {

    String createView(Map<Integer, Racer> racers);
}
