package org.loanpayment.repository.spouse;

import org.loanpayment.base.repository.BaseRepository;
import org.loanpayment.model.Spouse;
import org.loanpayment.model.Student;

import java.util.Optional;

public interface SpouseRepository extends BaseRepository<Spouse,Long> {

    Optional<Spouse> findByStudent(Student student);

    Optional<Spouse> findByNationalCode(String nationalCode);
}
