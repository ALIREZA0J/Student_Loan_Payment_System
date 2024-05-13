package org.loanpayment.service.installment;

import org.loanpayment.base.service.BaseService;
import org.loanpayment.model.Installment;
import org.loanpayment.model.Loan;
import org.loanpayment.model.enums.InstallmentStatus;

import java.util.List;

public interface InstallmentService extends BaseService<Installment,Long> {

    List<Installment> findByLoan(Loan loan, InstallmentStatus installmentStatus);
}
