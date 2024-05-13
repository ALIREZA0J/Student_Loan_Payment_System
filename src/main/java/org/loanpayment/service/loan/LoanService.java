package org.loanpayment.service.loan;

import org.loanpayment.base.service.BaseService;
import org.loanpayment.model.Loan;
import org.loanpayment.model.Semester;
import org.loanpayment.model.Student;
import org.loanpayment.model.enums.LoanType;
import org.loanpayment.model.enums.Section;

import java.util.List;
import java.util.Optional;

public interface LoanService extends BaseService<Loan,Long> {


    Optional<Loan> findByStudentAndSemesterAndLoanType(Student student, Semester semester, LoanType loanType);
    Optional<Loan> findByStudentAndSectionAndLoanType(Student student, Section section, LoanType loanType);

    List<Loan> findByStudentAndLoanType(Student student, LoanType loanType);

    List<Loan> findByStudent(Student student);

}
