package org.loanpayment.repository.university;

import org.hibernate.SessionFactory;
import org.loanpayment.base.repository.BaseRepositoryImpl;
import org.loanpayment.model.University;

public class UniversityRepositoryImpl extends BaseRepositoryImpl<University,Long> implements UniversityRepository{
    public UniversityRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<University> getEntityClass() {
        return University.class;
    }

    @Override
    public String getEntity() {
        return "University";
    }
}
