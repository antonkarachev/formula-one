package ru.karachev.formulaone.creator;

import ru.karachev.formulaone.domain.Racer;

import java.time.Duration;
import java.util.Map;

public class ViewCreatorImpl implements ViewCreator {

    private static final String HORIZONTAL_LINE = "-";
    private static final String NEW_LINE = "\n";
    private static final String PLACE_FORMAT = "%02d. ";
    private static final String NAME_FORMAT = "%-18s|";
    private static final String TEAM_FORMAT = "%-27s|";
    private static final String TIME_FORMAT = "%02d:%02d.%03d";

    @Override
    public String createView(Map<Integer, Racer> racersToPlace) {

        StringBuilder view = new StringBuilder();

        view.append(NEW_LINE)
                .append(firstStepViewMaker(racersToPlace));

        int lineLength = view.toString().split(NEW_LINE)[1].length();

        view.append(lineCreator(lineLength))
                .append(secondStepViewMaker(racersToPlace));

        return view.toString();
    }

    private String firstStepViewMaker(Map<Integer, Racer> racersToPlace) {
        StringBuilder firstStepView = new StringBuilder();


        racersToPlace.forEach((place, racer) -> {
                    if (place <= 15) {
                        firstStepView.append(String.format(PLACE_FORMAT, place))
                                .append(String.format(NAME_FORMAT, racer.getName()))
                                .append(String.format(TEAM_FORMAT, racer.getTeamName()))
                                .append(getTimeFromDuration(racer.getBestLapTime()))
                                .append(NEW_LINE);
                    }
                }
        );
        return firstStepView.toString();
    }


    private String secondStepViewMaker(Map<Integer, Racer> racersToPlace) {
        StringBuilder secondStepView = new StringBuilder();

        racersToPlace.forEach((place, racer) -> {
                    if (place > 15) {
                        secondStepView.append(String.format(PLACE_FORMAT, place))
                                .append(String.format(NAME_FORMAT, racer.getName()))
                                .append(String.format(TEAM_FORMAT, racer.getTeamName()))
                                .append(getTimeFromDuration(racer.getBestLapTime()))
                                .append(NEW_LINE);
                    }
                }
        );

        return secondStepView.toString();
    }

    private String getTimeFromDuration(Duration bestLapTime) {
        long bestLapTimeInMillis = bestLapTime.toMillis();
        long minutes = bestLapTimeInMillis / 60000;
        long seconds = (bestLapTimeInMillis % 60000) / 1000;
        long millis = (bestLapTimeInMillis % 6000) % 1000;

        return String.format(TIME_FORMAT, minutes, seconds, millis);
    }

    private String lineCreator(int quantity) {
        StringBuilder insertion = new StringBuilder();

        for (int i = 0; i < quantity; i++) {
            insertion.append(HORIZONTAL_LINE);
        }
        insertion.append(NEW_LINE);

        return insertion.toString();
    }

}
