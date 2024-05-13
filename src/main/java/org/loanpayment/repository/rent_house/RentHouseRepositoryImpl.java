package org.loanpayment.repository.rent_house;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.loanpayment.base.repository.BaseRepositoryImpl;
import org.loanpayment.model.Loan;
import org.loanpayment.model.RentHouse;
import org.loanpayment.model.Student;

import java.util.Optional;

public class RentHouseRepositoryImpl extends BaseRepositoryImpl<RentHouse,Long> implements RentHouseRepository {
    public RentHouseRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<RentHouse> getEntityClass() {
        return RentHouse.class;
    }

    @Override
    public String getEntity() {
        return "RentHouse";
    }

    @Override
    public Optional<RentHouse> findByStudent(Student student) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM %s WHERE student=:student";
        Query<RentHouse> query = session.createQuery(String.format(hql,getEntity()), getEntityClass());
        query.setParameter("student",student);
        return query.getResultList()
                .stream().findFirst();
    }
}
