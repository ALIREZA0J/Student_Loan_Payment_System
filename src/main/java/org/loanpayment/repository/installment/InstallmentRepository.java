package org.loanpayment.repository.installment;

import org.loanpayment.base.repository.BaseRepository;
import org.loanpayment.model.Installment;
import org.loanpayment.model.Loan;
import org.loanpayment.model.enums.InstallmentStatus;

import java.util.List;

public interface InstallmentRepository extends BaseRepository<Installment,Long> {


    List<Installment> findPaidInstallmentByLoan(Loan loan, InstallmentStatus installmentStatus);
}
