package org.loanpayment.repository.semester;

import org.hibernate.SessionFactory;
import org.loanpayment.base.repository.BaseRepositoryImpl;
import org.loanpayment.model.Semester;

public class SemesterRepositoryImpl extends BaseRepositoryImpl<Semester,Long> implements SemesterRepository {
    public SemesterRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Semester> getEntityClass() {
        return Semester.class;
    }

    @Override
    public String getEntity() {
        return "Semester";
    }
}
