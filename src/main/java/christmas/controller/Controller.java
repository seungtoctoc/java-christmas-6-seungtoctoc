package christmas.controller;

import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.utility.Utility;

public class Controller {
    public Controller() {
        InputView.printStart();
        readVisitDate();
    }

    public int readVisitDate() {
        while(true) {
            try {
                String visitDateInput = InputView.readVisitDate();
                return Utility.getNumberFromString(visitDateInput);
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
