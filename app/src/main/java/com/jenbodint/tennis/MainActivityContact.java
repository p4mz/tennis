package com.jenbodint.tennis;

import java.util.List;

/**
 * Created by Jenbodin T. on 10/6/2017 AD.
 */

public class MainActivityContact {
    interface View {
        void displayInput(String input);

        void displayResult(String result);

        List<String> getInput();
    }

    interface Action {
        void transform() throws Exception;
    }
}
