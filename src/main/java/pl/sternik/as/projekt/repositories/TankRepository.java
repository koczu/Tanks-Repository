package pl.sternik.as.projekt.repositories;

import pl.sternik.as.projekt.entities.Tank;

import java.util.List;


public interface TankRepository {
    Tank create(Tank tank) throws TankAlreadyExistsException;

    Tank readById(Long id) throws NoSuchTankException;

    Tank update(Tank tank) throws NoSuchTankException;

    void deleteById(Long id) throws NoSuchTankException;

    List<Tank> findAll();
}