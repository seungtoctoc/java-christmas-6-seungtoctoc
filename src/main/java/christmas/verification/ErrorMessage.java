package christmas.verification;

public class ErrorMessage {
    public static String getNumberErrorMessage() {
        return "[ERROR] 숫자가 입력되지 않았습니다. 다시 입력해 주세요.";
    }
    public static String getDateErrorMessage() {
        return "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    }

    public static String getOrderErrorMessage() {
        return "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    }

    public static String getQuantityErrorMessage() {
        return "[ERROR] 메뉴는 한 번에 최대 20개 까지만 주문할 수 있습니다. 다시 입력해 주세요.";
    }

    public static String getCategoryErrorMessage() {
        return "[ERROR] 음료만 주문 시, 주문할 수 없습니다. 다시 입력해 주세요.";
    }
}
