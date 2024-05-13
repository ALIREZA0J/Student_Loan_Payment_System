package org.loanpayment.validation;

import org.loanpayment.exception.ValidationException;

import java.util.Arrays;

public class NationalCodeValidation {



    public static void validateNationalCode(String nationalCode) {

        String[] identicalDigits = {"0000000000", "1111111111", "2222222222", "3333333333",
                "4444444444", "5555555555", "6666666666", "7777777777", "8888888888", "9999999999"};

        if (nationalCode.trim().isEmpty()) {
            System.out.println("National Code is empty");
            throw new ValidationException("National Code Not valid");
        } else if (nationalCode.length() != 10) {
            System.out.println("National Code must be exactly 10 digits");
            throw new ValidationException("National Code Not valid");
        } else if (Arrays.asList(identicalDigits).contains(nationalCode)) {
            System.out.println("MelliCode is not valid (Fake National Code)");
            throw new ValidationException("National Code Not valid");
        } else {
            int sum = 0;

            for (int i = 0; i < 9; i++) {
                sum += Character.getNumericValue(nationalCode.charAt(i)) * (10 - i);
            }

            int lastDigit;
            int divideRemaining = sum % 11;

            if (divideRemaining < 2) {
                lastDigit = divideRemaining;
            } else {
                lastDigit = 11 - (divideRemaining);
            }

            if (Character.getNumericValue(nationalCode.charAt(9)) == lastDigit) {
                System.out.println("nationalCode is valid");
            } else {
                System.out.println("nationalCode is not valid");
                throw new ValidationException("National Code Not valid");
            }
        }
    }
}
