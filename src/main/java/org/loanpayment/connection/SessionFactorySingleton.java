package org.loanpayment.connection;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.loanpayment.model.*;


public class SessionFactorySingleton {

    private SessionFactorySingleton() {
    }

    private static class SessionFactoryHelper {
        static org.hibernate.service.ServiceRegistry registry =
                new StandardServiceRegistryBuilder()
                        .configure()
                        .build();
        private static final SessionFactory INSTANCE =
                new MetadataSources(registry)
                        .addAnnotatedClass(BankCard.class)
                        .addAnnotatedClass(Installment.class)
                        .addAnnotatedClass(Loan.class)
                        .addAnnotatedClass(RentHouse.class)
                        .addAnnotatedClass(Semester.class)
                        .addAnnotatedClass(Spouse.class)
                        .addAnnotatedClass(Student.class)
                        .addAnnotatedClass(University.class)
                        .buildMetadata()
                        .buildSessionFactory();
    }

    public static SessionFactory getInstance() {
        return SessionFactoryHelper.INSTANCE;
    }
}