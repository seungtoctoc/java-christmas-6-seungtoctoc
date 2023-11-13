package christmas.model;

import java.util.List;

public class Result {
    public enum Price {
        APPLY_EVENT(10_000),
        NO_BENEFIT(0),
        GET_PRESENT(120_000),
        CHAMPAGNE(25_000),
        FIRST_CHRISTMAS_DISCOUNT(1_000),
        DAILY_INCREASE_DISCOUNT(100),
        SPECIAL_DISCOUNT(1_000),
        SANTA_BADGE(20_000),
        TREE_BADGE(10_000),
        STAR_BADGE(5_000);

        private int price;

        Price(int price) {
            this.price = price;
        }
        public int getPrice() {
            return price;
        }
    }
    static final int THIS_YEAR = 2023;
    static final int CHRISTMAS_DAY = 25;
    static final int DAYS_OF_WEEK = 7;
    int[] specialDiscountDays = {3, 10, 17, 24, 25, 31};

    private int visitDate;
    private List<Order> orders;
    private int originalPrice;
    private boolean getChampagne = false;
    private int champagneBenefit = Price.NO_BENEFIT.getPrice();
    private int christmasBenefit = Price.NO_BENEFIT.getPrice();
    boolean isWeekEnd;
    private int dayBenefit = Price.NO_BENEFIT.getPrice();
    private int specialBenefit = Price.NO_BENEFIT.getPrice();
    private int totalDiscount = Price.NO_BENEFIT.getPrice();
    private int paymentPrice;
    private String badge;

    public Result(int visitDate, List<Order> orders) {
        this.visitDate = visitDate;
        this.orders = orders;
        this.originalPrice = calculateOriginalPrice(this.orders);

        if(this.originalPrice >= Price.APPLY_EVENT.getPrice()) {
            this.getChampagne = canGetChampagne(this.originalPrice);
            this.champagneBenefit = calculateChampagneBenefit(this.getChampagne);

            this.christmasBenefit = calculateChristmasBenefit(visitDate);

            this.isWeekEnd = isWeekEnd(visitDate);
            this.dayBenefit = calculateDayBenefit(this.isWeekEnd, this.orders);

            this.specialBenefit = calculateSpecialBenefit(visitDate);

            this.totalDiscount = this.champagneBenefit + this.christmasBenefit + this.dayBenefit + this.specialBenefit;
        }

        this.paymentPrice = this.originalPrice - this.totalDiscount + this.champagneBenefit;
        this.badge = calculateBadge(this.totalDiscount);
    }

    private int calculateOriginalPrice(List<Order> orders) {
        int originalPrice = 0;

        for (Order order : orders) {
            String menuName = order.getName();
            int number = order.getNumber();
            int price = Menu.getPrice(menuName);

            originalPrice += price * number;
        }

        return originalPrice;
    }

    private boolean canGetChampagne(int originalPrice) {
        if (originalPrice >= Price.GET_PRESENT.getPrice()) {
            return true;
        }
        return false;
    }

    private int calculateChampagneBenefit(boolean getChampagne) {
        if (getChampagne) {
            return Price.CHAMPAGNE.getPrice();
        }
        return Price.NO_BENEFIT.getPrice();
    }

    private int calculateChristmasBenefit(int visitDate) {
        if (visitDate > CHRISTMAS_DAY) {
            return Price.NO_BENEFIT.getPrice();
        }
        int discountPrice = Price.FIRST_CHRISTMAS_DISCOUNT.getPrice() + Price.DAILY_INCREASE_DISCOUNT.getPrice() * (visitDate - 1);
        return discountPrice;
    }

    private int countCategory(List<Order> orders, String category) {
        int count = 0;

        for (Order order : orders) {
            String currentName = order.getName();
            int currentNumber = order.getNumber();

            String currentCategory = Menu.getCategory(currentName);
            if (currentCategory == category) {
                count += currentNumber;
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
                return Price.SPECIAL_DISCOUNT.getPrice();
            }
        }
        return Price.NO_BENEFIT.getPrice();
    }

    private String calculateBadge(int discountPrice) {
        if (discountPrice >= Price.SANTA_BADGE.getPrice()) {
            return "산타";
        }
        if (discountPrice >= Price.TREE_BADGE.getPrice()) {
            return "트리";
        }
        if (discountPrice >= Price.STAR_BADGE.getPrice()) {
            return "별";
        }
        return "없음";
    }

    public int getVisitDate() {
        return visitDate;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public int getOriginalPrice() {
        return originalPrice;
    }

    public boolean isGetChampagne() {
        return getChampagne;
    }

    public int getChampagneBenefit() {
        return champagneBenefit;
    }

    public int getChristmasBenefit() {
        return christmasBenefit;
    }

    public int getDayBenefit() {
        return dayBenefit;
    }

    public int getSpecialBenefit() {
        return specialBenefit;
    }

    public int getTotalDiscount() {
        return totalDiscount;
    }

    public int getPaymentPrice() {
        return paymentPrice;
    }

    public String getBadge() {
        return badge;
    }

    public boolean isWeekEnd() {
        return isWeekEnd;
    }
}
