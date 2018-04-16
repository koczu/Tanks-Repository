package pl.sternik.as.projekt.repositories.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sternik.as.projekt.entities.Tank;

@Repository
public interface TankRepository extends JpaRepository<Tank, Long> {
    Tank findByCatalogNumber(Long id);
}
