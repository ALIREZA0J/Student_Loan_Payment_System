package org.loanpayment.Menu;

import com.github.mfathi91.time.PersianDate;
import lombok.extern.slf4j.Slf4j;
import org.loanpayment.model.BankCard;
import org.loanpayment.model.Installment;
import org.loanpayment.model.Loan;
import org.loanpayment.model.Student;
import org.loanpayment.model.enums.InstallmentStatus;
import org.loanpayment.model.enums.LoanType;

import java.time.LocalDate;
import java.util.List;

import static org.loanpayment.Menu.Inputs.*;
import static org.loanpayment.Menu.StudentMenu.studentDashboard;
@Slf4j
public class InstallmentMenu {







    public static void installmentMenu(Student student) {
        log.info("installment menu start");
        boolean flag = true;
        while (flag) {
            System.out.println("|----------------------------------------|");
            System.out.println("|            Installment Menu            |");
            System.out.println("|----------------------------------------|");
            System.out.println("1- Paid Installments ");
            System.out.println("2- UnPaid Installments");
            System.out.println("3- Payment Installment");
            System.out.println("0- return to student dashboard");
            System.out.println("Enter your Select:");
            int select = getIntegerNum();
            switch (select) {
                case 1 -> paidInstallment(student);
                case 2 -> unPaidInstallment(student);
                case 3 -> paymentInstallment(student);
                case 0 -> flag = false;
                default -> System.out.println("Error 404 not found");
            }
        }
    }



    private static void paidInstallment(Student student){
        log.info("paidInstallment");
        LoanType loanType = getLoanType();
        List<Loan> loanListOfStudent = LOAN_SERVICE.findByStudentAndLoanType(student, loanType);

        if (loanListOfStudent.isEmpty()){
            System.out.println("You don't have any loan.");
        } else {
            loanListOfStudent.forEach(loan -> System.out.println(
                "Loan Id: " + loan.getId()
                        +" | Type : " + loan.getLoanType()
                        +" | Section : " + loan.getLoanSection()
                        +" | Semester : " + loan.getSemester().getSemesterNumber()
                        +" | Semester start date : " + PersianDate.fromGregorian(loan.getSemester().getStartDate())));

            System.out.println("Enter the id of the loan to see your paid installments.");
            System.out.print("Id: ");
            Long loanId = getLongNum();
            Loan loan = LOAN_SERVICE.findById(loanId).get();
            List<Installment> paidInstallmentLoan = INSTALLMENT_SERVICE
                    .findByLoan(loan, InstallmentStatus.PAID);

            if (paidInstallmentLoan.isEmpty()){
                System.out.println("You don't have any paid installment for this loan.");
            }else {
            paidInstallmentLoan.forEach(installment -> System.out.println(
                            "Installment Number : " + installment.getInstallmentNumber() +
                                    " | Payment Date : " + PersianDate.fromGregorian(installment.getPaymentDate()) +
                                    " | ّFor loan : " + installment.getLoan().getLoanType()
                    ));
            }
        }

    }

    private static void unPaidInstallment(Student student){
        log.info("unPaidInstallment");
        LoanType loanType = getLoanType();
        List<Loan> loanListOfStudent = LOAN_SERVICE.findByStudentAndLoanType(student, loanType);
        if (loanListOfStudent.isEmpty()){
            System.out.println("You don't have any loan.");
        } else {
            loanListOfStudent.forEach(loan -> System.out.println(
                            "Loan Id: " + loan.getId()
                                    + " | Type : " + loan.getLoanType()
                                    + " | Section : " + loan.getLoanSection()
                                    + " | Semester : " + loan.getSemester().getSemesterNumber()
                                    + " | Semester start date : " + PersianDate.fromGregorian(loan.getSemester()
                                    .getStartDate())));

            System.out.println("Enter the id of the loan to see your unpaid installments.");
            System.out.print("Id: ");
            Long loanId = getLongNum();
            Loan loan = LOAN_SERVICE.findById(loanId).get();
            List<Installment> unPaidInstallmentLoan = INSTALLMENT_SERVICE
                    .findByLoan(loan, InstallmentStatus.UNPAID);

            if (unPaidInstallmentLoan.isEmpty()){
                System.out.println("You don't have any unpaid installment for this loan.");
            }else {
                unPaidInstallmentLoan
                        .forEach(installment -> System.out.println(
                                "Installment Number : " + installment.getInstallmentNumber() +
                                        " | Due date : " + PersianDate.fromGregorian(installment.getDueDate()) +
                                        " | Installment Amount: " + installment.getAmount() +
                                        " | ّFor loan : " + installment.getLoan().getLoanType()
                        ));
            }
        }

    }

