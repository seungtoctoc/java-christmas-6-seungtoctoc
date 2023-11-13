package christmas.controller;

import christmas.verification.ErrorMessage;
import christmas.verification.Verification;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.utility.Utility;
import christmas.model.Order;

import java.util.List;
import java.util.ArrayList;

public class Controller {
    public Controller() {
        InputView.printStart();
        int visitDate = readVisitDate();
        List<Order> orders = readOrder();

        
        System.out.println(visitDate);

        for(Order order : orders) {
            System.out.println(order.getName() + " - " +order.getNumber());
        }


    }

    public int readVisitDate() {
        while(true) {
            try {
                String visitDateInput = InputView.readVisitDate();
                int visitDate = Utility.getNumberFromString(visitDateInput);
                Verification.verifyDate(visitDate);
                return visitDate;
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(ErrorMessage.getDateErrorMessage());
            }
        }
    }

    public List<Order> readOrder() {
        while(true) {
            try {
                String orderInput = InputView.readOrder();
                return makeOrderList(orderInput);
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(ErrorMessage.getOrderErrorMessage());
            }
        }
    }

    public List<Order> makeOrderList(String orderInput) {
        String[] splitOrders = orderInput.split(",");
        List<Order> orders = new ArrayList<>();

        for (String order : splitOrders) {
            Order currentOrder = new Order(order);
            orders.add(currentOrder);
        }

        return orders;
    }
}
