package com.test2.cars.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class EntityDataNonExistentException extends EntityNotFoundException {
    public EntityDataNonExistentException(Class<?> entity, Integer id) {
        super(String.format("No class %s entity with id %d exists!", entity, id));
    }
}
