package christmas.controller;

import christmas.verification.Verification;
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
                int visitDate = Utility.getNumberFromString(visitDateInput);
                Verification.verifyDate(visitDate);
                return visitDate;
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    public String readOrder() {
        while(true) {
            try {
                String orderInput = InputView.readOrder();

            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
