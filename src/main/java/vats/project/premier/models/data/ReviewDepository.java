package vats.project.premier.models.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vats.project.premier.models.Review;

import javax.transaction.Transactional;

@Repository
public interface ReviewDepository extends CrudRepository<Review, Integer> {
}
