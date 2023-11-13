package christmas.model;

import christmas.model.Order;
import christmas.model.Menu;

import java.util.List;

public class Result {
    static final int GET_PRESENT_PRICE = 120_000;
    static final int FIRST_CHRISTMAS_DISCOUNT_PRICE = 1_000;
    static final int DAILY_INCREASE_DISCOUNT_PRICE = 1_00;
    static final int DAYS_OF_WEEK = 7;
    static final int THIS_YEAR = 2023;
    static final int CHRISTMAS_DAY = 25;
    int[] specialDiscountDays = {3, 10, 17, 24, 25, 31};
    static final int SPECIAL_DISCOUNT_PRICE = 1000;

    private List<Order> orders;
    private int originalPrice;
    private boolean getPresent;
    private int christmasBenefit;
    boolean isWeekEnd;
    private int dayBenefit;
    private int specialBenefit;
    private int presentBenefit;
    private int discountPrice;
    private String badge;

    public Result(int visitDate, List<Order> orders) {
        this.orders = orders;
        this.originalPrice = getOriginalPrice(orders);
        this.getPresent = canGetPresent(this.originalPrice);
        this.christmasBenefit = getChristmasBenefit(visitDate);
        this.isWeekEnd = isWeekEnd(visitDate);
        this.dayBenefit = getDayBenefit(this.isWeekEnd, orders);
        this.specialBenefit = getSpecialBenefit(visitDate);
    }

    private int getOriginalPrice(List<Order> orders) {
        int originalPrice = 0;

        for (Order order : orders) {
            String menuName = order.getName();
            originalPrice += Menu.getPrice(menuName);
        }

        return originalPrice;
    }

    private boolean canGetPresent(int originalPrice) {
        if (originalPrice >= GET_PRESENT_PRICE) {
            return true;
        }
        return false;
    }

    private int getChristmasBenefit(int visitDate) {
        if (visitDate > CHRISTMAS_DAY) {
            return 0;
        }
        int discountPrice = FIRST_CHRISTMAS_DISCOUNT_PRICE + DAILY_INCREASE_DISCOUNT_PRICE * (visitDate - 1);
        return discountPrice;
    }

    private int countCategory(List<Order> orders, String category) {
        int count = 0;

        for (Order order : orders) {
            String currentName = order.getName();
            String currentCategory = Menu.getCategory(currentName);

            if (currentCategory == category) {
                count++;
            }
        }

        return count;
    }

    private boolean isWeekEnd(int visitDate) {
        int rest = visitDate % DAYS_OF_WEEK;

        if (rest == 1  || rest == 2) {
            return true;
        }
        return false;
    }

    private int getDayBenefit(boolean isWeekEnd, List<Order> orders) {
        int count;

        if (isWeekEnd == true) {
            count = countCategory(orders, "메인");
            return THIS_YEAR * count;
        }

        count = countCategory(orders, "디저트");
        return THIS_YEAR * count;
    }

    private int getSpecialBenefit(int visitDate) {
        for (int day : specialDiscountDays) {
            if (day == visitDate){
                return SPECIAL_DISCOUNT_PRICE;
            }
        }

        return 0;
    }
}
