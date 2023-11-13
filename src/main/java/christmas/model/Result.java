package christmas.model;

import christmas.model.Order;
import christmas.model.Menu;

import java.util.List;

public class Result {
    static final int GET_PRESENT_PRICE = 120_000;
    static final int FIRST_CHRISTMAS_DISCOUNT_PRICE = 1_000;
    static final int DAILY_INCREASE_DISCOUNT_PRICE = 1_00;

    private List<Order> orders;
    private int originalPrice;
    private boolean getPresent;
    private int dDayBenefit;
    private int weekdayBenefit;
    private int specialBenefit;

    private int presentBenefit;
    private int discountPrice;
    private String badge;

    public Result(int visitDate, List<Order> orders) {
        this.orders = orders;
        this.originalPrice = getOriginalPrice(orders);
        this.getPresent = canGetPresent(this.originalPrice);
        this.dDayBenefit = getdDayBenefit(visitDate)
    }

    private int getOriginalPrice(List<Order> orders) {
        int originalPrice = 0;

        for(Order order : orders) {
            String menuName = order.getName();
            originalPrice += Menu.getPrice(menuName);
        }

        return originalPrice;
    }

    private boolean canGetPresent (int originalPrice) {
        if (originalPrice >= GET_PRESENT_PRICE) {
            return true;
        }
        return false;
    }

    private int getdDayBenefit (int visitDate) {
        int discountPrice = FIRST_CHRISTMAS_DISCOUNT_PRICE + DAILY_INCREASE_DISCOUNT_PRICE * (visitDate - 1);
        return discountPrice;
    }
}
