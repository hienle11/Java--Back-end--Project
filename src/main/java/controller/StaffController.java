package controller;

import entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.StaffService;

@RestController
@RequestMapping(path = "/staffs")
public class StaffController extends AbstractCRUDController<Staff, Long>{

    @Autowired
    StaffController(StaffService service) {
        super(service);
    }
}
