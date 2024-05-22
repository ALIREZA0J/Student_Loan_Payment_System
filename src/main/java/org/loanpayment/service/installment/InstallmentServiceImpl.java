package org.loanpayment.service.installment;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.loanpayment.base.service.BaseServiceImpl;
import org.loanpayment.model.Installment;
import org.loanpayment.model.Loan;
import org.loanpayment.model.enums.InstallmentStatus;
import org.loanpayment.repository.installment.InstallmentRepository;

import java.util.List;

@Slf4j
public class InstallmentServiceImpl extends BaseServiceImpl<Installment,Long, InstallmentRepository>
        implements InstallmentService {

    public InstallmentServiceImpl(InstallmentRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }

    @Override
    public List<Installment> findByLoan(Loan loan, InstallmentStatus installmentStatus) {
        log.info("installment findByLoan");
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<Installment> paidInstallment = repository.findPaidInstallmentByLoan(loan, installmentStatus);
            session.getTransaction().commit();
            session.close();
            return paidInstallment;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
