package pro.sky.hibernate.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import pro.sky.hibernate.entity.User;
import pro.sky.hibernate.exception.DaoException;

@Repository
public class UserDao extends AbstractDao<User> {

    public UserDao(SessionFactory sessionFactory) {
        super(sessionFactory);
        setClazz(User.class);
    }

    public User findByIdWithRoles(Long id) {
        try (Session session = sessionFactory.openSession()) {
            User user = session.get(User.class, id);
            Hibernate.initialize(user.getRoles());
            return user;
        } catch (Exception e) {
            throw new DaoException("Ошибка при поиске в базе данных: " + e.getMessage());
        }
    }
}
