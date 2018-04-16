package pl.sternik.as.projekt.services;

import pl.sternik.as.projekt.entities.Tank;

import java.util.List;
import java.util.Optional;


public interface RepositoryService {
    List<Tank> findAll();

    List<Tank> findAllInMaintenance();

    List<Tank> findAllDuplicates();


    Optional<Tank> findById(Long id);

    Optional<Tank> create(Tank tank);

    Optional<Tank> edit(Tank tank);

    Optional<Boolean> deleteById(Long id);

    List<Tank> findLatest3();
}