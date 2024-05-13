package org.loanpayment.Menu;

import com.github.mfathi91.time.PersianDate;
import com.github.mfathi91.time.PersianMonth;
import org.loanpayment.model.Student;
import org.loanpayment.model.enums.Section;

import java.time.LocalDate;

import static org.loanpayment.Menu.Inputs.*;
import static org.loanpayment.Menu.InstallmentMenu.installmentMenu;
import static org.loanpayment.Menu.LoanMenu.loanMenu;


public class StudentMenu {



    private static void checkStudentGraduatedForRegisterLoan(Student student){
        LocalDate entriesYear = student.getSemester().getStartDate();
        Section section = student.getSection();

        if (section.equals(Section.BACHELOR) ) {
            LocalDate plusYears = entriesYear.plusYears(4);
            if (CURRENT_DATE.isBefore(plusYears)) {
                checkingTheLoanRegistrationTime(student);
            } else {
                System.out.println("First we congratulate you on your graduation.");
                System.out.println("You couldn't get loan. Because you have graduated.");
            }
        } else if (section.equals(Section.ASSOCIATE) || section.equals(Section.NON_CONTIGUOUS_MASTER)
                || section.equals(Section.NON_CONTIGUOUS_BACHELOR)) {
            LocalDate plusYears = entriesYear.plusYears(2);
            if (CURRENT_DATE.isBefore(plusYears)) {
                checkingTheLoanRegistrationTime(student);
            } else {
                System.out.println("First we congratulate you on your graduation.");
                System.out.println("You couldn't get loan. You have graduated");
            }
        } else if (section.equals(Section.MASTER)) {
            LocalDate plusYears = entriesYear.plusYears(6);
            if (CURRENT_DATE.isBefore(plusYears)) {
                checkingTheLoanRegistrationTime(student);
            } else {
                System.out.println("First we congratulate you on your graduation.");
                System.out.println("You couldn't get loan. You have graduated");
            }
        } else if (section.equals(Section.NON_CONTINUOUS_SPECIALIZED_DOCTOR) || section.equals(Section.DOCTOR)
                || section.equals(Section.PROFESSIONAL_DOCTOR)) {
            LocalDate plusYears = entriesYear.plusYears(5);
            if (CURRENT_DATE.isBefore(plusYears)) {
                checkingTheLoanRegistrationTime(student);
            } else {
                System.out.println("First we congratulate you on your graduation.");
                System.out.println("You couldn't get loan. You have graduated.");
            }
        }
    }
    private static void checkingTheLoanRegistrationTime(Student student) {

        LocalDate startDateAban = PersianDate.of(CURRENT_PERSIAN_DATE.getYear(), PersianMonth.ABAN, 1).toGregorian();
        LocalDate endDateAban = startDateAban.plusWeeks(1);
        LocalDate startDateBahman = PersianDate.of(CURRENT_PERSIAN_DATE.getYear(), PersianMonth.BAHMAN, 25).toGregorian();
        LocalDate endDateBahman = startDateBahman.plusWeeks(1);

        if ((((CURRENT_DATE.isAfter(startDateAban) || CURRENT_DATE.equals(startDateAban)) &&
                CURRENT_DATE.isBefore(endDateAban)) || ((CURRENT_DATE.isAfter(startDateBahman) ||
                CURRENT_DATE.equals(startDateBahman)) && CURRENT_DATE.isBefore(endDateBahman)))) {
            loanMenu(student);
        } else {
            System.out.println("You are not allowed to register a loan on these dates");
            System.out.println("Because date of registration for loan has passed ");
        }
    }

    private static void checkStudentGraduatedForRepaymentInstallment(Student student){
        LocalDate entriesYear = student.getSemester().getStartDate();
        Section section = student.getSection();

        if (section.equals(Section.BACHELOR) ) {
            LocalDate plusYears = entriesYear.plusYears(4);
            if (CURRENT_DATE.isAfter(plusYears)) {
                installmentMenu(student);
            } else {
                System.out.println("Refunds have not been activated for you");
            }
        } else if (section.equals(Section.ASSOCIATE) || section.equals(Section.NON_CONTIGUOUS_MASTER)
                || section.equals(Section.NON_CONTIGUOUS_BACHELOR)) {
            LocalDate plusYears = entriesYear.plusYears(2);
            if (CURRENT_DATE.isAfter(plusYears)) {
                installmentMenu(student);
            } else {
                System.out.println("Refunds have not been activated for you");
            }
        } else if (section.equals(Section.MASTER)) {
            LocalDate plusYears = entriesYear.plusYears(6);
            if (CURRENT_DATE.isAfter(plusYears)) {
                installmentMenu(student);
            } else {
                System.out.println("Refunds have not been activated for you");
            }
        } else if (section.equals(Section.NON_CONTINUOUS_SPECIALIZED_DOCTOR) || section.equals(Section.DOCTOR)
                || section.equals(Section.PROFESSIONAL_DOCTOR)) {
            LocalDate plusYears = entriesYear.plusYears(5);
            if (CURRENT_DATE.isAfter(plusYears)) {
                installmentMenu(student);
            } else {
                System.out.println("Refunds have not been activated for you");
            }
        }
    }






    public static void studentDashboard(Student student) {
        boolean flag = true;
        while (flag) {
            System.out.println("|----------------------------------------|");
            System.out.println("|----------  Student Dashboard  ---------|");
            System.out.println("|----------------------------------------|");
            System.out.println("1- Register Loan");
            System.out.println("2- installment Payment");
            System.out.println("3- return to main menu");
            System.out.println("0- Exit");
            System.out.print("Enter Your Select:");
            int select = getIntegerNum();
            switch (select) {
                case 1 -> checkStudentGraduatedForRegisterLoan(student);
                case 2 -> checkStudentGraduatedForRepaymentInstallment(student);
                case 3 -> new MainMenu().menu();
                case 0 -> {
                    System.out.println("Have good day.");
                    flag=false;
                }
                default -> System.out.println("Error 404 not found.");
            }
        }
    }
}
