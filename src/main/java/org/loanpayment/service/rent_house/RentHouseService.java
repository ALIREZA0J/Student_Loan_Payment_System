package org.loanpayment.service.rent_house;

import org.loanpayment.base.service.BaseService;
import org.loanpayment.model.RentHouse;
import org.loanpayment.model.Student;

import java.util.Optional;

public interface RentHouseService extends BaseService<RentHouse,Long> {

    Optional<RentHouse> findByStudent(Student student);
}
