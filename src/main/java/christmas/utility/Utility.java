package christmas.utility;

public class Utility {
    public int getNumberFromString(String input) {
        try {
            return Integer.parseInt(input);
        }
        catch (IllegalArgumentException e) {
            System.out.println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            throw new IllegalArgumentException();
        }
    }
}
