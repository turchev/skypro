package pro.sky.hibernate.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import pro.sky.hibernate.entity.Role;
import pro.sky.hibernate.entity.User;
import pro.sky.hibernate.exception.DaoException;

import java.util.Set;

@Repository
public class RoleDao extends AbstractDao<Role> {

    public RoleDao(SessionFactory sessionFactory) {
        super(sessionFactory);
        setClazz(Role.class);
    }

    public Set<User> getUsersByRoleName(String roleName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Role> query = session.createQuery("FROM app_role WHERE name = :roleName", Role.class);
            query.setParameter("roleName", roleName);
            Hibernate.initialize(query.getSingleResult().getUsers());
            return query.getSingleResult().getUsers();
        } catch (Exception e) {
            throw new DaoException("Ошибка при поиске в базе данных: " + e.getMessage());
        }
    }
}
