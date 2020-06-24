package com.test2.cars.services;

import com.test2.cars.entities.CarEntity;
import com.test2.cars.exceptions.EntityDataNonExistentException;
import com.test2.cars.exceptions.UploadProcessingException;
import com.test2.cars.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    private CarRepository repo;

    @Autowired
    private UploadService uploadService;

    public List<CarEntity> findAll() {
        return repo.findAll();
    }

    public CarEntity findById(Integer id) {
        Optional<CarEntity> optCar = repo.findById(id);

        if (optCar.isEmpty()) {
            throwEntityNotFound(id);
        }

        return optCar.get();
    }

    public CarEntity store(CarEntity car) {
        return repo.save(car);
    }

    public void delete(Integer id) {
        try {
            repo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            // keep default behaviour
            throwEntityNotFound(id);
        }
    }

    public CarEntity upload(
            Integer id,
            MultipartFile image
    ) throws UploadProcessingException {
        CarEntity car = findById(id);

        String imageUrl = uploadService.store(image);
        car.setUrlFoto(imageUrl);

        return store(car); // actually this will update our car
    }

    protected void throwEntityNotFound(Integer id) {
        throw new EntityDataNonExistentException(CarEntity.class, id);
    }
}
