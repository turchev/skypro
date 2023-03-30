package pro.sky.jdbc.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import pro.sky.jdbc.entity.Employee;

@Repository
public class EmployeeDao extends AbstractDao<Employee> {

    public EmployeeDao(SessionFactory sessionFactory) {
        super(sessionFactory);
        setClazz(Employee.class);
    }
}
