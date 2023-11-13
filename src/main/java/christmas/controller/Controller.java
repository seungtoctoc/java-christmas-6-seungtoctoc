package christmas.controller;

import christmas.model.Order;
import christmas.model.Result;
import christmas.utility.Utility;
import christmas.verification.Verification;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    public Controller() {
        InputView.printStart();

        int visitDate = readVisitDate();
        List<Order> orders = readOrder();

        Result result = new Result(visitDate, orders);
        OutputView.printResult(result);
    }

    private int readVisitDate() {
        while (true) {
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

    private List<Order> readOrder() {
        while (true) {
            try {
                String orderInput = InputView.readOrder();
                List<Order> orders = makeOrderList(orderInput);

                Verification.verifyOrders(orders);
                return orders;
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private List<Order> makeOrderList(String orderInput) {
        List<Order> orders = new ArrayList<>();

        String[] nameAndNumbers = orderInput.split(",");
        for (String nameAndNumber : nameAndNumbers) {
            Order order = new Order(nameAndNumber);
            orders.add(order);
        }
        return orders;
    }
}