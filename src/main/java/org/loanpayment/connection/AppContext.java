package org.loanpayment.connection;

import org.hibernate.SessionFactory;
import org.loanpayment.repository.bankcard.BankCardRepository;
import org.loanpayment.repository.bankcard.BankCardRepositoryImpl;
import org.loanpayment.repository.installment.InstallmentRepository;
import org.loanpayment.repository.installment.InstallmentRepositoryImpl;
import org.loanpayment.repository.loan.LoanRepository;
import org.loanpayment.repository.loan.LoanRepositoryImpl;
import org.loanpayment.repository.rent_house.RentHouseRepository;
import org.loanpayment.repository.rent_house.RentHouseRepositoryImpl;
import org.loanpayment.repository.semester.SemesterRepository;
import org.loanpayment.repository.semester.SemesterRepositoryImpl;
import org.loanpayment.repository.spouse.SpouseRepository;
import org.loanpayment.repository.spouse.SpouseRepositoryImpl;
import org.loanpayment.repository.student.StudentRepository;
import org.loanpayment.repository.student.StudentRepositoryImpl;
import org.loanpayment.repository.university.UniversityRepository;
import org.loanpayment.repository.university.UniversityRepositoryImpl;
import org.loanpayment.service.bankcard.BankCardService;
import org.loanpayment.service.bankcard.BankCardServiceImpl;
import org.loanpayment.service.installment.InstallmentService;
import org.loanpayment.service.installment.InstallmentServiceImpl;
import org.loanpayment.service.loan.LoanService;
import org.loanpayment.service.loan.LoanServiceImpl;
import org.loanpayment.service.rent_house.RentHouseService;
import org.loanpayment.service.rent_house.RentHouseServiceImpl;
import org.loanpayment.service.semester.SemesterService;
import org.loanpayment.service.semester.SemesterServiceImpl;
import org.loanpayment.service.spouse.SpouseService;
import org.loanpayment.service.spouse.SpouseServiceImpl;
import org.loanpayment.service.student.StudentService;
import org.loanpayment.service.student.StudentServiceImpl;
import org.loanpayment.service.university.UniversityService;
import org.loanpayment.service.university.UniversityServiceImpl;

public class AppContext {


    private static final SessionFactory SESSION_FACTORY;

    private static final BankCardRepository BANK_CARD_REPOSITORY;
    private static final InstallmentRepository INSTALLMENT_REPOSITORY;
    private static final LoanRepository LOAN_REPOSITORY;
    private static final RentHouseRepository RENT_HOUSE_REPOSITORY;
    private static final SemesterRepository SEMESTER_REPOSITORY;
    private static final SpouseRepository SPOUSE_REPOSITORY;
    private static final StudentRepository STUDENT_REPOSITORY;
    private static final UniversityRepository UNIVERSITY_REPOSITORY;

    private static final BankCardService BANK_CARD_SERVICE;
    private static final InstallmentService INSTALLMENT_SERVICE;
    private static final LoanService LOAN_SERVICE;
    private static final RentHouseService RENT_HOUSE_SERVICE;
    private static final SemesterService SEMESTER_SERVICE;
    private static final SpouseService SPOUSE_SERVICE;
    private static final StudentService STUDENT_SERVICE;
    private static final UniversityService UNIVERSITY_SERVICE;


    static {
        SESSION_FACTORY = SessionFactorySingleton.getInstance();

        BANK_CARD_REPOSITORY = new BankCardRepositoryImpl(SESSION_FACTORY);
        INSTALLMENT_REPOSITORY = new InstallmentRepositoryImpl(SESSION_FACTORY);
        LOAN_REPOSITORY = new LoanRepositoryImpl(SESSION_FACTORY);
        RENT_HOUSE_REPOSITORY = new RentHouseRepositoryImpl(SESSION_FACTORY);
        SEMESTER_REPOSITORY = new SemesterRepositoryImpl(SESSION_FACTORY);
        SPOUSE_REPOSITORY = new SpouseRepositoryImpl(SESSION_FACTORY);
        STUDENT_REPOSITORY = new StudentRepositoryImpl(SESSION_FACTORY);
        UNIVERSITY_REPOSITORY = new UniversityRepositoryImpl(SESSION_FACTORY);

        BANK_CARD_SERVICE = new BankCardServiceImpl(BANK_CARD_REPOSITORY,SESSION_FACTORY);
        INSTALLMENT_SERVICE = new InstallmentServiceImpl(INSTALLMENT_REPOSITORY,SESSION_FACTORY);
        LOAN_SERVICE = new LoanServiceImpl(LOAN_REPOSITORY,SESSION_FACTORY);
        RENT_HOUSE_SERVICE = new RentHouseServiceImpl(RENT_HOUSE_REPOSITORY,SESSION_FACTORY);
        SEMESTER_SERVICE = new SemesterServiceImpl(SEMESTER_REPOSITORY,SESSION_FACTORY);
        SPOUSE_SERVICE = new SpouseServiceImpl(SPOUSE_REPOSITORY,SESSION_FACTORY);
        STUDENT_SERVICE = new StudentServiceImpl(STUDENT_REPOSITORY,SESSION_FACTORY);
        UNIVERSITY_SERVICE = new UniversityServiceImpl(UNIVERSITY_REPOSITORY,SESSION_FACTORY);
    }


    public static BankCardService getBankCardService() {
        return BANK_CARD_SERVICE;
    }

    public static InstallmentService getInstallmentService() {
        return INSTALLMENT_SERVICE;
    }

    public static LoanService getLoanService() {
        return LOAN_SERVICE;
    }

    public static RentHouseService getRentHouseService() {
        return RENT_HOUSE_SERVICE;
    }

    public static SemesterService getSemesterService() {
        return SEMESTER_SERVICE;
    }

    public static SpouseService getSpouseService() {
        return SPOUSE_SERVICE;
    }

    public static StudentService getStudentService() {
        return STUDENT_SERVICE;
    }

    public static UniversityService getUniversityService() {
        return UNIVERSITY_SERVICE;
    }

}
