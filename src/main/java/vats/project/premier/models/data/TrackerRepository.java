package vats.project.premier.models.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vats.project.premier.models.Tracker;

@Repository
public interface TrackerRepository extends CrudRepository<Tracker, Integer> {
}
