package christmas.model;

import christmas.utility.Utility;
import christmas.verification.ErrorMessage;

public class Order {
    private String name;
    private int number;

    public Order(String order) {
        String[] nameAndNumber = order.split("-");

        verifyLength(nameAndNumber);
        verifyName(nameAndNumber[0]);

        this.name = nameAndNumber[0];
        this.number = Utility.getNumberFromString(nameAndNumber[1]);
    }

    private void verifyName(String name) {
        if (Menu.isInMenu(name) == false) {
            throw new IllegalArgumentException(ErrorMessage.getOrderErrorMessage());
        }
    }

    private void verifyLength(String[] nameAndNumber) {
        if (nameAndNumber.length != 2) {
            throw new IllegalArgumentException(ErrorMessage.getOrderErrorMessage());
        }
    }

    public String getName() {
        return this.name;
    }

    public int getNumber() {
        return this.number;
    }
}
