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
            throw new IllegalArgumentException();
        }
    }

    public static void verifyOrdersQuantity(List<Order> orders) {
        int totalQuantity = 0;

        for (Order order : orders) {
            totalQuantity += order.getNumber();
        }

        if (totalQuantity > MAX_QUANTITY) {
            throw new IllegalArgumentException();
        }
    }

    public static void verifyOrdersCategory(List<Order> orders) {
        for (Order order : orders) {
            String name = order.getName();
            String category = Menu.getCategory(name);

            if (category != "음료") {
                return;
            }
        }
        throw new IllegalArgumentException();
    }

    public static void verifyOrders(List<Order> orders) {
        verifyOrdersQuantity(orders);
        verifyOrdersCategory(orders);
    }
}
