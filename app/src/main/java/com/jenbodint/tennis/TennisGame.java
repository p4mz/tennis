package com.jenbodint.tennis;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jenbodin T. on 10/5/2017 AD.
 */

class TennisGame {

    private static final String PLAYER_A = "A";
    private static final String PLAYER_B = "B";
    private static final String SEPARATOR = " ";
    private static final String DUCE = "DUCE";

    List<String[]> history = new ArrayList<>();
    private int countAWin = 0;
    private int countBWin = 0;
    private boolean isDeuce;

    /**
     * reset all parameters for calculating
     */
    private void initParameters() {
        this.history = new ArrayList<>();
        this.countAWin = 0;
        this.countBWin = 0;
        this.isDeuce = false;
    }

    /**
     * transform scores to be real score
     * @param score
     * @return list of transformed score. For example 15, 30, 40 and WIN
     */
    public List<String> transform(@NonNull String score) {

        String[] arrScore = score.split(SEPARATOR);
        initParameters();
        for (String item : arrScore) {
            switch (item) {
                case PLAYER_A:
                    addScoreToAPlayer();
                    break;
                case PLAYER_B:
                    addScoreToBPlayer();
                    break;

                default:
                    break;
            }
        }

        List<String> scores = new ArrayList<>();
        for (String[] record : history) {
            if (record[0].equals(DUCE)) {
                scores.add(record[0]);
            } else {
                scores.add(String.format("%s: %s", record[0], record[1]));
            }
        }

        return scores;
    }

    public void addScoreToAPlayer() {
        String point = "0";
        if (countAWin == 0) point = "15";
        else if (countAWin == 1) point = "30";
        else if (countAWin == 2) {
            point = "40";
            if (isBPlayerReachTo40()) {
                isDeuce = true;
            }
        } else {
            if (isDeuce) {
                if (isLastWinIsYourAfterDeuce(PLAYER_A)) {
                    point = "WIN";
                } else {
                    point = "ADV";
                }
            } else {
                point = "WIN";
            }
        }

        history.add(new String[]{PLAYER_A, point});
        countAWin++;
        if (isDeuce) {
            if (point.equals("40")) {
                history.add(new String[]{DUCE, ""});
            } else if (point.equals("ADV") && !isLastWinIsDeuce()) {

                history.add(new String[]{DUCE, ""});
            }
        }
    }

    public void addScoreToBPlayer() {
        String point = "0";
        if (countBWin == 0) point = "15";
        else if (countBWin == 1) point = "30";
        else if (countBWin == 2) {
            point = "40";
            if (isAPlayerReachTo40()) {
                isDeuce = true;
            }
        } else {
            if (isDeuce) {
                if (isLastWinIsYourAfterDeuce(PLAYER_B)) {
                    point = "WIN";
                } else {
                    point = "ADV";
                }
            } else {
                point = "WIN";
            }
        }
        history.add(new String[]{PLAYER_B, point});
        countBWin++;
        if (isDeuce) {
            if (point.equals("40")) {
                history.add(new String[]{DUCE, ""});
            } else if (point.equals("ADV") && !isLastWinIsDeuce()) {

                history.add(new String[]{DUCE, ""});
            }
        }
    }

    public boolean isLastWinIsYourAfterDeuce(String player) {
        if (isDeuce) {
            String lastWin = history.get(history.size() - 1)[0];
            return lastWin.equals(player) &&
                    !lastWin.equals(DUCE);
        }
        return false;
    }

    public boolean isLastWinIsDeuce() {
        if (isDeuce) {
            String lastWin = history.get(history.size() - 2)[0];
            return lastWin.equals(DUCE);
        }
        return false;
    }

    /**
     * determine with count win on B player more than 2 times. 1 is 15 points, 2 is 30 points and 3 is 40 points.
     *
     * @return reach or not yet
     */
    public boolean isBPlayerReachTo40() {
        return countBWin > 2;
    }

    /**
     * determine with count win on B player more than 2 times. 1 is 15 points, 2 is 30 points and 3 is 40 points.
     *
     * @return reach or not yet
     */
    public boolean isAPlayerReachTo40() {
        return countAWin > 2;
    }

}
