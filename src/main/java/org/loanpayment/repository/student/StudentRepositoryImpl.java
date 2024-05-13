package org.loanpayment.repository.student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.loanpayment.base.repository.BaseRepositoryImpl;
import org.loanpayment.model.Student;

import java.util.Optional;

public class StudentRepositoryImpl extends BaseRepositoryImpl<Student,Long> implements StudentRepository {
    public StudentRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Student> getEntityClass() {
        return Student.class;
    }

    @Override
    public String getEntity() {
        return "Student";
    }

    @Override
    public Optional<Student> findByStudentCode(Long studentCode) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM %s WHERE studentCode=:studentCode";
        Query<Student> query = session.createQuery(String.format(hql,getEntity()), getEntityClass());
        query.setParameter("studentCode",studentCode);
        return query.getResultList()
                .stream().findFirst();

    }

    @Override
    public Optional<Student> findByNationalCode(String nationalCode) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM %s WHERE nationalCode=:nationalCode";
        Query<Student> query = session.createQuery(String.format(hql,getEntity()), getEntityClass());
        query.setParameter("nationalCode",nationalCode);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public Optional<Student> findByUsernameAndPassword(String username, String password) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM %s WHERE username=:username AND password=:password";
        Query<Student> query = session.createQuery(String.format(hql,getEntity()), getEntityClass());
        query.setParameter("username",username);
        query.setParameter("password",password);
        return query.getResultList()
                .stream().findFirst();
    }
}
