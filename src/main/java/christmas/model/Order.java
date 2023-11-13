package christmas.model;

import christmas.verification.ErrorMessage;
import christmas.verification.Verification;

public class Order {
    private String name;
    private int number;

    public Order(String order) {
        String[] nameAndNumber = order.split("-");

        this.name = nameAndNumber[0];
        this.number = nameAndNumber[1];
    }

    public void verifyName(String name) {
        try {
            Menu.valueOf(name);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ErrorMessage.getOrderErrorMessage());
        }
    }

    public void verifyNumber() {

    }
}
