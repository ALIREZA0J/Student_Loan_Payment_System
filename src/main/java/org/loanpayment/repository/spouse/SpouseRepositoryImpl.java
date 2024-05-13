package org.loanpayment.repository.spouse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.loanpayment.base.repository.BaseRepositoryImpl;
import org.loanpayment.model.Loan;
import org.loanpayment.model.Spouse;
import org.loanpayment.model.Student;

import java.util.Optional;

public class SpouseRepositoryImpl extends BaseRepositoryImpl<Spouse,Long> implements SpouseRepository {
    public SpouseRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Spouse> getEntityClass() {
        return Spouse.class;
    }

    @Override
    public String getEntity() {
        return "Spouse";
    }

    @Override
    public Optional<Spouse> findByStudent(Student student) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM %s WHERE student=:student";
        Query<Spouse> query = session.createQuery(String.format(hql,getEntity()), getEntityClass());
        query.setParameter("student",student);
        return query.getResultList().stream().findFirst();
    }

    @Override
    public Optional<Spouse> findByNationalCode(String nationalCode) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM %s WHERE nationalCode=:nationalCode";
        Query<Spouse> query = session.createQuery(String.format(hql,getEntity()), getEntityClass());
        query.setParameter("nationalCode",nationalCode);
        return query.getResultList().stream().findFirst();
    }
}
