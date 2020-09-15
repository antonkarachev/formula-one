package ru.karachev.formulaone.creator;

import ru.karachev.formulaone.domain.Racer;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ViewCreatorImpl implements ViewCreator {

    private static final String HORIZONTAL_LINE = "-";
    private static final String NEW_LINE = "\n";
    private static final String PLACE_FORMAT = "%02d. ";
    private static final String NAME_FORMAT = "%-18s|";
    private static final String TEAM_FORMAT = "%-27s|";
    private static final String TIME_FORMAT = "%02d:%02d.%03d";
    private static final int FIRST_PRIZE_PLACE = 1;
    private static final int LINE_TO_SET_LENGTH = 1;

    @Override
    public String createView(List<Racer> racerSortedByPlace, int numberOfPrizes) {
        StringBuilder view = new StringBuilder();
        AtomicInteger prizePlaces = new AtomicInteger(FIRST_PRIZE_PLACE);

        racerSortedByPlace.forEach(racer -> {
            if (prizePlaces.get() == numberOfPrizes + 1) {
                int lineLength = view.toString().split(NEW_LINE)[LINE_TO_SET_LENGTH].length();
                view.append(lineCreator(lineLength));
            }
            view.append(String.format(PLACE_FORMAT, prizePlaces.getAndIncrement()))
                    .append(String.format(NAME_FORMAT, racer.getName()))
                    .append(String.format(TEAM_FORMAT, racer.getTeamName()))
                    .append(getTimeFromDuration(racer.getBestLapTime()))
                    .append(NEW_LINE);
        });

        return view.toString();
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
