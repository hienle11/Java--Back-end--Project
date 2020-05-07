package service;

import dao.StaffDAO;
import entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffService extends AbstractCRUDService<Staff, Long> implements GenericService<Staff, Long>{

    @Autowired
    StaffService(StaffDAO dao) {
        super(dao);
    }
}
