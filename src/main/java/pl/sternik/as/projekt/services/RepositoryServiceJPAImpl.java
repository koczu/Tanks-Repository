package pl.sternik.as.projekt.services;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.sternik.as.projekt.entities.Tank;
import pl.sternik.as.projekt.repositories.springdata.TankRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Qualifier("spring-data")
public class RepositoryServiceJPAImpl implements RepositoryService {

    @Autowired
    private TankRepository dataBase;


    @Override
    public List<Tank> findAll() {
        List<Tank> l = new ArrayList<>();
        for (Tank item : dataBase.findAll()) {
            l.add(item);
        }
        return l;
    }

    @Override
    public List<Tank> findAllInMaintenance() {
        List<Tank> l = new ArrayList<>();
        for (Tank item : dataBase.findAll()) {
            l.add(item);
        }
        return l;
    }

    @Override
    public List<Tank> findAllDuplicates() {
        List<Tank> l = new ArrayList<>();
        for (Tank item : dataBase.findAll()) {
            l.add(item);
        }
        return l;
    }

    @Override
    public Optional<Tank> findById(Long id) {
        return Optional.ofNullable(dataBase.findByCatalogNumber(id));
    }

    @Override
    public Optional<Tank> create(Tank tank) {
        return Optional.of(dataBase.save(tank));
    }

    @Override
    public Optional<Tank> edit(Tank tank) {
        return Optional.of(dataBase.save(tank));
    }

    @Override
    public Optional<Boolean> deleteById(Long id) {
        dataBase.delete(id);
        return Optional.of(Boolean.TRUE);
    }

    @Override
    public List<Tank> findLatest3() {
        return Collections.emptyList();
    }
}
