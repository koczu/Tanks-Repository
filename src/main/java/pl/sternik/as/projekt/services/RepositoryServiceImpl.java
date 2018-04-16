package pl.sternik.as.projekt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.sternik.as.projekt.entities.Status;
import pl.sternik.as.projekt.entities.Tank;
import pl.sternik.as.projekt.repositories.NoSuchTankException;
import pl.sternik.as.projekt.repositories.TankAlreadyExistsException;
import pl.sternik.as.projekt.repositories.TankRepository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Qualifier("spring-data")
public class RepositoryServiceImpl implements RepositoryService {

    @Autowired
    private TankRepository dataBase;

    @Override
    public List<Tank> findAll() {
        return dataBase.findAll();
    }

    @Override
    public List<Tank> findAllInMaintenance() {

        return dataBase.findAll().stream().filter(t -> Objects.equals(t.getStatus(), Status.IN_MAINTENANCE))
                .collect(Collectors.toList());
    }

    @Override
    public List<Tank> findAllDuplicates() {
        return dataBase.findAll().stream().filter(t -> Objects.equals(t.getStatus(), Status.DUPLICATE))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Tank> findById(Long id) {
        try {
            return Optional.of(dataBase.readById(id));
        } catch (NoSuchTankException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Tank> create(Tank tank) {
        try {
            return Optional.of(dataBase.create(tank));
        } catch (TankAlreadyExistsException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Tank> edit(Tank tank) {
        try {
            return Optional.of(dataBase.update(tank));
        } catch (NoSuchTankException e) {
            return Optional.empty();
        }

    }

    @Override
    public Optional<Boolean> deleteById(Long id) {
        try {
            dataBase.deleteById(id);
            return Optional.of(Boolean.TRUE);
        } catch (NoSuchTankException e) {
            return Optional.of(Boolean.FALSE);
        }
    }

    @Override
    public List<Tank> findLatest3() {
        //TODO:
        return Collections.emptyList();
    }


}
