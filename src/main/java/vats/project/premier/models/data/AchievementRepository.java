package vats.project.premier.models.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vats.project.premier.models.Achievement;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AchievementRepository extends CrudRepository<Achievement, Integer> {
}
