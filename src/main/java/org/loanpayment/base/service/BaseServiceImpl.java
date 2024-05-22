package org.loanpayment.base.service;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.loanpayment.base.entity.BaseEntity;
import org.loanpayment.base.repository.BaseRepository;
import org.loanpayment.exception.LoanPaymentException;


import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@Slf4j
public class BaseServiceImpl <T extends BaseEntity<ID>,
        ID extends Serializable ,
        R extends BaseRepository<T,ID>>
        implements BaseService<T , ID>{

    protected final R repository;
    protected final SessionFactory sessionFactory;


    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    Validator validator = validatorFactory.getValidator();

    @Override
    public boolean validate(T entity) {

        Set<ConstraintViolation<T>> violations = validator.validate(entity);
        if (violations.isEmpty())
            return true;
        else {
            System.out.println("Invalid user data found:");
            for (ConstraintViolation<T> violation : violations) {
                System.out.println(violation.getMessage());
            }
            return false;
        }
    }

    public BaseServiceImpl(R repository, SessionFactory sessionFactory) {
        this.repository = repository;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public T saveOrUpdate(T entity) {
        log.info("save entity");

        if (!validate(entity))
            return null;

        Transaction transaction = null ;
        try (Session session = sessionFactory.getCurrentSession()){
            transaction = session.beginTransaction();
            T t = repository.saveOrUpdate(entity);
            transaction.commit();
            session.close();
            return t;
        }
        catch (Exception e){
            assert transaction != null;
            transaction.rollback();
            return null;
        }
    }

    @Override
    public Optional<T> findById(ID id) {
        log.info("find entity by id");
        try (Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();
            Optional<T> findEntity = repository.findById(id);
            findEntity.orElseThrow(() -> new LoanPaymentException(String.format("Entity with id : %s not found", id)));
            session.getTransaction().commit();
            session.close();
            return findEntity;
        }
        catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public void deleteById(ID id) {
        try (Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();
            Optional<T> findEntity = repository.findById(id);
            findEntity.orElseThrow(() -> new LoanPaymentException(String.format("Entity with id : %s not found", id)));
            repository.delete(findEntity.get());
            session.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<T> findAll() {
        try (Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();
            List<T> listAll = repository.findAll();
            session.getTransaction().commit();
            session.close();
            return listAll;
        }
        catch (Exception ignored){
            return null;
        }
    }

//    @Override
//    public boolean isValid(T entity) {
//        Set<ConstraintViolation<T>> violations = validator.validate(entity);
//        if (!violations.isEmpty()) {
//            for (ConstraintViolation<T> p : violations)
//                System.out.println(p.getMessage());
//            return false;
//        }
//        return true;
//    }
}
