package org.loanpayment.service.student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.loanpayment.base.service.BaseServiceImpl;
import org.loanpayment.exception.StudentException;
import org.loanpayment.model.Student;
import org.loanpayment.repository.student.StudentRepository;
import org.loanpayment.validation.NationalCodeValidation;

import java.util.Optional;

public class StudentServiceImpl extends BaseServiceImpl<Student,Long, StudentRepository> implements StudentService{
    public StudentServiceImpl(StudentRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }

    @Override
    public Optional<Student> findByStudentCode(Long studentCode) {
        try (Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();
            Optional<Student> student = repository.findByStudentCode(studentCode);
            student.orElseThrow(() -> new StudentException("Student not found"));
            session.getTransaction().commit();
            session.close();
            return student;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Student> findByNationalCode(String nationalCode) {
        try (Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();
            Optional<Student> student = repository.findByNationalCode(nationalCode);
            student.orElseThrow(() -> new StudentException("Student not found"));
            session.getTransaction().commit();
            session.close();
            return student;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Student registerStudent(Student student) {
        Transaction transaction = null ;
        try (Session session = sessionFactory.getCurrentSession()){
            transaction = session.beginTransaction();

            NationalCodeValidation.validateNationalCode(student.getNationalCode());


            Optional<Student> optionalStudent = repository.findByStudentCode(student.getStudentCode());
            if (optionalStudent.isPresent())
                throw new StudentException("Student Can't Save - This Student Code Is Already Exist");

            Student saveStudent = repository.saveOrUpdate(student);
            transaction.commit();
            session.close();
            return saveStudent;
        }
        catch (Exception e){
            assert transaction != null;
            System.out.println(e.getMessage());
            transaction.rollback();
            return null;
        }
    }

    @Override
    public Optional<Student> findByUsernameAndPassword(String username, String password) {
        try (Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();
            Optional<Student> student = repository.findByUsernameAndPassword(username, password);
            student.orElseThrow(() -> new StudentException("Student not found"));
            session.getTransaction().commit();
            session.close();
            return student;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }
}
