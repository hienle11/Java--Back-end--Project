package service;

import dao.GenericDAO;
import dao.StaffDAO;
import entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("staffService")
public class StaffService extends AbstractCRUDService<Staff, Long> implements GenericService<Staff, Long>{

    @Autowired
    @Qualifier("staffDAO")
    GenericDAO staffDAO;

    @Override
    protected GenericDAO getDao() {
        return staffDAO;
    }
}
