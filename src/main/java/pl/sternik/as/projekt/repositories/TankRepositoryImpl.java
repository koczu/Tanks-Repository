package pl.sternik.as.projekt.repositories;

import org.springframework.stereotype.Service;
import pl.sternik.as.projekt.entities.Status;
import pl.sternik.as.projekt.entities.Tank;
import pl.sternik.as.projekt.entities.Type;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Service
public class TankRepositoryImpl implements TankRepository {

    private List<Tank> tanks = new ArrayList<Tank>() {
        private static final long serialVersionUID = 1L;

        {
            add(Tank.produceTank(1L, "Leopard", "Third Reich", "the best tank", Type.MEDIUM_TANK, new Date(), 15.99, Status.IN_MAINTENANCE));
            add(Tank.produceTank(2L, "Is-7", "USSR", "Russia", Type.HEAVY_TANK, new Date(), 15.09, Status.WORKING));
            add(Tank.produceTank(3L, "Is-7", "USSR", "Russia", Type.HEAVY_TANK, new Date(), 15.09, Status.DUPLICATE));
        }
    };

    @Override
    public List<Tank> findAll() {
        return this.tanks;
    }

    @Override
    public Tank readById(Long id) throws NoSuchTankException {
        return this.tanks.stream().filter(p -> Objects.equals(p.getId(), id)).findFirst()
                .orElseThrow(NoSuchTankException::new);
    }

    @Override
    public Tank create(Tank tank) {
        if (!tanks.isEmpty()) {
            tank.setId(
                    this.tanks.stream().mapToLong(Tank::getId).max().getAsLong() + 1);
        } else {
            tank.setId(1L);
        }
        this.tanks.add(tank);
        return tank;
    }

    @Override
    public Tank update(Tank tank) throws NoSuchTankException {
        for (int i = 0; i < this.tanks.size(); i++) {
            if (Objects.equals(this.tanks.get(i).getId(), tank.getId())) {
                this.tanks.set(i, tank);
                return tank;
            }
        }
        throw new NoSuchTankException("There is no such tank: " + tank.getId());
    }

    @Override
    public void deleteById(Long id) throws NoSuchTankException {
        for (int i = 0; i < this.tanks.size(); i++) {
            if (Objects.equals(this.tanks.get(i).getId(), id)) {
                this.tanks.remove(i);
            }
        }
        throw new NoSuchTankException("There is no such tank: " + id);
    }

}
