package pro.sky.jdbc.dao;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import pro.sky.jdbc.entity.Employee;
import pro.sky.jdbc.exception.DaoException;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmployeeDaoImpl implements EmployeeDao {

    private final SessionFactory sessionFactory;

    @Override
    public void create(Employee employee) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException("Не удалось сохранить сущность в базу данных: " + e.getMessage());
        }
    }

    @Override
    public Employee findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Employee.class, id);
        } catch (Exception e) {
            throw new DaoException("Ошибка при поиске в базе данных: " + e.getMessage());
        }
    }

    @Override
    public List<Employee> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Employee").list();
        } catch (Exception e) {
            throw new DaoException("Ошибка при поиске в базе данных: " + e.getMessage());
        }
    }

    @Override
    public void update(Employee employee) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException("Не удалось изменить сущность в базе данных: " + e.getMessage());
        }
    }

    @Override
    public void delete(Employee employee) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException("Не удалось удалить сущность из базы данных: " + e.getMessage());
        }
    }
}
