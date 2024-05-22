package org.loanpayment.service.loan;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.loanpayment.base.service.BaseServiceImpl;
import org.loanpayment.exception.LoanException;
import org.loanpayment.model.Loan;
import org.loanpayment.model.Semester;
import org.loanpayment.model.Student;
import org.loanpayment.model.enums.LoanType;
import org.loanpayment.model.enums.Section;
import org.loanpayment.repository.loan.LoanRepository;

import java.util.List;
import java.util.Optional;
@Slf4j
public class LoanServiceImpl extends BaseServiceImpl<Loan,Long, LoanRepository> implements LoanService {
    public LoanServiceImpl(LoanRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }

    @Override
    public Optional<Loan> findByStudentAndSemesterAndLoanType(Student student, Semester semester, LoanType loanType) {
        log.info("Loan findByStudentAndSemesterAndLoanType ");
        try (Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();
            Optional<Loan> loan = repository.findByStudentAndSemesterAndLoanType(student, semester, loanType);
            session.getTransaction().commit();
            session.close();
            return loan;
        }
        catch (Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Loan> findByStudentAndSectionAndLoanType(Student student, Section section, LoanType loanType) {
        log.info("loan findByStudentAndSectionAndLoanType");
        try (Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();
            Optional<Loan> loan = repository.findByStudentAndSectionAndLoanType(student, section, loanType);
            session.getTransaction().commit();
            session.close();
            return loan;
        }
        catch (Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<Loan> findByStudentAndLoanType(Student student, LoanType loanType) {
        log.info("loan findByStudentAndLoanType");
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<Loan> loanList = repository.findByStudentAndLoanType(student, loanType);
            session.getTransaction().commit();
            session.close();
            return loanList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Loan> findByStudent(Student student) {
        log.info("loan findByStudent");
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<Loan> loanList = repository.findByStudent(student);
            session.getTransaction().commit();
            session.close();
            return loanList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
