package christmas.utility;

import java.text.DecimalFormat;

public class Utility {
    public static int getNumberFromString(String input) {
        try {
            return Integer.parseInt(input);
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    public static String getFormattedNumber(int number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedNumber = decimalFormat.format(number);
        return formattedNumber;
    }
}
