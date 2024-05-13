package org.loanpayment.repository.rent_house;

import org.loanpayment.base.repository.BaseRepository;
import org.loanpayment.model.RentHouse;
import org.loanpayment.model.Student;

import java.util.Optional;

public interface RentHouseRepository extends BaseRepository<RentHouse,Long> {

    Optional<RentHouse> findByStudent(Student student);
}
