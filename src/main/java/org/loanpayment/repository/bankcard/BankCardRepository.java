package org.loanpayment.repository.bankcard;

import org.loanpayment.base.repository.BaseRepository;
import org.loanpayment.model.BankCard;
import org.loanpayment.model.Spouse;
import org.loanpayment.model.Student;

import java.util.Optional;

public interface BankCardRepository extends BaseRepository<BankCard,Long> {

    Optional<BankCard> findByStudent(Student student);
}
