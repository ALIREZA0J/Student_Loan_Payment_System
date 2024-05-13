package org.loanpayment.service.student;

import org.loanpayment.base.service.BaseService;
import org.loanpayment.model.Student;

import java.util.Optional;

public interface StudentService extends BaseService<Student,Long> {

    Optional<Student> findByStudentCode(Long studentCode);

    Optional<Student> findByNationalCode(String nationalCode);

    Student registerStudent (Student student);

    Optional<Student> findByUsernameAndPassword(String username,String password);
}
