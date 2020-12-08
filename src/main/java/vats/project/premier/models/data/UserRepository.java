package vats.project.premier.models.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vats.project.premier.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

}
