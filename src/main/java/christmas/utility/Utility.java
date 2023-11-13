package christmas.utility;

import christmas.verification.ErrorMessage;

public class Utility {
    public static int getNumberFromString(String input) {
        try {
            return Integer.parseInt(input);
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }
}
