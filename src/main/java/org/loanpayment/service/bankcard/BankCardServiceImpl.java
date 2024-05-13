package org.loanpayment.service.bankcard;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.loanpayment.base.service.BaseServiceImpl;
import org.loanpayment.exception.BankCardException;
import org.loanpayment.exception.SpouseException;
import org.loanpayment.model.BankCard;
import org.loanpayment.model.Student;
import org.loanpayment.repository.bankcard.BankCardRepository;

import java.util.Optional;

public class BankCardServiceImpl extends BaseServiceImpl<BankCard, Long, BankCardRepository> implements BankCardService{
    public BankCardServiceImpl(BankCardRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }

    @Override
    public Optional<BankCard> findByStudent(Student student) {
        try (Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();
            Optional<BankCard> bankCard = repository.findByStudent(student);
            bankCard.orElseThrow(() -> new BankCardException("Bank card not found"));
            session.getTransaction().commit();
            session.close();
            return bankCard;
        }
        catch (Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
