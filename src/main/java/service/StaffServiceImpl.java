package service;

import dao.GenericDAO;
import entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("staffService")
public class StaffServiceImpl extends AbstractCRUDService<Staff, Long> implements GenericService<Staff, Long> {

    @Autowired
    @Qualifier("staffDAOImpl")
    GenericDAO staffDAO;

    @Override
    protected GenericDAO getDao() {
        return staffDAO;
    }
}
