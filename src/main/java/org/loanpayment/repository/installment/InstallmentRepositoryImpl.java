package org.loanpayment.repository.installment;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.loanpayment.base.repository.BaseRepositoryImpl;
import org.loanpayment.model.Installment;
import org.loanpayment.model.Loan;
import org.loanpayment.model.enums.InstallmentStatus;

import java.util.List;

public class InstallmentRepositoryImpl extends BaseRepositoryImpl<Installment,Long> implements InstallmentRepository {
    public InstallmentRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Installment> getEntityClass() {
        return Installment.class;
    }

    @Override
    public String getEntity() {
        return "Installment";
    }

    @Override
    public List<Installment> findPaidInstallmentByLoan(Loan loan, InstallmentStatus installmentStatus) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM %s WHERE loan=:loan AND installmentStatus=:installmentStatus";
        Query<Installment> query = session.createQuery(String.format(hql,getEntity()), getEntityClass());
        query.setParameter("loan",loan);
        query.setParameter("installmentStatus",installmentStatus);
        return query.getResultList();
    }
}
