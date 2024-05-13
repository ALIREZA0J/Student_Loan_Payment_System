package org.loanpayment.repository.bankcard;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.loanpayment.base.repository.BaseRepositoryImpl;
import org.loanpayment.model.BankCard;
import org.loanpayment.model.Student;

import java.util.Optional;

public class BankCardRepositoryImpl extends BaseRepositoryImpl<BankCard,Long> implements BankCardRepository {
    public BankCardRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<BankCard> getEntityClass() {
        return BankCard.class;
    }

    @Override
    public String getEntity() {
        return "BankCard";
    }

    @Override
    public Optional<BankCard> findByStudent(Student student) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM %s WHERE student=:student";
        Query<BankCard> query = session.createQuery(String.format(hql,getEntity()), getEntityClass());
        query.setParameter("student",student);
        return Optional.ofNullable(query.getSingleResult());
    }
}
