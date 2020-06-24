package com.test2.cars.controllers;

import com.test2.cars.entities.CarEntity;
import com.test2.cars.exceptions.UploadProcessingException;
import com.test2.cars.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarController {
    @Autowired
    private CarService service;

    @GetMapping
    public List<CarEntity> index() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public CarEntity show(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarEntity store(@Valid @RequestBody CarEntity car) {
        return service.store(car);
    }

    @PostMapping("/{id}")
    public CarEntity upload(
            @PathVariable Integer id,
            @RequestParam("image") MultipartFile image
    ) throws UploadProcessingException {
        return service.upload(id, image);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
