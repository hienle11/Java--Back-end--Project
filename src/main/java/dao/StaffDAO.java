package dao;

import entity.Staff;
import org.springframework.stereotype.Repository;

@Repository
public class StaffDAO
        extends AbstractHibernateDAO<Staff, Long>
        implements GenericDAO<Staff, Long> {
}
