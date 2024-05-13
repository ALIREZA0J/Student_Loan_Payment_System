package org.loanpayment.Menu;


import org.loanpayment.model.*;
import org.loanpayment.model.enums.*;
import java.time.LocalDate;
import java.util.Optional;
import static org.loanpayment.Menu.Inputs.*;

public class MainMenu {


    public void menu(){


        System.out.println("|---------------------------------------------|");
        System.out.println("|         WELCOME TO LOAN PAYMENT SYSTEM      |");
        System.out.println("|---------------------------------------------|");
        System.out.println("| 1- Sign in         |");
        System.out.println("| 2- Sign up         |");
        System.out.println("| 0- Exit            |");
        System.out.println("|--------------------|");
        System.out.println("Enter your select:");
        try {
            int select = getIntegerNum();
            switch (select) {
                case 1 -> login();
                case 2 -> signup();
                case 0 -> System.out.println("exit");
                default -> System.out.println("Error 404 not found");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("!!!WRONG!!!");
        }
    }



    private void login(){
        System.out.println("Welcome back.");


        boolean flag = true;
        while (flag){
            System.out.println("Enter Your Username: ");
            String username = getString();
            System.out.println("Enter Your password: ");
            String password = getString();

            Optional<Student> student = STUDENT_SERVICE.findByUsernameAndPassword(username,password);
            if (student.isPresent()) {
                StudentMenu.studentDashboard(student.get());
                flag = false;
            } else {
                System.out.println("Username or password unCorrect !!!!");
                System.out.println("Please Try Again ... ");
            }
        }
    }

    private void signup(){
        System.out.println("                                          ");
        Person person = getPerson();
        Student student = getStudent(person);

        Student saveStudent = STUDENT_SERVICE.registerStudent(student);
        getBankcardData(saveStudent);
        System.out.println("Your Student data successfully registered.");
        System.out.println("Your username: " + student.getUsername());
        System.out.println("Your password: " + student.getPassword());
        System.out.println("Thank you for your trust. ");
        login();
    }

    private void getBankcardData(Student student){
        System.out.println("Now You Should Enter Your Bank Card Data Which You Want To Give Loan: ");
        BankType bankType = getBankType();
        System.out.println("Enter Your Card Number: ");
        Long cardNumber = getLongNum();
        System.out.println("Enter cvv2 : ");
        Integer cvv2 = getIntegerNum();
        System.out.println("Enter expiration date: ");
        LocalDate expirationDate = getPersianDate();

        BankCard bankCard = BankCard.builder()
                .setBankType(bankType).setCardNumber(cardNumber)
                .setCvv2(cvv2).setExpirationDate(expirationDate)
                .setStudent(student)
                .build();

        BANK_CARD_SERVICE.saveOrUpdate(bankCard);
    }


    private  BankType getBankType(){
        System.out.println("""
                Select Bank name:
                1-Maskan
                2-Meli
                3-Tejarat
                4-Refah
                """);
        Integer select = getIntegerNum();
        BankType bankType = null;
        switch (select){
            case 1 -> bankType = BankType.Maskan;
            case 2 -> bankType = BankType.Meli;
            case 3 -> bankType = BankType.Tejarat;
            case 4 -> bankType = BankType.Refah;
        }

        return bankType;
    }





    protected static Person getPerson(){
        System.out.println("Enter First Name :");
        String fName = Inputs.getString();
        System.out.println("Enter Last Name:");
        String lName = Inputs.getString();
        System.out.println("Enter National Code:");
        String nationalCode = Inputs.getString();
        System.out.println("Enter father_name:");
        String fatherName = Inputs.getString();
        System.out.println("Enter mother_name:");
        String motherName = Inputs.getString();
        Gender gender = getGender();


        return Person.builder()
                .setFirstName(fName).setLastName(lName)
                .setFatherName(fatherName).setMotherName(motherName)
                .setNationalCode(nationalCode).setGender(gender)
                .build();


    }

    private Student getStudent(Person person){
        System.out.println("Enter Your Student Code: ");
        Long studentCode = getLongNum();
        Section section = getSection();
        boolean dormitoryUserOrNot = dormitoryUserOrNot();
        MaritalStatus maritalStatus = getMaritalStatus();
        University university = getUniversity();
        Semester semester = getEnterSemester();

        return Student.builder()
                .setStudentCode(studentCode).setSection(section)
                .setDormitoryUser(dormitoryUserOrNot).setMaritalStatus(maritalStatus)
                .setUniversity(university).setSemester(semester)
                .setUsername(person.getNationalCode()).setPassword(generateSecurePassword())
                .setFatherName(person.getFatherName()).setMotherName(person.getMotherName())
                .setFirstName(person.getFirstName()).setLastName(person.getLastName())
                .setNationalCode(person.getNationalCode()).setGender(person.getGender())
                .build();

    }

    private  University getUniversity(){
        System.out.println("Select Your University: ");
        UNIVERSITY_SERVICE.findAll().forEach(System.out::println);
        Long idOfUniversity = getLongNum();
        return UNIVERSITY_SERVICE.findById(idOfUniversity).get();
    }

    private  Semester getEnterSemester(){
        System.out.println("Select You Entry Semester: ");
        SEMESTER_SERVICE.findAll().forEach(System.out::println);
        Long idOfSemester = getLongNum();
        return SEMESTER_SERVICE.findById(idOfSemester).get();
    }

    protected static City getCity(){
        System.out.println("""
                Choose birth city:
                1- TEHRAN
                2- EAST_AZARBAIJAN
                3- WEST_AZARBAIJAN
                4- ARDABIL
                5- ESFAHAN
                6- ALBORZ
                7- ILAM
                8- BUSHEHR
                9- CHAHARMAHAL_VA_BAKHTIARI
                10- SOUTHERN_KHORASAN
                11- KHORASAN_RAZAVI
                12- NORTH_KHORASAN
                13- KHUZESTAN
                14- ZANJAN
                15- SEMNAN
                16- SISTAN_VA_BALUCHESTAN
                17- FARS
                18- QAZVIN
                19- QOM
                20- KURDISTAN
                21- KERMAN
                22- KERMANSHAH
                23- KOHGILOYEH_VA_BOYERAHMAD
                24- GOLESTAN
                25- GUILAN
                26- LORESTAN
                27- MAZANDARAN
                28- CENTRAL
                29- HORMOZGAN
                30- HAMEDAN
                31- YAZD
                """);
        int select = Inputs.getIntegerNum();
        City city = null;

        switch (select) {
            case 1 -> city = City.TEHRAN;
            case 2 -> city = City.EAST_AZARBAIJAN;
            case 3 -> city = City.WEST_AZARBAIJAN;
            case 4 -> city = City.ARDABIL;
            case 5 -> city = City.ESFAHAN;
            case 6 -> city = City.ALBORZ;
            case 7 -> city = City.ILAM;
            case 8 -> city = City.BUSHEHR;
            case 9 -> city = City.CHAHARMAHAL_VA_BAKHTIARI;
            case 10 -> city = City.SOUTHERN_KHORASAN;
            case 11 -> city = City.KHORASAN_RAZAVI;
            case 12 -> city = City.NORTH_KHORASAN;
            case 13 -> city = City.KHUZESTAN;
            case 14 -> city = City.ZANJAN;
            case 15 -> city = City.SEMNAN;
            case 16 -> city = City.SISTAN_VA_BALUCHESTAN;
            case 17 -> city = City.FARS;
            case 18 -> city = City.QAZVIN;
            case 19 -> city = City.QOM;
            case 20 -> city = City.KURDISTAN;
            case 21 -> city = City.KERMAN;
            case 22 -> city = City.KERMANSHAH;
            case 23 -> city = City.KOHGILOYEH_VA_BOYERAHMAD;
            case 24 -> city = City.GOLESTAN;
            case 25 -> city = City.GUILAN;
            case 26 -> city = City.LORESTAN;
            case 27 -> city = City.MAZANDARAN;
            case 28 -> city = City.CENTRAL;
            case 29 -> city = City.HORMOZGAN;
            case 30 -> city = City.HAMEDAN;
            case 31 -> city = City.YAZD;
            default -> System.out.println("*** NO CITY ***");
        }
        return city;
    }

    private  static Gender getGender(){
        System.out.println("""
                Choose Your Sex:
                1-Male
                2-Female                
                """);

        int select = getIntegerNum();
        Gender gender = null;
        switch (select){
            case 1 -> gender = Gender.MALE;
            case 2 -> gender = Gender.FEMALE;
            default -> System.out.println("You Are A Gay");
        }

        return gender;
    }
    private  Section getSection(){
        System.out.println("""
                Choose Section:
                1-ASSOCIATE
                2-BACHELOR
                3-NON_CONTIGUOUS_BACHELOR
                4-MASTER
                5-NON_CONTIGUOUS_MASTER
                6-DOCTOR
                7-PROFESSIONAL_DOCTOR
                8-NON_CONTINUOUS_SPECIALIZED_DOCTOR
                """);
        int select = Inputs.getIntegerNum();
        Section section = null;
        switch (select) {
            case 1 -> section = Section.ASSOCIATE;
            case 2 -> section = Section.BACHELOR;
            case 3 -> section = Section.NON_CONTIGUOUS_BACHELOR;
            case 4 -> section = Section.MASTER;
            case 5 -> section = Section.NON_CONTIGUOUS_MASTER;
            case 6 -> section = Section.DOCTOR;
            case 7 -> section = Section.PROFESSIONAL_DOCTOR;
            case 8 -> section = Section.NON_CONTINUOUS_SPECIALIZED_DOCTOR;
            default -> System.out.println("*** Not Found ***");
        }
        return section;
    }

    private  MaritalStatus getMaritalStatus(){
        System.out.println("""
                Your Marital Status
                1-SINGLE
                2-MARRIED
                """);
        int select = getIntegerNum();
        MaritalStatus maritalStatus = null;
        switch (select){
            case 1 -> maritalStatus = MaritalStatus.SINGLE;
            case 2 -> maritalStatus = MaritalStatus.MARRIED;
        }

        return maritalStatus;
    }

    private  boolean dormitoryUserOrNot(){
        System.out.println("""
                Are You Use Dormitory?
                1-Yes
                2-No
                """);

        int select = getIntegerNum();
        Boolean y_OR_N = null;
        switch (select){
            case 1 -> y_OR_N = true;
            case 2 -> y_OR_N = false;
        }

        return y_OR_N;
    }
}
