package dao;

import entity.Staff;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("staffDAO")
public class StaffDAO extends AbstractHibernateDAO<Staff, Long> {
}
