package christmas.verification;

import christmas.model.Menu;
import christmas.model.Order;

import java.util.List;

public class Verification {
    static final int FIRST_DATE = 1;
    static final int LAST_DATE = 31;
    static final int MAX_QUANTITY = 20;

    public static void verifyDate(int date) {
        if(date < FIRST_DATE || date > LAST_DATE) {
            throw new IllegalArgumentException(ErrorMessage.getDateErrorMessage());
        }
    }

    private void verifyOrdersQuantity(List<Order> orders) {
        int totalQuantity = 0;

        for (Order order : orders) {
            totalQuantity += order.getNumber();
        }

        if (totalQuantity > MAX_QUANTITY) {
            throw new IllegalArgumentException(ErrorMessage.getQuantityErrorMessage());
        }
    }

    private void verifyOrdersCategory(List<Order> orders) {
        
    }

    public static void verifyOrders(List<Order> orders) {

    }


}
