package org.loanpayment.service.spouse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.loanpayment.base.service.BaseServiceImpl;
import org.loanpayment.exception.LoanException;
import org.loanpayment.exception.SpouseException;
import org.loanpayment.model.Spouse;
import org.loanpayment.model.Student;
import org.loanpayment.repository.spouse.SpouseRepository;

import java.util.Optional;

public class SpouseServiceImpl extends BaseServiceImpl<Spouse,Long, SpouseRepository> implements SpouseService {
    public SpouseServiceImpl(SpouseRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }

    @Override
    public Optional<Spouse> findByStudent(Student student) {
        try (Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();
            Optional<Spouse> spouse = repository.findByStudent(student);
            session.getTransaction().commit();
            session.close();
            return spouse;
        }
        catch (Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Spouse> findByNationalCode(String nationalCode) {
        try (Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();
            Optional<Spouse> spouse = repository.findByNationalCode(nationalCode);
            session.getTransaction().commit();
            session.close();
            return spouse;
        }
        catch (Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
