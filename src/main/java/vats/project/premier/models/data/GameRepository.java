package vats.project.premier.models.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vats.project.premier.models.Game;

import javax.transaction.Transactional;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {
}
