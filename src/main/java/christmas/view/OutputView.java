package christmas.view;

import christmas.model.Result;
import christmas.model.Order;
import christmas.utility.Utility;

import java.util.List;

public class OutputView {
    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printResult(Result result) {
        int visitDate = result.getVisitDate();
        System.out.println("12월 " + visitDate + "일 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");

        System.out.println("<주문 메뉴>");
        List<Order> orders = result.getOrders();
        for (Order order : orders) {
            String name =  order.getName();
            int number = order.getNumber();

            System.out.println(name + " " + number + "개");
        }

        System.out.println("<할인 전 총 주문 금액>");
        int originalPrice = result.getOriginalPrice();
        String formattedNumber = Utility.getFormattedNumber(originalPrice);
        System.out.println(formattedNumber + "원");
    }
}
