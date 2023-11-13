package christmas.view;

import christmas.model.Order;
import christmas.model.Result;
import christmas.utility.Utility;

import java.util.List;

public class OutputView {
    final static int NO_BENEFIT = 0;

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printResult(Result result) {
        printVisitDate(result);
        printMenu(result);
        printOriginalPrice(result);
        printChampagne(result);
        printBenefits(result);
        printTotalDiscount(result);
        printPaymentPrice(result);
        printBadge(result);
    }

    public static void printVisitDate(Result result) {
        int visitDate = result.getVisitDate();
        System.out.println("\n12월 " + visitDate + "일 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public static void printMenu(Result result) {
        System.out.println("\n<주문 메뉴>");

        List<Order> orders = result.getOrders();
        for (Order order : orders) {
            String name =  order.getName();
            int number = order.getNumber();

            System.out.println(name + " " + number + "개");
        }
    }

    public static void printOriginalPrice(Result result) {
        System.out.println("\n<할인 전 총주문 금액>");

        int originalPrice = result.getOriginalPrice();
        String formattedNumber = Utility.getFormattedNumber(originalPrice);
        System.out.println(formattedNumber);
    }

    public static void printChampagne(Result result) {
        System.out.println("\n<증정 메뉴>");

        boolean isGetChampagne = result.isGetChampagne();
        if(isGetChampagne) {
            System.out.println("샴페인 1개");
            return;
        }
        System.out.println("없음");
    }

    public static void printBenefits(Result result) {
        System.out.println("\n<혜택 내역>");

        int totalDiscount = result.getTotalDiscount();
        if (totalDiscount == 0) {
            System.out.println("없음");
            return;
        }
        printChristmasBenefit(result);
        printDayBenefit(result);
        printSpecialBenefit(result);
        printChampagneBenefit(result);
    }

    public static void printChristmasBenefit(Result result) {
        int christmasBenefit = result.getChristmasBenefit();

        if(christmasBenefit > NO_BENEFIT) {
            String formattedChristmasBenefit = Utility.getFormattedNumber(christmasBenefit);
            System.out.println("크리스마스 디데이 할인: -" + formattedChristmasBenefit);
        }
    }

    public static void printDayBenefit(Result result) {
        int dayBenefit = result.getDayBenefit();
        String formattedDayBenefit = Utility.getFormattedNumber(dayBenefit);

        boolean isWeekEnd = result.isWeekEnd();
        if (isWeekEnd) {
            System.out.println("주말 할인: -" + formattedDayBenefit);
            return;
        }
        System.out.println("평일 할인: -" + formattedDayBenefit);
    }

    public static void printSpecialBenefit(Result result) {
        int specialBenefit = result.getSpecialBenefit();
        if(specialBenefit > NO_BENEFIT) {
            String formattedSpecialBenefit = Utility.getFormattedNumber(specialBenefit);
            System.out.println("특별 할인: -" + formattedSpecialBenefit);
        }
    }

    public static void printChampagneBenefit(Result result) {
        int champagneBenefit = result.getChampagneBenefit();
        if(champagneBenefit > NO_BENEFIT) {
            String formattedChampagneBenefit = Utility.getFormattedNumber(champagneBenefit);
            System.out.println("증정 이벤트: -" + formattedChampagneBenefit);
        }
    }

    public static void printTotalDiscount(Result result) {
        System.out.println("\n<총혜택 금액>");

        int totalDiscount = result.getTotalDiscount();
        String formattedTotalDiscount = Utility.getFormattedNumber(totalDiscount);
        System.out.println("-" + formattedTotalDiscount);
    }

    public static void printPaymentPrice(Result result) {
        System.out.println("\n<할인 후 예상 결제 금액>");

        int paymentPrice = result.getPaymentPrice();
        String formattedPaymentPrice = Utility.getFormattedNumber(paymentPrice);
        System.out.println(formattedPaymentPrice);
    }

    public static void printBadge(Result result) {
        System.out.println("\n<12월 이벤트 배지>");

        String badge = result.getBadge();
        System.out.println(badge);
    }
}
