package christmas.verification;

import christmas.verification.ErrorMessage;
public class Verification {
    static final int FIRST_DATE = 1;
    static final int LAST_DATE = 31;

    public static void verifyDate(int date) {
        if(date < FIRST_DATE || date > LAST_DATE) {
            throw new IllegalArgumentException(ErrorMessage.getDateErrorMessage());
        }
    }
}
