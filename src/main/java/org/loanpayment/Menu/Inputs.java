package org.loanpayment.Menu;

import com.github.mfathi91.time.PersianDate;
import org.loanpayment.connection.AppContext;
import org.loanpayment.model.RentHouse;
import org.loanpayment.model.Semester;
import org.loanpayment.model.Student;
import org.loanpayment.service.bankcard.BankCardService;
import org.loanpayment.service.installment.InstallmentService;
import org.loanpayment.service.loan.LoanService;
import org.loanpayment.service.rent_house.RentHouseService;
import org.loanpayment.service.semester.SemesterService;
import org.loanpayment.service.spouse.SpouseService;
import org.loanpayment.service.student.StudentService;
import org.loanpayment.service.university.UniversityService;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static org.passay.AllowedRegexRule.ERROR_CODE;

public class Inputs {
    private static final Scanner SCANNER = new Scanner(System.in);
    protected static final SemesterService SEMESTER_SERVICE = AppContext.getSemesterService();
    protected static final BankCardService BANK_CARD_SERVICE = AppContext.getBankCardService();
    public static final InstallmentService INSTALLMENT_SERVICE = AppContext.getInstallmentService();
    protected static final LoanService LOAN_SERVICE = AppContext.getLoanService();
    protected static final RentHouseService RENT_HOUSE_SERVICE = AppContext.getRentHouseService();
    protected static final SpouseService SPOUSE_SERVICE = AppContext.getSpouseService();
    protected static final StudentService STUDENT_SERVICE = AppContext.getStudentService();
    protected static final UniversityService UNIVERSITY_SERVICE = AppContext.getUniversityService();



    protected static Semester CURRENT_SEMESTER = getCurrentSemester();

    public static final PersianDate CURRENT_PERSIAN_DATE = PersianDate.now();
    public static final LocalDate CURRENT_DATE = CURRENT_PERSIAN_DATE.toGregorian();



    public static Integer getIntegerNum(){
        Integer num = null;
        try {
            num = SCANNER.nextInt();
            SCANNER.nextLine();
        } catch (InputMismatchException e){
            System.out.println(e.getMessage());
        }
        return num;
    }

    public static Long getLongNum(){
        Long num = null;
        try {
            num = SCANNER.nextLong();
            SCANNER.nextLine();
        } catch (InputMismatchException e){
            e.printStackTrace();
        }
        return num;
    }

    public static Double getDoubleNum(){
        Double num = null;
        try {
            num = SCANNER.nextDouble();
            SCANNER.nextLine();
        } catch (InputMismatchException e){
            e.printStackTrace();
        }
        return num;
    }

    public static String getString(){
        String string = null;
        while (true) {
            try {
                string = SCANNER.nextLine();
                if (string.isBlank() || string.isEmpty()) {
                    System.out.println("Input can't empty");
                } else break;
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
        return string;
    }

    public static LocalTime getLocalTime(){
        boolean flag = true;
        Integer hour = null;
        Integer minutes = null;
        while (flag){
            System.out.println("Enter hour: ");
            hour = getIntegerNum();
            System.out.println("Enter minutes: ");
            minutes = getIntegerNum();
            if (hour>=0 && hour<24){
                if (minutes>=0 && minutes<60){
                    flag = false;
                } else System.out.println("Wrong input for minutes. (try again)");
            }else System.out.println("Wrong input for hour. (try again)");
        }
        return LocalTime.of(hour, minutes);
    }
    public static LocalDate getLocalDate(){
        boolean flag = true;
        Integer year = null;
        Integer month = null;
        Integer day = null;
        while (flag){
            System.out.println("Enter year:");
            year = getIntegerNum();
            System.out.println("Enter month:");
            month = getIntegerNum();
            System.out.println("Enter day:");
            day = getIntegerNum();
            if (month>0 && month<13){
                if (month<=6){
                    if (day>0 && day<32){
                        flag = false;
                    } else System.out.println("days of month in six first of year is 31");
                }else if (month >= 7  && month < 12){
                    if (day>0 && day < 31){
                        flag = false;
                    }else System.out.println("days of month between 7-11 of year is 30");
                } else {
                    if (day>0 && day < 30){
                        flag = false;
                    }else System.out.println("days of Last month of year is 29");
                }
            } else System.out.println("Month should be between 1-12.");
        }
        return LocalDate.of(year, month, day);
    }

    public static LocalDate getPersianDate(){
        Integer year = null;
        Integer month = null;
        Integer day = null;
        while (true){
            System.out.println("Enter year:");
            year = getIntegerNum();
            System.out.println("Enter month:");
            month = getIntegerNum();
            System.out.println("Enter day:");
            day = getIntegerNum();
            if (month>0 && month<13){
                if (month<=6){
                    if (day>0 && day<32){
                        break;
                    } else System.out.println("days of month in six first of year is 31");
                }else if (month >= 7  && month < 12){
                    if (day>0 && day < 31){
                        break;
                    }else System.out.println("days of month between 7-11 of year is 30");
                } else {
                    if (day>0 && day < 30){
                        break;
                    }else System.out.println("days of Last month of year is 29");
                }
            } else System.out.println("Month should be between 1-12.");
        }

        return PersianDate.of(year, month, day).toGregorian();
    }

    public static String generateSecurePassword() {
        PasswordGenerator gen = new PasswordGenerator();
        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(2);

        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(2);

        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(2);

        CharacterData specialChars = new CharacterData() {
            public String getErrorCode() {
                return ERROR_CODE;
            }

            public String getCharacters() {
                return "@#!%&*";
            }
        };
        CharacterRule splCharRule = new CharacterRule(specialChars);
        splCharRule.setNumberOfCharacters(2);

        return gen.generatePassword(8, splCharRule, lowerCaseRule,
                upperCaseRule, digitRule);

    }

    public static Semester getCurrentSemester(){
        List<Semester> semesterList = SEMESTER_SERVICE.findAll();
        return semesterList.get(semesterList.size() - 1);
    }
}
