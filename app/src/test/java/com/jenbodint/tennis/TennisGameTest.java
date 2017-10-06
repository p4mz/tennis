package com.jenbodint.tennis;

import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Jenbodin T. on 10/5/2017 AD.
 */

public class TennisGameTest {

    /**
     * Basic winning for A Player only
     */
    @Test
    public void whenAPlayerWinUntilGameEnd() {

        // arrange
        String score = "A A A A";
        String[] expectedResult = new String[]{
                "A: 15",
                "A: 30",
                "A: 40",
                "A: WIN"
        };

        // act
        TennisGame tennisGame = new TennisGame();
        List<String> result = tennisGame.transform(score);

        // assert
        assertEquals(expectedResult.length, result.size());
        int resultLength = expectedResult.length;
        for (int i = 0; i < resultLength; i++) {
            assertEquals(expectedResult[i], result.get(i));
        }

    }

    /**
     * Basic winning for B Player only
     */
    @Test
    public void whenBPlayerWinUntilGameEnd() {

        // arrange
        String score = "B B B B";
        String[] expectedResult = new String[]{
                "B: 15",
                "B: 30",
                "B: 40",
                "B: WIN"
        };

        // act
        TennisGame tennisGame = new TennisGame();
        List<String> result = tennisGame.transform(score);

        // assert
        assertEquals(expectedResult.length, result.size());
        int resultLength = expectedResult.length;
        for (int i = 0; i < resultLength; i++) {
            assertEquals(expectedResult[i], result.get(i));
        }

    }

    /**
     * A stand for A win
     * B stand for B win
     */
    @Test
    public void whenABABAAThenAShouldDisplay_A15_B15_A30_B30_A40_AWIN() {

        // arrange
        String score = "A B A B A A";
        String[] expectedResult = new String[]{
                "A: 15",
                "B: 15",
                "A: 30",
                "B: 30",
                "A: 40",
                "A: WIN"
        };

        // act
        TennisGame tennisGame = new TennisGame();
        List<String> result = tennisGame.transform(score);

        // assert
        assertEquals(expectedResult.length, result.size());
        int resultLength = expectedResult.length;
        for (int i = 0; i < resultLength; i++) {
            assertEquals(expectedResult[i], result.get(i));
        }

    }

    /**
     * A stand for A win
     * B stand for B win
     * D stand for DEUCE
     */
    @Test
    public void whenBBAAABABBBThenShouldDisplay_B15_B30_A15_A30_A40_B40_D_AADV_BADV_D_BADV_BWIN() {

        // arrange
        String score = "B B A A A B A B B B";
        String[] expectedResult = new String[]{
                "B: 15",
                "B: 30",
                "A: 15",
                "A: 30",
                "A: 40",
                "B: 40",
                "DUCE",
                "A: ADV",
                "B: ADV",
                "DUCE",
                "B: ADV",
                "B: WIN",
        };

        // act
        TennisGame tennisGame = new TennisGame();
        List<String> result = tennisGame.transform(score);

        // assert
        assertEquals(expectedResult.length, result.size());
        int resultLength = expectedResult.length;
        for (int i = 0; i < resultLength; i++) {
            assertEquals(expectedResult[i], result.get(i));
        }

    }

    /**
     * A stand for A win
     * B stand for B win
     * D stand for DEUCE
     */
    @Test
    public void whenBBAAABAAThenShouldDisplay_B15_B30_A15_A30_A40_B40_D_AADV_AWIN() {

        // arrange
        String score = "B B A A A B A A";
        String[] expectedResult = new String[]{
                "B: 15",
                "B: 30",
                "A: 15",
                "A: 30",
                "A: 40",
                "B: 40",
                "DUCE",
                "A: ADV",
                "A: WIN",
        };

        // act
        TennisGame tennisGame = new TennisGame();
        List<String> result = tennisGame.transform(score);

        // assert
        assertEquals(expectedResult.length, result.size());
        int resultLength = expectedResult.length;
        for (int i = 0; i < resultLength; i++) {
            assertEquals(expectedResult[i], result.get(i));
        }

    }

    /**
     * A stand for A win
     * B stand for B win
     * D stand for DEUCE
     */
    @Test
    public void whenBBAAABABABABABABABABBBThenShouldDisplay_B15_B30_A15_A30_A40_B40_D_AADV_BADV_D_AADV_BADV_D_AADV_BADV_D_BADV_AADV_D_AADV_BADV_D_AADV_BADV_D_AADV_BADV_D_BADV_BWIN() {

        // arrange
        String score = "B B A A A B A B A B A B B A A B A B A B B B";
        String[] expectedResult = new String[]{
                "B: 15",
                "B: 30",
                "A: 15",
                "A: 30",
                "A: 40",
                "B: 40",
                "DUCE",
                "A: ADV",
                "B: ADV",
                "DUCE",
                "A: ADV",
                "B: ADV",
                "DUCE",
                "A: ADV",
                "B: ADV",
                "DUCE",
                "B: ADV",
                "A: ADV",
                "DUCE",
                "A: ADV",
                "B: ADV",
                "DUCE",
                "A: ADV",
                "B: ADV",
                "DUCE",
                "A: ADV",
                "B: ADV",
                "DUCE",
                "B: ADV",
                "B: WIN",
        };

        // act
        TennisGame tennisGame = new TennisGame();
        List<String> result = tennisGame.transform(score);

        // assert
        assertEquals(expectedResult.length, result.size());
        int resultLength = expectedResult.length;
        for (int i = 0; i < resultLength; i++) {
            assertEquals(expectedResult[i], result.get(i));
        }

    }

}
