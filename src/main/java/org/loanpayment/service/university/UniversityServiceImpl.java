package org.loanpayment.service.university;

import org.hibernate.SessionFactory;
import org.loanpayment.base.service.BaseServiceImpl;
import org.loanpayment.model.University;
import org.loanpayment.repository.university.UniversityRepository;

public class UniversityServiceImpl extends BaseServiceImpl<University,Long, UniversityRepository>
        implements UniversityService {
    public UniversityServiceImpl(UniversityRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }
}
