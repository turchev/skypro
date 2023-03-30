package pro.sky.jdbc.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import pro.sky.jdbc.entity.City;

@Repository
public class CityDao extends AbstractDao<City> {

    public CityDao(SessionFactory sessionFactory) {
        super(sessionFactory);
        setClazz(City.class);
    }
}
