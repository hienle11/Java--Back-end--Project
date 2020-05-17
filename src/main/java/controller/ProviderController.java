package controller;

import entity.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.GenericService;

@RestController
@RequestMapping(path = "/providers")
public class ProviderController extends AbstractCRUDController<Provider, Long>{

    @Autowired
    @Qualifier("providerServiceImpl")
    private GenericService providerService;

    @Override
    protected GenericService getService() {
        return providerService;
    }
}
