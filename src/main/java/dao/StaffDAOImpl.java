package dao;

import entity.Staff;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class StaffDAOImpl extends AbstractHibernateDAO<Staff, Long> {
}
