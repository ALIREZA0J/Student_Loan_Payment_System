package org.loanpayment.Menu;

import lombok.extern.slf4j.Slf4j;
import org.loanpayment.model.*;
import org.loanpayment.model.enums.*;

import java.time.LocalDate;
import java.util.Optional;

import static org.loanpayment.Menu.Inputs.*;
import static org.loanpayment.Menu.MainMenu.getCity;
import static org.loanpayment.Menu.MainMenu.getPerson;

@Slf4j
public class LoanMenu {

    public static void loanMenu(Student student) {
        log.info("loan menu start");
        boolean flag = true;
        while (flag) {
            System.out.println("|----------------------------------------|");
            System.out.println("|------------   Loan  Menu   ------------|");
            System.out.println("|----------------------------------------|");
            System.out.println("1- Tuition Loan ");
            System.out.println("2- Education Loan");
            System.out.println("3- Housing Loan");
            System.out.println("0- return to student dashboard");
            try {
                int select = getIntegerNum();
                switch (select) {
                    case 1 -> tuitionLoan(student);
                    case 2 -> educationLoan(student);
                    case 3 -> housingLoan(student);
                    case 0 -> flag = false;
                    default -> System.out.println("Error 404 not found.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private static void tuitionLoan(Student student) {
        log.info("tuitionLoan check for student");
        if (student.getUniversity().getUniversityType().equals(UniversityType.DOLATI_ROZANE)) {
            log.warn("You are not allowed to receive a tuition loan.");
            log.warn("The reason is your university is DOLATI_ROZANE.");
//            System.out.println("You are not allowed to receive a tuition loan.");
//            System.out.println("The reason is your university is DOLATI_ROZANE.");
        } else {
            Optional<Loan> loan = LOAN_SERVICE
                    .findByStudentAndSemesterAndLoanType(student, CURRENT_SEMESTER, LoanType.TUITION);
            if (loan.isPresent()) {
                log.warn("You are not allowed to receive the loan.\n" +
                        "The reason is you received this loan in current semester recently.");
//                System.out.println("You are not allowed to receive the loan.\n" +
//                        "The reason is you received this loan in current semester recently.");
            } else {
                Loan newLoan = null;
                Section section = student.getSection();
                if (section.equals(Section.ASSOCIATE) || section.equals(Section.BACHELOR)
                        || section.equals(Section.NON_CONTIGUOUS_BACHELOR)) {
                    newLoan = Loan.builder()
                            .setLoanType(LoanType.TUITION).setLoanDate(CURRENT_DATE)
                            .setLoanSection(section).setStudent(student).setSemester(CURRENT_SEMESTER)
                            .setAmount(1_300_000L)
                            .build();
                } else if (section.equals(Section.MASTER) || section.equals(Section.NON_CONTIGUOUS_MASTER) ||
                        section.equals(Section.PROFESSIONAL_DOCTOR) || section.equals(Section.DOCTOR)) {
                    newLoan = Loan.builder()
                            .setLoanType(LoanType.TUITION).setLoanDate(CURRENT_DATE)
                            .setLoanSection(section).setStudent(student).setSemester(CURRENT_SEMESTER)
                            .setAmount(2_600_000L)
                            .build();
                } else if (section.equals(Section.NON_CONTINUOUS_SPECIALIZED_DOCTOR)) {
                    newLoan = Loan.builder()
                            .setLoanType(LoanType.TUITION).setLoanDate(CURRENT_DATE)
                            .setLoanSection(section).setStudent(student).setSemester(CURRENT_SEMESTER)
                            .setAmount(6_500_000L)
                            .build();
                }
                Loan saveNewLoan = LOAN_SERVICE.saveOrUpdate(newLoan);
                calculateLoanInstallmentsAndRegistration(saveNewLoan);
                System.out.println("Tuition loan with amount " + saveNewLoan.getAmount() + " registered for you.");
                depositingLoanMoneyToTheStudentAccount(student, saveNewLoan);
                log.info("loan registered");
            }
        }

    }

    private static void educationLoan(Student student) {
        log.info("educationLoan check for student");
        Optional<Loan> loan = LOAN_SERVICE
                .findByStudentAndSemesterAndLoanType(student, CURRENT_SEMESTER, LoanType.EDUCATION);
        if (loan.isPresent()) {
            log.warn("You are not allowed to receive the loan.\n" +
                    "The reason is you received this loan in current semester recently.");
//            System.out.println("You are not allowed to receive the loan.\n" +
//                    "The reason is you received this loan in current semester recently.");
        } else {
            Loan newLoan = null;
            Section section = student.getSection();
            if (section.equals(Section.ASSOCIATE) || section.equals(Section.BACHELOR)
                    || section.equals(Section.NON_CONTIGUOUS_BACHELOR)) {
                newLoan = Loan.builder()
                        .setLoanType(LoanType.EDUCATION).setLoanDate(CURRENT_DATE)
                        .setLoanSection(section).setStudent(student).setSemester(CURRENT_SEMESTER)
                        .setAmount(1_900_000L)
                        .build();
            } else if (section.equals(Section.MASTER) || section.equals(Section.NON_CONTIGUOUS_MASTER) ||
                    section.equals(Section.PROFESSIONAL_DOCTOR) || section.equals(Section.DOCTOR)) {
                newLoan = Loan.builder()
                        .setLoanType(LoanType.EDUCATION).setLoanDate(CURRENT_DATE)
                        .setLoanSection(section).setStudent(student).setSemester(CURRENT_SEMESTER)
                        .setAmount(2_250_000L)
                        .build();
            } else if (section.equals(Section.NON_CONTINUOUS_SPECIALIZED_DOCTOR)) {
                newLoan = Loan.builder()
                        .setLoanType(LoanType.EDUCATION).setLoanDate(CURRENT_DATE)
                        .setLoanSection(section).setStudent(student).setSemester(CURRENT_SEMESTER)
                        .setAmount(2_600_000L)
                        .build();
            }
            Loan saveNewLoan = LOAN_SERVICE.saveOrUpdate(newLoan);
            calculateLoanInstallmentsAndRegistration(saveNewLoan);
            System.out.println("Education loan with amount " + saveNewLoan.getAmount() + " registered for you.");
            depositingLoanMoneyToTheStudentAccount(student, saveNewLoan);
            log.info("loan registered");
        }
    }

    private static void housingLoan(Student student) {
        log.info("housingLoan check for student");
        if (student.getDormitoryUser().equals(true)) {
            log.warn("You are not allowed to receive a housing loan.\"The reason is you use dormitory.\"");
//            System.out.println("You are not allowed to receive a housing loan.");
//            System.out.println("The reason is you use dormitory.");
        } else if (student.getMaritalStatus().equals(MaritalStatus.SINGLE)) {
            log.warn("You are not allowed to receive a housing loan.\"The reason is you are not married.\"");
//            System.out.println("You are not allowed to receive a housing loan.");
//            System.out.println("The reason is you are not married.");
        } else {

            Optional<Spouse> studentAsSpouse = SPOUSE_SERVICE.findByNationalCode(student.getNationalCode());
            Optional<RentHouse> rentHouse;
            Optional<Spouse> spouse;
            if (studentAsSpouse.isPresent()){
                Student studentFindSpouseOfThisStudent = studentAsSpouse.get().getStudent();

                Optional<Spouse> optionalSpouse = SPOUSE_SERVICE.findByNationalCode(studentFindSpouseOfThisStudent.getNationalCode());
                if (optionalSpouse.isPresent()){
                    spouse = optionalSpouse;
                } else {

                    Spouse spouseForSave = Spouse.builder()
                            .setFirstName(studentFindSpouseOfThisStudent.getFirstName()).setLastName(studentFindSpouseOfThisStudent.getLastName())
                            .setMotherName(studentFindSpouseOfThisStudent.getMotherName()).setFatherName(studentFindSpouseOfThisStudent.getFatherName())
                            .setStudent(student)
                            .setNationalCode(studentFindSpouseOfThisStudent.getNationalCode()).setGender(studentFindSpouseOfThisStudent.getGender()).build();
                    SPOUSE_SERVICE.saveOrUpdate(spouseForSave);

                    spouse = SPOUSE_SERVICE.findByNationalCode(studentFindSpouseOfThisStudent.getNationalCode());
                }



                Optional<RentHouse> rentHouseOfThisCouple = RENT_HOUSE_SERVICE.findByStudent(studentFindSpouseOfThisStudent);
                if (rentHouseOfThisCouple.isPresent())
                    rentHouse = rentHouseOfThisCouple;
                else
                    rentHouse = saveStudentRentHouseData(student);
            } else
            {
                Optional<RentHouse> rentHouseOfStudent = RENT_HOUSE_SERVICE.findByStudent(student);
                Optional<Spouse> spouseOfStudent = SPOUSE_SERVICE.findByStudent(student);

                if (rentHouseOfStudent.isPresent() && spouseOfStudent.isPresent()) {
                    rentHouse = rentHouseOfStudent;
                    spouse = spouseOfStudent;
                } else if (spouseOfStudent.isPresent()) {
                    Optional<Student> studentByNationalCode = STUDENT_SERVICE.findByNationalCode(spouseOfStudent.get().getNationalCode());
                    if (studentByNationalCode.isPresent()){
                        Optional<RentHouse> rentHouseOfThisCouple = RENT_HOUSE_SERVICE.findByStudent(studentByNationalCode.get());
                        if (rentHouseOfThisCouple.isPresent()){
                            rentHouse = rentHouseOfThisCouple;
                        }
                        else
                            rentHouse = saveStudentRentHouseData(student);
                    } else {
                        rentHouse = saveStudentRentHouseData(student);
                    }
                    spouse = spouseOfStudent;

                } else if (rentHouseOfStudent.isPresent()) {
                    spouse = getSpouseOfStudent(student);
                    rentHouse = rentHouseOfStudent;
                } else {
                    rentHouse = saveStudentRentHouseData(student);
                    spouse = getSpouseOfStudent(student);
                }
            }




            if (rentHouse.isPresent() && spouse.isPresent()) {
                Optional<Student> studentByNationalCode = STUDENT_SERVICE.findByNationalCode(spouse.get().getNationalCode());
                if (studentByNationalCode.isPresent()) {
                    Student spouseStudentOfStudent = studentByNationalCode.get();
                    Optional<Loan> housingLoanIsExistForSpouseOfStudent = LOAN_SERVICE
                            .findByStudentAndSectionAndLoanType(spouseStudentOfStudent, student.getSection(), LoanType.HOUSING);
                    if (housingLoanIsExistForSpouseOfStudent.isEmpty()) {
                        System.out.println("you can register for housing loan");
                        registerHousingLoan(student, rentHouse.get());
                    } else
                        System.out.println("you can't register for housing loan");
                } else {
                    registerHousingLoan(student, rentHouse.get());
                }
            } else {
                System.out.println("Your data is not complete.");
            }
        }


    }


    private static void registerHousingLoan(Student student, RentHouse rentHouse) {
        log.info("registerHousingLoan");
        Loan newLoan = null;
        Section section = student.getSection();
        if (rentHouse.getProvince().equals(City.TEHRAN)) {
            newLoan = Loan.builder()
                    .setLoanType(LoanType.HOUSING).setLoanDate(CURRENT_DATE)
                    .setLoanSection(section).setStudent(student).setSemester(CURRENT_SEMESTER)
                    .setAmount(32_000_000L)
                    .build();
        } else if (checkIsTheCityOfRentHouseInTheMetropolis(rentHouse.getProvince())) {
            newLoan = Loan.builder()
                    .setLoanType(LoanType.HOUSING).setLoanDate(CURRENT_DATE)
                    .setLoanSection(section).setStudent(student).setSemester(CURRENT_SEMESTER)
                    .setAmount(26_000_000L)
                    .build();
        } else {
            newLoan = Loan.builder()
                    .setLoanType(LoanType.HOUSING).setLoanDate(CURRENT_DATE)
                    .setLoanSection(section).setStudent(student).setSemester(CURRENT_SEMESTER)
                    .setAmount(19_500_000L)
                    .build();
        }
        Loan saveNewLoan = LOAN_SERVICE.saveOrUpdate(newLoan);
        calculateLoanInstallmentsAndRegistration(saveNewLoan);
        System.out.println("Housing loan with amount " + saveNewLoan.getAmount() + " registered for you.");
        depositingLoanMoneyToTheStudentAccount(student, saveNewLoan);
    }


    private static Optional<Spouse> getSpouseOfStudent(Student partnerStudent) {
        log.info("getSpouseOfStudent");
        System.out.println("""
                You must enter your spouse's information.
                Is your spouse a student?
                1-Yes
                2-No                
                """);

        Integer select = getIntegerNum();

        Spouse spouse = null;
        switch (select) {
            case 1 -> {
                System.out.println("Enter Your Spouse Student code:");
                Long studentCode = getLongNum();
                Student student = STUDENT_SERVICE.findByStudentCode(studentCode).get();
                spouse = Spouse.builder()
                        .setFirstName(student.getFirstName()).setLastName(student.getLastName())
                        .setMotherName(student.getMotherName()).setFatherName(student.getFatherName())
                        .setStudent(partnerStudent)
                        .setNationalCode(student.getNationalCode()).setGender(student.getGender()).build();
            }
            case 2 -> {
                Person person = getPerson();
                spouse = Spouse.builder()
                        .setFirstName(person.getFirstName()).setLastName(person.getLastName())
                        .setMotherName(person.getMotherName()).setFatherName(person.getFatherName())
                        .setNationalCode(person.getNationalCode()).setGender(person.getGender())
                        .setStudent(partnerStudent)
                        .build();
            }
        }

        Spouse saveSpouse = SPOUSE_SERVICE.saveOrUpdate(spouse);
        return Optional.ofNullable(saveSpouse);
    }

    private static void depositingLoanMoneyToTheStudentAccount(Student student, Loan saveNewLoan) {
        log.info("depositingLoanMoneyToTheStudentAccount");
        BankCard bankCard = BANK_CARD_SERVICE.findByStudent(student).get();
        bankCard.setInventory(bankCard.getInventory()+saveNewLoan.getAmount());
        BANK_CARD_SERVICE.saveOrUpdate(bankCard);
        System.out.println("The money has been deposited into your account.");
    }


    private static boolean checkIsTheCityOfRentHouseInTheMetropolis(City city) {
        log.info("checkIsTheCityOfRentHouseInTheMetropolis");
        if (city.equals(City.GUILAN) || city.equals(City.ESFAHAN) || city.equals(City.EAST_AZARBAIJAN)
                || city.equals(City.FARS) || city.equals(City.KHUZESTAN) || city.equals(City.QOM)
                || city.equals(City.KHORASAN_RAZAVI) || city.equals(City.ALBORZ)
        ) {
            return true;
        } else return false;
    }


    private static void calculateLoanInstallmentsAndRegistration(Loan loan) {
        log.info("calculateLoanInstallmentsAndRegistration");
        double interestRate = 0.04;
        int numberOfMonths = 60;
        double amount = loan.getAmount() + (loan.getAmount() * interestRate * 5);
        double installmentAmount = (amount / (12 * 31));

        LocalDate dueDate = specifyTheStartDateOfTheInstallments(loan);

        for (int i = 1; i <= numberOfMonths; i++) {

            Installment installment = new Installment(i, installmentAmount, dueDate,
                    null, InstallmentStatus.UNPAID, loan);
            INSTALLMENT_SERVICE.saveOrUpdate(installment);
            dueDate = dueDate.plusMonths(1);
            if (i % 12 == 0) {
                installmentAmount *= 2;
            }
        }

    }

    private static LocalDate specifyTheStartDateOfTheInstallments(Loan loan) {
        log.info("specifyTheStartDateOfTheInstallments");
        Section section = loan.getLoanSection();
        LocalDate firstDueDate = loan.getLoanDate();

        if (section.equals(Section.BACHELOR)) {
            firstDueDate = firstDueDate.plusYears(4);
        } else if (section.equals(Section.ASSOCIATE) || section.equals(Section.NON_CONTIGUOUS_MASTER)
                || section.equals(Section.NON_CONTIGUOUS_BACHELOR)) {
            firstDueDate = firstDueDate.plusYears(2);
        } else if (section.equals(Section.MASTER)) {
            firstDueDate = firstDueDate.plusYears(6);
        } else if (section.equals(Section.NON_CONTINUOUS_SPECIALIZED_DOCTOR) || section.equals(Section.DOCTOR)
                || section.equals(Section.PROFESSIONAL_DOCTOR)) {
            firstDueDate = firstDueDate.plusYears(5);
        }

        return firstDueDate;
    }


    private static Optional<RentHouse> saveStudentRentHouseData(Student student) {
        log.info("saveStudentRentHouseData");
        System.out.println("*****Rent House data*****");
        City city = getCity();
        System.out.println("Enter the postal code: ");
        Long postalCode = getLongNum();
        System.out.println("Enter the rental number: ");
        Long rentalNumber = getLongNum();


        RentHouse rentHouse = RentHouse.builder()
                .setProvince(city)
                .setPostalCode(postalCode)
                .setRentalNumber(rentalNumber)
                .setStudent(student)
                .build();
        return Optional.ofNullable(RENT_HOUSE_SERVICE.saveOrUpdate(rentHouse));


    }
}
