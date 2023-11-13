package christmas.model;

import christmas.model.Order;
import christmas.model.Menu;

import java.util.List;

public class Result {
    private List<Order> orders;
    private int originalPrice;
    private String present;
    private int dDayBenefit;
    private int weekdayBenefit;
    private int specialBenefit;

    private int presentBenefit;
    private int discountPrice;
    private String badge;

    public Result(List<Order> orders) {
        this.orders = orders;
        this.originalPrice = getOriginalPrice(orders);

    }

    private int getOriginalPrice(List<Order> orders) {
        int originalPrice = 0;

        for(Order order : orders) {
            String menuName = order.getName();
            originalPrice += Menu.getPrice(menuName);
        }

        return originalPrice;
    }

}
