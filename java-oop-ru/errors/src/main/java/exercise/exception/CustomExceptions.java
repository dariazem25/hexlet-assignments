package exercise.exception;

import exercise.NegativeRadiusException;

public class CustomExceptions {
    public static final CustomException INVALID_CARD_NUMBER = new CustomException("BANK-001", "Invalid card number.");
    public static final CustomException MONEY_VALUE_IS_INCORRECT = new CustomException(
        "BANK-002", "Money value is incorrect."
    );
    public static final NegativeRadiusException NEGATIVE_RADIUS_EXCEPTION = new NegativeRadiusException("Не удалось посчитать площадь");
}
