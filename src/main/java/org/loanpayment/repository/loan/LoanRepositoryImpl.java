package org.loanpayment.repository.loan;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.loanpayment.base.repository.BaseRepositoryImpl;
import org.loanpayment.model.Loan;
import org.loanpayment.model.Semester;
import org.loanpayment.model.Student;
import org.loanpayment.model.enums.LoanType;
import org.loanpayment.model.enums.Section;

import java.util.List;
import java.util.Optional;

public class LoanRepositoryImpl extends BaseRepositoryImpl<Loan,Long> implements LoanRepository{
    public LoanRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Loan> getEntityClass() {
        return Loan.class;
    }

    @Override
    public String getEntity() {
        return "Loan";
    }

    @Override
    public Optional<Loan> findByStudentAndSemesterAndLoanType(Student student, Semester semester, LoanType loanType) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM %s WHERE student=:student AND semester=:semester AND loanType=:loanType";
        Query<Loan> query = session.createQuery(String.format(hql,getEntity()), getEntityClass());
        query.setParameter("student",student);
        query.setParameter("semester",semester);
        query.setParameter("loanType",loanType);
        return query.getResultList()
                .stream().findFirst();
    }

    @Override
    public Optional<Loan> findByStudentAndSectionAndLoanType(Student student, Section loanSection, LoanType loanType) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM %s WHERE student=:student AND loanSection=:loanSection AND loanType=:loanType";
        Query<Loan> query = session.createQuery(String.format(hql,getEntity()), getEntityClass());
        query.setParameter("student",student);
        query.setParameter("loanSection",loanSection);
        query.setParameter("loanType",loanType);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public List<Loan> findByStudentAndLoanType(Student student, LoanType loanType) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM %s WHERE student=:student AND loanType=:loanType";
        Query<Loan> query = session.createQuery(String.format(hql,getEntity()), getEntityClass());
        query.setParameter("student",student);
        query.setParameter("loanType",loanType);
        return query.getResultList();
    }

    @Override
    public List<Loan> findByStudent(Student student) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM %s WHERE student=:student ";
        Query<Loan> query = session.createQuery(String.format(hql,getEntity()), getEntityClass());
        query.setParameter("student",student);
        return query.getResultList();
    }
}
