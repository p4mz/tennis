package com.jenbodint.tennis;

import android.text.TextUtils;

import java.util.List;

/**
 * Created by Jenbodin T. on 10/6/2017 AD.
 */

public class MainActivityPresenter implements MainActivityContact.Action {

    private final MainActivityContact.View mView;
    private final TennisGame mTennisGame;

    private final int INDEX_GAME = 0;
    private final int INDEX_SCORE = 1;
    private final String DIVIDER = ":";
    private final String NEWLINE = "\n";

    public MainActivityPresenter(MainActivityContact.View view,
                                 TennisGame tennisGame) {
        mView = view;
        mTennisGame = tennisGame;
    }

    @Override
    public void transform() throws Exception {
        StringBuilder output = new StringBuilder();
        List<String> inputs = mView.getInput();
        boolean isFirstGame = true;
        for (String input : inputs) {
            String[] arrInput = input.split(DIVIDER);

            // to add space between each games
            if (!isFirstGame) {
                output.append(NEWLINE);
                output.append(NEWLINE);
            } else {
                isFirstGame = false;
            }

            output.append(arrInput[INDEX_GAME]);
            output.append(NEWLINE);
            List<String> transformedScore = mTennisGame.transform(arrInput[INDEX_SCORE].trim());
            output.append(TextUtils.join(NEWLINE, transformedScore));
        }

        mView.displayResult(output.toString());

    }
}
