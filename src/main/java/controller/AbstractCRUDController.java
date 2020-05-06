package controller;

import entity.AbstractEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import service.AbstractCRUDService;
import service.GenericService;

public abstract class AbstractCRUDController <Entity extends AbstractEntity, ID> {

    private AbstractCRUDService service;

    AbstractCRUDController(AbstractCRUDService service) {
        this.service = service;
    }

    @PostMapping
    public Entity createAProduct(@RequestBody Entity product) {
        return (Entity) service.create(product);
    }
}
