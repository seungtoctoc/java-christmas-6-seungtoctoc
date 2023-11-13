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


    }

}
