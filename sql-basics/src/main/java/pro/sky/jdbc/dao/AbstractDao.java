package pro.sky.jdbc.dao;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pro.sky.jdbc.exception.DaoException;

import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractDao<T> implements Dao<T> {
    private final SessionFactory sessionFactory;
    @Setter
    private Class<T> clazz;

    @Override
    public void create(T entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException("Не удалось сохранить сущность в базу данных: " + e.getMessage());
        }
    }

    @Override
    public T findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(clazz, id);
        } catch (Exception e) {
            throw new DaoException("Ошибка при поиске в базе данных: " + e.getMessage());
        }
    }

    @Override
    public List<T> findAll() {
        try (Session session = sessionFactory.openSession()) {
            String query = "FROM " + clazz.getSimpleName().toLowerCase();
            return session.createQuery(query).list();
        } catch (Exception e) {
            throw new DaoException("Ошибка при поиске в базе данных: " + e.getMessage());
        }
    }

    @Override
    public void delete(T entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException("Не удалось удалить сущность из базы данных: " + e.getMessage());
        }
    }

    @Override
    public void update(T entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException("Не удалось изменить сущность в базе данных: " + e.getMessage());
        }
    }
}
