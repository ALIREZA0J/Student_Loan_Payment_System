package org.loanpayment.service.bankcard;

import org.loanpayment.base.service.BaseService;
import org.loanpayment.model.BankCard;
import org.loanpayment.model.Spouse;
import org.loanpayment.model.Student;

import java.util.Optional;

public interface BankCardService extends BaseService<BankCard,Long> {

    Optional<BankCard> findByStudent(Student student);
}
