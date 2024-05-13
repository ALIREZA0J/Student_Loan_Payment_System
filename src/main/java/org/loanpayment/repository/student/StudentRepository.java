package org.loanpayment.repository.student;

import org.loanpayment.base.repository.BaseRepository;
import org.loanpayment.model.Student;

import java.util.Optional;


public interface StudentRepository extends BaseRepository<Student, Long> {

    Optional<Student> findByStudentCode(Long studentCode);
    Optional<Student> findByNationalCode(String nationalCode);
    Optional<Student> findByUsernameAndPassword(String username,String password);
}
