package org.loanpayment.service.rent_house;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.loanpayment.base.service.BaseServiceImpl;
import org.loanpayment.model.RentHouse;
import org.loanpayment.model.Student;
import org.loanpayment.repository.rent_house.RentHouseRepository;

import java.util.Optional;
@Slf4j
public class RentHouseServiceImpl extends BaseServiceImpl<RentHouse,Long, RentHouseRepository> implements RentHouseService{
    public RentHouseServiceImpl(RentHouseRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }

    @Override
    public Optional<RentHouse> findByStudent(Student student) {
        log.info("rent house findByStudent");
        try (Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();
            Optional<RentHouse> rentHouse = repository.findByStudent(student);
            session.getTransaction().commit();
            session.close();
            return rentHouse;
        }
        catch (Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
