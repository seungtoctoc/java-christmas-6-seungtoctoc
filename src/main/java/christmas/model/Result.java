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
    static final int CHAMPAGNE_PRICE = 25_000;
    static final int NO_BENEFIT = 0;

    private List<Order> orders;
    private int originalPrice;
    private boolean getChampagne;
    private int champagneBenefit;
    private int christmasBenefit;
    boolean isWeekEnd;
    private int dayBenefit;
    private int specialBenefit;

    private int discountPrice;
    private int paymentPrice;
    private String badge;

    public Result(int visitDate, List<Order> orders) {
        this.orders = orders;
        this.originalPrice = calculateOriginalPrice(this.orders);

        this.getChampagne = canGetChampagne(this.originalPrice);
        this.champagneBenefit = calculateChampagneBenefit(this.getChampagne);

        this.christmasBenefit = calculateChristmasBenefit(visitDate);

        this.isWeekEnd = isWeekEnd(visitDate);
        this.dayBenefit = calculateDayBenefit(this.isWeekEnd, this.orders);

        this.specialBenefit = calculateSpecialBenefit(visitDate);

        this.discountPrice = this.champagneBenefit + this.christmasBenefit + this.dayBenefit + this.specialBenefit;
        this.paymentPrice = this.originalPrice - this.discountPrice + this.champagneBenefit;
    }

    private int calculateOriginalPrice(List<Order> orders) {
        int originalPrice = 0;

        for (Order order : orders) {
            String menuName = order.getName();
            originalPrice += Menu.getPrice(menuName);
        }

        return originalPrice;
    }

    private boolean canGetChampagne(int originalPrice) {
        if (originalPrice >= GET_PRESENT_PRICE) {
            return true;
        }
        return false;
    }

    private int calculateChampagneBenefit(boolean getChampagne) {
        if (getChampagne) {
            return CHAMPAGNE_PRICE;
        }
        return NO_BENEFIT;
    }

    private int calculateChristmasBenefit(int visitDate) {
        if (visitDate > CHRISTMAS_DAY) {
            return NO_BENEFIT;
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

    private int calculateDayBenefit(boolean isWeekEnd, List<Order> orders) {
        int count;

        if (isWeekEnd == true) {
            count = countCategory(orders, "메인");
            return THIS_YEAR * count;
        }

        count = countCategory(orders, "디저트");
        return THIS_YEAR * count;
    }

    private int calculateSpecialBenefit(int visitDate) {
        for (int day : specialDiscountDays) {
            if (day == visitDate) {
                return SPECIAL_DISCOUNT_PRICE;
            }
        }
        return NO_BENEFIT;
    }
}
