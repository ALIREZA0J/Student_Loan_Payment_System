package org.loanpayment.service.spouse;

import org.loanpayment.base.service.BaseService;
import org.loanpayment.model.Spouse;
import org.loanpayment.model.Student;

import java.util.Optional;

public interface SpouseService extends BaseService<Spouse,Long> {

    Optional<Spouse> findByStudent(Student student);

    Optional<Spouse> findByNationalCode(String nationalCode);

}