    private static void paymentInstallment(Student student){
        log.info("paymentInstallment");
        BankCard bankCardOfStudent = BANK_CARD_SERVICE.findByStudent(student).get();
        List<Loan> loanList = LOAN_SERVICE.findByStudent(student);
        if (loanList.isEmpty()){
            System.out.println("You don't have any loan to pay the installment.");
        } else {
            BankCard bankCardData = getBankCardData();
            for (int i = 0; i < loanList.size(); i++) {
                Loan loan = loanList.get(i);
                INSTALLMENT_SERVICE.findByLoan(loan, InstallmentStatus.UNPAID)
                        .stream()
                        .filter(installment -> installment.getDueDate().isBefore(CURRENT_DATE)
                                || installment.getDueDate().equals(CURRENT_DATE))
                        .forEach(System.out::println);
            }
            while (true){
                System.out.println("If You want to pay the installment loan enter the id of the installment else enter zero (-0-): ");
                Long installmentId = getLongNum();
                if (installmentId != 0){
                    if (checkBankCardOfStudentEqualBankCardData(bankCardOfStudent,bankCardData)) {
                        Installment installment = INSTALLMENT_SERVICE.findById(installmentId).get();
                        installment.setInstallmentStatus(InstallmentStatus.PAID);
                        installment.setPaymentDate(CURRENT_DATE);
                        INSTALLMENT_SERVICE.saveOrUpdate(installment);
                        System.out.println("Installment with id : " + installment.getId() + " is paid.");
                    } else {
                        System.out.println("Attention the information entered for the bank card does not match your bank card ");
                        break;
                    }
                } else {
                    System.out.println("Have good day.");
                    break;
                }
            }

        }

    }

    private static boolean checkBankCardOfStudentEqualBankCardData(BankCard bankCardOfStudent,BankCard bankCardData){
        log.info("checkBankCardOfStudentEqualBankCardData");
        if (bankCardOfStudent.getCardNumber().equals(bankCardData.getCardNumber())
        && bankCardOfStudent.getCvv2().equals(bankCardData.getCvv2())
        && bankCardOfStudent.getExpirationDate().equals(bankCardData.getExpirationDate())
        ) return true;
        else return false;
    }

    private static BankCard getBankCardData(){
        log.info("getBankCardData");
        System.out.println("Enter Your Bank Card data: ");
        System.out.println("Card Number:");
        Long cardNumber = getLongNum();
        System.out.println("CVV2 :");
        Integer cvv2 = getIntegerNum();
        System.out.println("Expiration Date");
        LocalDate expirationData = getPersianDate();

        return BankCard.builder()
                .setCardNumber(cardNumber)
                .setCvv2(cvv2)
                .setExpirationDate(expirationData)
                .build();
    }


    private static LoanType getLoanType(){
        log.info("getLoanType");
        System.out.println("""
                select type of loan you want to see :
                1-TUITION
                2-EDUCATION
                3-HOUSING
                """);

        Integer select = getIntegerNum();
        LoanType loanType = null;
        switch (select){
            case 1 -> loanType = LoanType.TUITION;
            case 2 -> loanType = LoanType.EDUCATION;
            case 3 -> loanType = LoanType.HOUSING;
            default -> System.out.println("not found.");
        }

        return loanType;
    }

}
