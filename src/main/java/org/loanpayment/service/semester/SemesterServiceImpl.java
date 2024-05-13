package org.loanpayment.service.semester;

import org.hibernate.SessionFactory;
import org.loanpayment.base.service.BaseServiceImpl;
import org.loanpayment.model.Semester;
import org.loanpayment.repository.semester.SemesterRepository;

public class SemesterServiceImpl extends BaseServiceImpl<Semester,Long, SemesterRepository> implements SemesterService {
    public SemesterServiceImpl(SemesterRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }
}
